package com.demo.demotask.vms.home

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.*
import com.demo.demotask.local.repositories.MandiListingRepos
import com.demo.demotask.remote.services.NetworkResponses
import com.demo.demotask.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.demo.demotask.local.model.Records as Records

class MandiListingsVM constructor(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    private lateinit var mandiRedords: LiveData<List<Records>>

    init {
        mandiRedords = MandiListingRepos.getMandiRecords(context)
    }

    fun fetchLiveMandiRecords() {

        viewModelScope.launch(Dispatchers.IO) {

            MandiListingRepos.deleteAllRecords(context);

            MandiListingRepos.deleteAllRecords(context);
            val response = MandiListingRepos.fetchMandiData(Constants.apiKey, "json", 0, 200);

            if (response != null) {
                if (response is NetworkResponses.Success) {
                    MandiListingRepos.addRecords(context, response.data.records)
                }
                if (response is NetworkResponses.Error) {
                    Log.e("TAG", "ERROR " + response.exception)
                }
            }

        }
    }

    fun getMandiRecords(): LiveData<List<Records>> {
        return mandiRedords
    }


    fun getSearchableList(data: List<Records>): List<String> {

        val searchableRecords: MutableList<String> = mutableListOf()

        for (i in data) {
            searchableRecords.add(i.district)
            searchableRecords.add(i.state)
            searchableRecords.add(i.market)
        }
        return searchableRecords.distinct()
    }

    fun loadMandiRecordsFromDB(localContext: Context) {
        viewModelScope.launch {
            val records = MandiListingRepos.getAllRecords(context)
        }
    }

    fun loadFromDB(): LiveData<List<Records>> {
        return MandiListingRepos.getAllRecords(context)
    }

    fun sortByDate() {
        mandiRedords.value?.sortedBy { it.arrivalDate }
    }

    fun sortByPrice() {
        mandiRedords.value?.sortedWith(object : Comparator<Records> {
            override fun compare(o1: Records, o2: Records): Int {
                return extractInt(o1) - extractInt(o2)
            }

            fun extractInt(s: Records): Int {
                val num = s.modalPrice
                return Integer.parseInt(num)
            }
        })

    }


}