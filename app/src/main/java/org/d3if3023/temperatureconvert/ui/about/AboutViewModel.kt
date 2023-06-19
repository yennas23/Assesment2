package org.d3if3023.temperatureconvert.ui.about

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3023.temperatureconvert.model.HasilPenemu
import org.d3if3023.temperatureconvert.network.CelciusApi
import org.d3if3023.temperatureconvert.network.CelciusStatus
import org.d3if3023.temperatureconvert.network.UpdateWorker
import java.util.concurrent.TimeUnit

class AboutViewModel: ViewModel() {
    private val data = MutableLiveData<HasilPenemu>()
    private val status = MutableLiveData<CelciusStatus>()

    init {
        retrieveData()
    }

    private fun retrieveData() {
        viewModelScope.launch (Dispatchers.IO) {
            status.postValue(CelciusStatus.LOADING)
            try {
                data.postValue(CelciusApi.service.getData())
                status.postValue(CelciusStatus.SUCCESS)
            } catch (e: Exception) {
                Log.d("AboutViewModel", "Failure: ${e.message}")
                status.postValue(CelciusStatus.FAILED)
            }
        }
    }

    fun getData(): LiveData<HasilPenemu> = data
    fun getStatus(): LiveData<CelciusStatus> = status

    fun scheduleUpdater(app: Application) {
        val request = OneTimeWorkRequestBuilder<UpdateWorker>()
            .setInitialDelay(1, TimeUnit.MINUTES)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            UpdateWorker.WORK_NAME,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }
}