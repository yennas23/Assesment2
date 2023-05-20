package org.d3if3023.temperatureconvert.model

import org.d3if3023.temperatureconvert.db.ConvertionEntity

fun ConvertionEntity.hitungKonversi(): HasilKonversi {
//    val kelvin =  + 273.15.toFloat()
    val convertion = input
    val kelvin = convertion + 273.15
    val fahrenheit = convertion * 4/5
    val reamur = (convertion * 9/5)+32

    return HasilKonversi(kelvin, fahrenheit, reamur)

}

