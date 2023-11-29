package com.plantia.ui.home

fun automaticWaterText(isAutomaticWater: Boolean): String {
    val statusText = if (isAutomaticWater) "ON" else "OFF"

    return "Automatic water is $statusText"
}

fun waterNowText(waterNow: Boolean) : String {
    val statusText = if (waterNow) "YES" else "NO"

    return "Water now? $statusText"
}