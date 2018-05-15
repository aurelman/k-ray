package io.androweed.kray.core

data class Vector(val x: Double, val y: Double, val z: Double) {
    operator fun plus(other: Vector) =
            Vector(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector) =
            Vector(x - other.x, y - other.y, z - other.z)

    operator fun times(scalar: Double) =
            Vector(x * scalar, y * scalar, z * scalar)

    operator fun unaryMinus(): Vector =
            Vector(-x, -y, -z)
}