package io.androweed.kray.core

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.androweed.kray.scene.IntersectionResult
import io.androweed.kray.scene.Primitive
import org.assertj.core.api.Assertions.assertThat
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it


internal object RaySpec : Spek({

    describe("a Ray") {
        it("should delegate intersection computation to primitive") {
            val ray = Ray(origin= Vector(0.0, 0.0, 0.0), direction = Vector(0.0, 0.0, 1.0))

            val primitive = mock<Primitive>()
            val intersection = IntersectionResult( intersect = false, intersection = null, primitive = primitive)
            whenever(primitive.intersect(ray)).thenReturn(intersection)

            val result = ray.intersect(primitive)

            assertThat(result).isEqualTo(intersection)
            verify(primitive).intersect(ray)
        }
    }
})

