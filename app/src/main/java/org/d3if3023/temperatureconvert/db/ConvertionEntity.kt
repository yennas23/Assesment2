package org.d3if3023.temperatureconvert.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "celciusconvertion")
data class ConvertionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    val input: Double

//    var hasilhistory: String = "",
//    var konversi: Double,
)

