package org.d3if3023.temperatureconvert.ui.about.main

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.TextUtils
import android.view.*
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3023.temperatureconvert.R
import org.d3if3023.temperatureconvert.databinding.FragmentMainBinding
import org.d3if3023.temperatureconvert.db.ConvertionDb
import org.d3if3023.temperatureconvert.model.HasilKonversi

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
//    val df = DecimalFormat("#.##")//Decimal formatter

    private val viewModel: MainViewModel by lazy {
        val data = ConvertionDb.getInstance(requireContext())
        val factory = MainViewModelFactory(data.dao)
        ViewModelProvider(this, factory)[MainViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.btnConvert.setOnClickListener { hitungKonversi() }
        viewModel.getHasilKonversi().observe(requireActivity()) { showResult(it) }
    }

    private fun isInputEmpty(input: String): Boolean {
        return input.isNullOrBlank()
    }
    private fun isInputNumber(input: String): Boolean {
        return input.toIntOrNull() != null
    }

    fun isInputValid(input: String): Boolean {
        val regex = Regex("^(?=.*[0-9])(?=.*[a-zA-Z])([a-zA-Z0-9]+)$")
        return regex.matches(input)
    }

    private fun hitungKonversi() {
        // inisialiasi variabel dengan memamggil inputan dari inpTemperature
        val suhu = binding.inpTemperature.text.toString()
        var input = suhu.toDouble()

        // melakukan pengecekan inputan kalau kosong (sanity check)
        if (TextUtils.isEmpty(suhu)) {
            Toast.makeText(context, R.string.suhu_invalid, Toast.LENGTH_LONG).show()
        }

        // rumus mengubah suhu
//        val kelvin = df.format(doubleInput + 273.15)
//        val fahrenheit = df.format((doubleInput * 9/5)+32)
//        val reamur = df.format(doubleInput * 4/5)

        viewModel.hitungKonversi(input)

    }

    @SuppressLint("SetTextI18n")
    private fun showResult(result: HasilKonversi?){
        if (result == null) return
        binding.Konversi.isVisible = true
        binding.hasilKelvin.isVisible = true
        binding.hasilFahrenheit.isVisible = true
        binding.hasilReamur.isVisible = true

        binding.Konversi.text = getString(R.string.konversi_intro)
        binding.hasilKelvin.text = "Kelvin : " + result.kelvin.toString()
        binding.hasilFahrenheit.text = "Fahrenheit : " + result.fahrenheit.toString()
        binding.hasilReamur.text = "Reamur : " + result.reamur.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.my_menu, menu)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(R.id.action_mainFragment_to_historyFragment)
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(R.id.action_mainFragment_to_aboutFragment)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }


}

