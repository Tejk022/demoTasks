package com.demo.demotask.local.dao

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.*
import com.demo.demotask.local.model.MandiDataResponse
import com.demo.demotask.local.model.Records
import com.demo.demotask.utils.RecordsConverter

@Dao
interface MandiRecordsDao {

    @TypeConverters(RecordsConverter::class)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRecords(records: List<Records>)

    @TypeConverters(RecordsConverter::class)
    @Query("SELECT * FROM MandiRecords")
    fun getAllRecords(): LiveData<List<Records>>

    @TypeConverters(RecordsConverter::class)
    @Query("SELECT * FROM MandiRecords WHERE district AND market AND state Like:searchKey")
    fun getSearchRecords(searchKey: String): LiveData<List<Records>>

    @Query("DELETE FROM MandiRecords")
    fun deleteAll()

    @Delete
    fun deleteEverything(records: List<Records>)

}