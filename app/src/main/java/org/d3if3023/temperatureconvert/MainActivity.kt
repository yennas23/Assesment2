package org.d3if3023.temperatureconvert

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    companion object {
        const val CHANNEL_ID = "updater"
        const val PERMISSION_REQUEST_CODE = 1
    }

//    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = getString(R.string.channel_name)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance)
            channel.description = getString(R.string.channel_desc)
            val manager = getSystemService(Context.NOTIFICATION_SERVICE)
                    as NotificationManager?
            manager?.createNotificationChannel(channel)
        }

        navController = findNavController(R.id.myNavHostFragment)
        NavigationUI.setupActionBarWithNavController(this, navController)
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    // membuat dialog exit
    override fun onBackPressed() {
        // inisialisasi alert dialog
        val builder = AlertDialog.Builder(this)

        // mengeset judul dialog
        builder.setTitle(R.string.app_name)

        // mengeset pesan dialog
        builder.setMessage(R.string.exit_dialog)

        // jika ya
        builder.setPositiveButton("Ya") { _, _ ->
            this.finish()
        }

        // jika tidak
        builder.setNegativeButton("Tidak") { _, _ -> }

        // dialog tidak bisa kembali / cancel
        builder.setCancelable(false)

        // menampilkan dialog
        builder.show()
    }
}

//    private lateinit var binding: ActivityMainBinding
//
//    private val viewModel: SatuViewModel by lazy {
//        ViewModelProvider(this)[SatuViewModel::class.java]
//    }
//
//    val df = DecimalFormat("#.##")//Decimal formatter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        binding.btnConvert.setOnClickListener { hitungKonversi() }
//        viewModel.getHasilKonversi().observe(this, {showResult(it)})
//    }
//
//
//    private fun hitungKonversi() {
//        // inisialiasi variabel dengan memamggil inputan dari inpTemperature
//        val suhu = binding.inpTemperature.text.toString()
////        var doubleInput = suhu.toDouble()
//
//        viewModel.hitungKonversi(
//            suhu.toDouble()
//        )
//
//        // melakukan pengecekan inputan kalau kosong (sanity check)
//        if (TextUtils.isEmpty(suhu)) {
//            Toast.makeText(this, R.string.suhu_invalid, Toast.LENGTH_LONG).show()
//        }
//
////        viewModel.hitungKonversi(
////            kelvin.toDouble(),
////            fahrenheit.toDouble(),
////            reamur.toDouble()
////        )
//
//        // membuat layout dapat dilihat (sebelumnya diset tidak terlihat dilayout)
//        binding.Konversi.isVisible = true
//        binding.hasilFahrenheit.isVisible = true
//        binding.hasilKelvin.isVisible = true
//        binding.hasilReamur.isVisible = true
//
////        // rumus mengubah suhu
////        val kelvin = df.format(doubleInput + 273.15)
////        val fahrenheit = df.format((doubleInput * 9/5)+32)
////        val reamur = df.format(doubleInput * 4/5)
//
//    }
//
//    @SuppressLint("StringFormatInvalid")
//    private fun showResult(result: HasilKonversi?){
//        if (result == null) return
//        binding.Konversi.text = getString(R.string.konversi_intro)
//        binding.hasilKelvin.text = getString(R.string.kel, result.kelvin)
//        binding.hasilFahrenheit.text = getString(R.string.fah, result.fahrenheit)
//        binding.hasilReamur.text = getString(R.string.rea, result.reamur)
//    }
//}
