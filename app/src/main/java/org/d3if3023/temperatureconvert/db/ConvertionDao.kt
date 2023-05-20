package org.d3if3023.temperatureconvert.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface ConvertionDao {
    @Insert
    fun insert(celciusconvertion: ConvertionEntity)

    @Query("SELECT * FROM celciusconvertion ORDER BY id DESC")
    fun getLastConvertion(): LiveData<List<ConvertionEntity>>

    @Query("DELETE FROM celciusconvertion")
    fun clearData()
}
