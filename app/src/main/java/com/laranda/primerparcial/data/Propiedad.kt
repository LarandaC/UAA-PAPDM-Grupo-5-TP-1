package com.laranda.primerparcial.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.laranda.primerparcial.R

enum class tipoPropiedad {
    CASA, APARTAMENTO
}

data class Propiedad(
    @StringRes val direccion: Int,
    val tipo: tipoPropiedad,
    val precio: Double,
    val superficie: Double,
    val numHabitaciones: Int,
    @DrawableRes val imageResourceId: Int,
)

val propiedades = listOf(
    Propiedad(
        direccion = R.string.direccion1,
        tipo = tipoPropiedad.CASA,
        precio = 250000.0,
        superficie = 150.0,
        numHabitaciones = 3,
        imageResourceId = R.drawable.casa1
    ),
    Propiedad(
        direccion = R.string.direccion2,
        tipo = tipoPropiedad.APARTAMENTO,
        precio = 180000.0,
        superficie = 100.0,
        numHabitaciones = 2,
        imageResourceId = R.drawable.apartamento1
    ),
    Propiedad(
        direccion = R.string.direccion3,
        tipo = tipoPropiedad.CASA,
        precio = 320000.0,
        superficie = 200.0,
        numHabitaciones = 4,
        imageResourceId = R.drawable.casa2
    ),
    Propiedad(
        direccion = R.string.direccion4,
        tipo = tipoPropiedad.APARTAMENTO,
        precio = 220000.0,
        superficie = 120.0,
        numHabitaciones = 3,
        imageResourceId = R.drawable.apartamento2
    )
)
