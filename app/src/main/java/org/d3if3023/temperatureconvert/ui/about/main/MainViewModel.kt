package org.d3if3023.temperatureconvert.ui.about.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3023.temperatureconvert.db.ConvertionDao
import org.d3if3023.temperatureconvert.db.ConvertionEntity
import org.d3if3023.temperatureconvert.model.HasilKonversi
import org.d3if3023.temperatureconvert.model.hitungKonversi

class MainViewModel (private val data: ConvertionDao) : ViewModel() {
    private val hasilKonversi = MutableLiveData<HasilKonversi?>()

    fun hitungKonversi(konversiA:Double) {
        val dataConvertion = ConvertionEntity(
            input = konversiA


        )
        hasilKonversi.value = dataConvertion.hitungKonversi()
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                data.insert(dataConvertion)
            }
        }
    }
    fun getHasilKonversi(): LiveData<HasilKonversi?> = hasilKonversi


}