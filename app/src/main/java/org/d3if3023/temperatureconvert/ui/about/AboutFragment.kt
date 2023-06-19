package org.d3if3023.temperatureconvert.ui.about

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import org.d3if3023.temperatureconvert.MainActivity
import org.d3if3023.temperatureconvert.R
import org.d3if3023.temperatureconvert.databinding.FragmentAboutBinding
import org.d3if3023.temperatureconvert.model.HasilPenemu
import org.d3if3023.temperatureconvert.network.CelciusApi
import org.d3if3023.temperatureconvert.network.CelciusStatus

class AboutFragment : Fragment(){
    private lateinit var binding: FragmentAboutBinding
    private val viewModel: AboutViewModel by lazy {
        ViewModelProvider(this)[AboutViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            binding.buttonBagikan.setOnClickListener { bagikanApp() }

        viewModel.getData().observe(viewLifecycleOwner) {

            binding.textView3.text = it.informasi
            Glide.with(binding.logo.context)
                .load(CelciusApi.getCelciusUrl(it.gambar))
                .error(R.drawable.baseline_broken_image_24)
                .into(binding.logo)

        }

        viewModel.getStatus().observe(viewLifecycleOwner) {
            updateProgress(it)
        }

        viewModel.scheduleUpdater(requireActivity().application)
        }
        private fun updateProgress(status: CelciusStatus) {
            when (status) {
                CelciusStatus.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                CelciusStatus.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        requestNotificationPermission()
                    }
                }
                CelciusStatus.FAILED -> {
                    binding.progressBar.visibility = View.GONE
                    binding.networkError.visibility = View.VISIBLE
                }
            }
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun requestNotificationPermission() {
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.POST_NOTIFICATIONS),
                MainActivity.PERMISSION_REQUEST_CODE
            )
        }
    }

    fun bagikanApp(){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, requireActivity().getString(R.string.app_name))
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Konversikan suhu celcius yang kamu inginkan sekarang dengan Celcius Convertion !")
        requireActivity().startActivity(Intent.createChooser(sharingIntent, "Bagikan Melalui"))
    }
}
