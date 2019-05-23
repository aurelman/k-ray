package io.androweed.kray.core

import java.lang.Float.min

data class Color(
    val red: Float = 0.0f,
    val green: Float = 0.0f,
    val blue: Float = 0.0f
) {
    companion object {
        const val COMPONENT_MIN_VALUE = 0.0f
        const val COMPONENT_MAX_VALUE = 1.0f
        const val BAD_COMPONENT_VALUE_MESSAGE = "component value must be in [0.0, $COMPONENT_MAX_VALUE]"
    }

    init {
        ensureComponentValueIsOk(red)
        ensureComponentValueIsOk(green)
        ensureComponentValueIsOk(blue)
    }

    operator fun plus(color: Color) =
        Color(clamp(red + color.red), clamp(green + color.green), clamp(blue + color.blue))

    private fun clamp(value: Float) = min(value, COMPONENT_MAX_VALUE)

    private fun ensureComponentValueIsOk(value: Float) {
        require(value in COMPONENT_MIN_VALUE..COMPONENT_MAX_VALUE) { BAD_COMPONENT_VALUE_MESSAGE }
    }
}