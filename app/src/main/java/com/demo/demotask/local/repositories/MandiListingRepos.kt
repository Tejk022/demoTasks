package com.demo.demotask.local.repositories

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.demo.demotask.local.database.MandiDatabase
import com.demo.demotask.local.model.MandiDataResponse
import com.demo.demotask.local.model.Records
import com.demo.demotask.remote.retrofit.RetrofitInstance
import com.demo.demotask.remote.services.NetworkResponses
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MandiListingRepos {

    companion object {

        var mandiDatabase: MandiDatabase? = null
        var mutableRecords: MutableLiveData<List<Records>> = MutableLiveData()

        fun initDB(context: Context): MandiDatabase {
            return MandiDatabase.getDatabase(context);
        }

        suspend fun fetchMandiData(
            apiKey: String,
            format: String,
            offset: Int,
            limit: Int
        ): NetworkResponses<MandiDataResponse> {

            return try {
                var response = RetrofitInstance
                    .getMandiService()
                    .getMandiData(apiKey, format, offset, limit)
                NetworkResponses.Success(response)

            } catch (e: HttpException) {
                NetworkResponses.Error(exception = e)
            }
        }

        suspend fun addRecords(context: Context, record: List<Records>) {
            mandiDatabase = initDB(context)
            CoroutineScope(IO).launch {
                mandiDatabase!!.getMandiDao().insertRecords(record)
            }
        }

        fun getAllRecords(context: Context): LiveData<List<Records>> {
            mandiDatabase = initDB(context)
            mutableRecords.postValue(mandiDatabase!!.getMandiDao().getAllRecords().value)
            return mutableRecords;
        }

        fun deleteAllRecords(context: Context) {
            mandiDatabase = initDB(context)
            mandiDatabase!!.getMandiDao().deleteAll()
        }

        fun getSearchRecords(context: Context, searchKey: String): LiveData<List<Records>> {
            mandiDatabase = initDB(context)
            return mandiDatabase!!.getMandiDao().getSearchRecords(searchKey);
        }


        fun getMandiRecords(context: Context): LiveData<List<Records>> {
            mandiDatabase = initDB(context)
            return mandiDatabase!!.getMandiDao().getAllRecords()
        }

    }


}