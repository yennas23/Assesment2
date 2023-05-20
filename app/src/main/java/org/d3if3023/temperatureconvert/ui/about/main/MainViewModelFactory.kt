package org.d3if3023.temperatureconvert.ui.about.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import org.d3if3023.temperatureconvert.db.ConvertionDao

class MainViewModelFactory(
    private val data: ConvertionDao
) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(data) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}