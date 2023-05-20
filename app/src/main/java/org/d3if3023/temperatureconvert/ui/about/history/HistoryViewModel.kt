package org.d3if3023.temperatureconvert.ui.about.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if3023.temperatureconvert.db.ConvertionDao

class HistoryViewModel(private val data: ConvertionDao) : ViewModel() {
    val db = data.getLastConvertion()
    fun hapusData() = viewModelScope.launch {
        withContext(Dispatchers.IO) {
            data.clearData()
        }
    }
}