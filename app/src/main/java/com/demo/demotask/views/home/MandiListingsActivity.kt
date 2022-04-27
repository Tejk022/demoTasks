package com.demo.demotask.views.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.demo.demotask.R
import com.demo.demotask.local.model.Records
import com.demo.demotask.views.home.adapter.MandiRecordsAdapter
import com.demo.demotask.vms.home.MandiListingVMFactory
import com.demo.demotask.vms.home.MandiListingsVM
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import javax.security.auth.login.LoginException
import kotlin.Comparator

class MandiListingsActivity : AppCompatActivity() {

    private lateinit var mandiListingVM: MandiListingsVM;
    private lateinit var mandiRecordsAdapter: MandiRecordsAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var autoComplete: AutoCompleteTextView
    private lateinit var searchButton: Button
    private lateinit var dateS: Button
    private lateinit var reset: Button
    private lateinit var priceS: Button
    private var sortPrice = true
    private lateinit var autocompleteAdapter: ArrayAdapter<String>
    private var mandiRecords: List<Records> = listOf()
    private lateinit var vmFactory: MandiListingVMFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mandi_listings)

        recyclerView = findViewById(R.id.recvMandiListing)
        autoComplete = findViewById<AutoCompleteTextView>(R.id.searchEditText)
        searchButton = findViewById<Button>(R.id.search)
        priceS = findViewById<Button>(R.id.price)
        reset = findViewById<Button>(R.id.reset)
        dateS = findViewById<Button>(R.id.date)

        recyclerView.layoutManager = LinearLayoutManager(this)

        vmFactory = MandiListingVMFactory(application)

        mandiListingVM =
            ViewModelProvider(this, vmFactory).get(MandiListingsVM::class.java);

        mandiRecordsAdapter = MandiRecordsAdapter(arrayListOf())
        recyclerView.adapter = mandiRecordsAdapter

        mandiListingVM.fetchLiveMandiRecords();

        mandiListingVM.getMandiRecords().observe(this, Observer {
            mandiRecords = it
            updateUiRecords(it)
            updateAdapter(it)
        })


        searchButton.setOnClickListener(View.OnClickListener {
            val enteredText = autoComplete.getText().toString()
            var filteredRecords: List<Records> = mandiRecords
            filteredRecords =
                filteredRecords.filter { it.district == enteredText || it.market == enteredText || it.state == enteredText }
            updateUiRecords(filteredRecords)
            hideKeyboard()
        })

        priceS.setOnClickListener(View.OnClickListener {
            sortData()
        })

        reset.setOnClickListener(View.OnClickListener {
            updateUiRecords(mandiRecords)
            autoComplete.setText("")
        })
        dateS.setOnClickListener(View.OnClickListener {

            val sortData = mandiRecords.sortedBy { it.arrivalDate }
            updateUiRecords(sortData)

        })

    }


    fun updateUiRecords(records: List<Records>) {

        if (mandiRecordsAdapter != null) {
            mandiRecordsAdapter = MandiRecordsAdapter(records)
            recyclerView.adapter = mandiRecordsAdapter
        }
    }

    fun updateAdapter(records: List<Records>) {

        autocompleteAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_expandable_list_item_1,
            mandiListingVM.getSearchableList(records)
        )
        autoComplete.setAdapter(autocompleteAdapter)
    }

    fun Activity.hideKeyboard() {
        hideKeyboard(currentFocus ?: View(this))
    }

    fun Context.hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun sortData() {
        var sortData = mandiRecords.sortedWith(object : Comparator<Records> {
            override fun compare(o1: Records, o2: Records): Int {
                return extractInt(o1) - extractInt(o2)
            }

            fun extractInt(s: Records): Int {
                val num = s.modalPrice
                return Integer.parseInt(num)
            }
        })
        if (!sortPrice) {
            Collections.reverse(sortData)
        }
        sortPrice = !sortPrice
        updateUiRecords(sortData)
    }

}