package com.laranda.primerparcial.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.mutableStateListOf
import com.laranda.primerparcial.R

enum class tipoPropiedad {
    CASA, APARTAMENTO
}

data class Propiedad(
    val direccion: String,
    val tipo: tipoPropiedad,
    val precio: Double,
    val superficie: Double,
    val numHabitaciones: Int,
    val imageResourceId: String,
)

val propiedades = mutableStateListOf<Propiedad>(
    Propiedad(
        direccion = "Calle 123, Ciudad A",
        tipo = tipoPropiedad.CASA,
        precio = 250000.0,
        superficie = 150.0,
        numHabitaciones = 3,
        imageResourceId = "https://media.admagazine.com/photos/654119298f54ac8192294a51/16:9/w_1920,c_limit/casa%20up.jpg"
    ),
    Propiedad(
        direccion = "Avenida 456, Ciudad B",
        tipo = tipoPropiedad.APARTAMENTO,
        precio = 180000.0,
        superficie = 100.0,
        numHabitaciones = 2,
        imageResourceId = "https://definicion.de/wp-content/uploads/2021/01/Apartamento-1.jpg"
    ),
)

fun listarPropiedades(): List<Propiedad> {
    return propiedades
}

fun add(propiedad: Propiedad) {
    propiedades.add(propiedad)
}

fun eliminarPropiedad(propiedad: Propiedad) {
    propiedades.remove(propiedad)
}

