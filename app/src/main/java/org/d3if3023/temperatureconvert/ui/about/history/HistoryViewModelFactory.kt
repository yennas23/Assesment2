package org.d3if3023.temperatureconvert.ui.about.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3023.temperatureconvert.db.ConvertionDao

class HistoryViewModelFactory(
    private val data: ConvertionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HistoryViewModel::class.java)) {
            return HistoryViewModel(data) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}