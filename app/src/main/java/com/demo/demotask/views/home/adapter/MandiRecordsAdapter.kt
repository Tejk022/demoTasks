package com.demo.demotask.views.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.demo.demotask.R
import com.demo.demotask.local.model.MandiDataResponse
import com.demo.demotask.local.model.Records

class MandiRecordsAdapter(var records: List<Records>) :
    RecyclerView.Adapter<MandiRecordsAdapter.ModelListViewHolder>() {


    class ModelListViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val productNameTV: TextView = itemView.findViewById(R.id.productName);
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelListViewHolder {
        val productItemView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item_view, parent, false);

        return ModelListViewHolder(productItemView);
    }

    override fun onBindViewHolder(holder: ModelListViewHolder, position: Int) {

        var mandiProduct = records.get(position);
        var line = "State:" + mandiProduct.state +
                "\nModal Price:" + mandiProduct.modalPrice +
                "\nArrival Date :" + mandiProduct.arrivalDate +
                " \nproduct :" + mandiProduct.commodity +
                "\nDistrict :" + mandiProduct.district +
                "\nMarket :" + mandiProduct.market

        holder.productNameTV.setText(line);
    }

    override fun getItemCount(): Int {
        return records.size;
    }


}