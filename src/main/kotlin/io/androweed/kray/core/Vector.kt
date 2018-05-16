package io.androweed.kray.core

import kotlin.math.sqrt

/**
 * `Vector` is an essential type in `k-ray`.
 *
 * They can be used to represent points in the 3D space, or direction in that space.
 *
 * `Vector` instances have value semantic and thus are immutable and thread safe.
 */
data class Vector constructor(val x: Double = 0.0, val y: Double = 0.0, val z: Double = 0.0) {
    private var normalized: Boolean = false

    private constructor(x: Double = 0.0, y: Double = 0.0, z: Double = 0.0, normalized: Boolean) : this(x, y, z) {
        this.normalized = normalized
    }

    operator fun plus(other: Vector) = Vector(x + other.x, y + other.y, z + other.z)

    operator fun minus(other: Vector) =  Vector(x - other.x, y - other.y, z - other.z)

    /**
     * @see Double.times for the reverse operation
     */
    operator fun times(scalar: Double) = Vector(x * scalar, y * scalar, z * scalar)

    operator fun unaryMinus(): Vector = Vector(-x, -y, -z)

    infix fun dot(other: Vector) = x * other.x + y * other.y + z * other.z

    infix fun cross(other: Vector) = Vector(
            y * other.z - z * other.y,
            z * other.x - x * other.z,
            x * other.y - y * other.x)

    /**
     * Returns a `Vector` with the same direction as the current instance, but whose length is `1.0`.
     * It actually consists of a `Vector` where each coordinate has been divided by the length of the current `Vector`
     * instance.
     *
     * For optimization matters, if the current instance is already the result of a previous call to [normalize]
     * on a `Vector` instance, it will return the current (`this`) instance, avoiding useless computations.
     */
    fun normalize(): Vector {
        if (normalized) {
            return this
        }
        return Vector(x / length, y / length, z / length, true)
    }

    val length by lazy {
        if (normalized) {
            1.0
        } else {
            sqrt(squaredLength)
        }
    }

    val squaredLength by lazy {
        if (normalized) {
            1.0
        } else {
            x * x + y * y + z * z
        }
    }
}

operator fun Double.times(vec: Vector) = vec * this