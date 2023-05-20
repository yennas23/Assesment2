package org.d3if3023.temperatureconvert.ui.about

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import org.d3if3023.temperatureconvert.R
import org.d3if3023.temperatureconvert.databinding.FragmentAboutBinding

class AboutFragment : Fragment(){
    private lateinit var binding: FragmentAboutBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.buttonBagikan.setOnClickListener { bagikanApp() }
    }

    fun bagikanApp(){
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, requireActivity().getString(R.string.app_name))
        sharingIntent.putExtra(Intent.EXTRA_TEXT, "Konversikan suhu celcius yang kamu inginkan sekarang dengan Celcius Convertion !")
        requireActivity().startActivity(Intent.createChooser(sharingIntent, "Bagikan Melalui"))
    }
}
