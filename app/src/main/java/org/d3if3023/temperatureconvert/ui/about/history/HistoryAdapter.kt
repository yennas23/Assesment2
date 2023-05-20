package org.d3if3023.temperatureconvert.ui.about.history

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if3023.temperatureconvert.databinding.ListHistoryBinding
import org.d3if3023.temperatureconvert.db.ConvertionEntity
import org.d3if3023.temperatureconvert.model.hitungKonversi
import java.text.SimpleDateFormat
import java.util.*

class HistoryAdapter : ListAdapter<ConvertionEntity, HistoryAdapter.ViewHolder>(DIFF_CALLBACK) {
    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<ConvertionEntity>() {
                override fun areItemsTheSame(
                    oldData: ConvertionEntity, newData: ConvertionEntity
                ): Boolean {
                    return oldData.id == newData.id
                }

                override fun areContentsTheSame(
                    oldData: ConvertionEntity, newData: ConvertionEntity
                ): Boolean {
                    return oldData == newData
                }
            }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListHistoryBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ListHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: ConvertionEntity) = with(binding) {
            val rnd = Random()
            val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))

            val hasilKonversi = item.hitungKonversi()
            kategoriTextView.text = hasilKonversi.kelvin.toString()
            kategoriTextView.text = hasilKonversi.fahrenheit.toString()
            kategoriTextView.text = hasilKonversi.reamur.toString()
            val circleBg = kategoriTextView.background as GradientDrawable
            circleBg.setColor(color)
            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            universeTextView.text = hasilKonversi.kelvin.toString()
            universeTextView.text = hasilKonversi.fahrenheit.toString()
            universeTextView.text = hasilKonversi.reamur.toString()
        }
    }
}