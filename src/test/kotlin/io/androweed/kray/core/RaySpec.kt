package io.androweed.kray.core


import assertk.assertThat
import assertk.assertions.isEqualTo
import io.androweed.kray.scene.IntersectionResult
import io.androweed.kray.scene.Primitive
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal object RaySpec : Spek({

    describe("a Ray") {
        it("should delegate intersection computation to primitive") {
            val ray = Ray(origin= Vector(0.0, 0.0, 0.0), direction = Vector(0.0, 0.0, 1.0))

            val primitive = mockk<Primitive>()
            val intersection = IntersectionResult( intersect = false, intersection = null, primitive = primitive)

            every { primitive.intersect(ray) } returns intersection

            val result = ray.intersect(primitive)

            assertThat(result).isEqualTo(intersection)
            verify { primitive.intersect(ray) }
        }
    }
})

