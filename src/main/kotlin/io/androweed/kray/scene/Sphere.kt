package io.androweed.kray.scene

import io.androweed.kray.core.Position
import io.androweed.kray.core.QuadraticEquation
import io.androweed.kray.core.Ray
import io.androweed.kray.geometry.IntersectionResult
import io.androweed.kray.geometry.Primitive

data class Sphere(private val center: Position, private val radius: Double): Primitive {
    override fun normalAt(position: Position) = (center toward position).normalize()

    override fun intersect(ray: Ray): IntersectionResult {
        val positionToRayOrigin = center toward ray.origin
        val a = ray.direction.squaredLength
        val b = 2 * (ray.direction dot positionToRayOrigin)
        val c = positionToRayOrigin.squaredLength - squaredRadius
        val result = QuadraticEquation(a, b, c)
        if (result.hasNoRoot()) {
            return IntersectionResult(this, false, null)
        }
        val intersectionPosition = ray.origin + (ray.direction.normalize() * result.firstRoot())
        return IntersectionResult(this, true, intersectionPosition)
    }

    private val squaredRadius by lazy {
        radius * radius
    }
}