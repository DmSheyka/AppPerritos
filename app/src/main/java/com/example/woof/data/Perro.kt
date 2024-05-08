package com.example.woof.data

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.example.woof.R

data class Perro(
    @DrawableRes val idRecursoImg: Int,
    @StringRes val nombre: Int,
    val annio: Int,
    @StringRes val pasatiempos: Int
)
val perros = listOf(
    Perro(R.drawable.koda, R.string.nombre_1, 2, R.string.descripcion_1),
    Perro(R.drawable.lola, R.string.nombre_2, 16, R.string.descripcion_2),
    Perro(R.drawable.frankie, R.string.nombre_3, 2, R.string.descripcion_3),
    Perro(R.drawable.nox, R.string.nombre_4, 8, R.string.descripcion_4),
    Perro(R.drawable.faye, R.string.nombre_5, 8, R.string.descripcion_5),
    Perro(R.drawable.bella, R.string.nombre_6, 14, R.string.descripcion_6),
    Perro(R.drawable.moana, R.string.nombre_7, 2, R.string.descripcion_7),
    Perro(R.drawable.tzeitel, R.string.nombre_8, 7, R.string.descripcion_8),
    Perro(R.drawable.leroy, R.string.nombre_9, 4, R.string.descripcion_9)
)
