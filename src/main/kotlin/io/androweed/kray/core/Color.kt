package io.androweed.kray.core

import kotlin.math.max

data class Color(
    val red: Float = 0.0f,
    val green: Float = 0.0f,
    val blue: Float = 0.0f
) {
    companion object {
        private const val COMPONENT_MIN_VALUE = 0.0f
        private const val COMPONENT_MAX_VALUE = 1.0f

        private const val BAD_COMPONENT_VALUE_MESSAGE = "component value must be in [0.0, $COMPONENT_MAX_VALUE]"

        private fun ensureComponentValueIsOk(value: Float) {
            require(value in COMPONENT_MIN_VALUE..COMPONENT_MAX_VALUE) { BAD_COMPONENT_VALUE_MESSAGE }
        }

        private fun withClampedComponents(rawRed: Float, rawGreen: Float, rawBlue: Float): Color {
            val max = max(rawRed, max(rawGreen, rawBlue))
            if (max <= 1.0) {
                return Color(rawRed, rawGreen, rawBlue)
            }
            return Color(rawRed / max, rawGreen / max, rawBlue / max)
        }
    }

    init {
        ensureComponentValueIsOk(red)
        ensureComponentValueIsOk(green)
        ensureComponentValueIsOk(blue)
    }

    operator fun plus(color: Color) =
        withClampedComponents(red + color.red, green + color.green, blue + color.blue)

    operator fun times(scalar: Number) =
        withClampedComponents(red * scalar.toFloat(), green * scalar.toFloat(), blue * scalar.toFloat())
}

operator fun Number.times(color: Color) = color * this
