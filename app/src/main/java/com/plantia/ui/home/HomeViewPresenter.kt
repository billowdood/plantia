package com.plantia.ui.home

fun automaticWaterText(isAutomaticWater: Boolean): String {
    val statusText = if (isAutomaticWater) "ON" else "OFF"

    return "Automatic water is $statusText"
}