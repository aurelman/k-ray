package io.androweed.kray.scene

import assertk.assertThat
import assertk.assertions.isCloseTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import io.androweed.kray.core.Position
import io.androweed.kray.core.Ray
import io.androweed.kray.core.Vector
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


internal object SphereSpec : Spek({

    describe("a Sphere") {

        val sphere = Sphere(
                center = Vector(0.0, 0.0, 0.0),
                radius = 10.0)

        context("testing intersections") {

            it("should detect no intersection if none") {
                val ray = Ray(origin = Vector(100.0, 100.0, 100.0), direction = Vector(12.0, 25.0, 100.0))
                val result = sphere.intersect(ray)
                assertThat(result.intersect).isFalse()
            }

            it("should detect intersection with a shaving ray") {
                val ray  = Ray(origin = Position(x = 0.0, y = 10.0, z = -100.0), direction = Vector(x= 0.0, y = 0.0, z = 1.0))
                val result = sphere.intersect(ray)
                assertThat(result.intersect).isTrue()
                assertThat(result.intersection?.x as Double).isCloseTo(0.0, 0.000001)
                assertThat(result.intersection?.y as Double).isCloseTo(10.0, 0.000001)
                assertThat(result.intersection?.z as Double).isCloseTo(0.0, 0.000001)

                assertThat(result.normal.x).isCloseTo(0.0, 0.000001)
                assertThat(result.normal.y).isCloseTo(1.0, 0.000001)
                assertThat(result.normal.z).isCloseTo(0.0, 0.000001)
            }

            it("should detect intersection with a traversing ray") {
                val ray  = Ray(origin = Position(x = 0.0, y = 0.0, z = -100.0), direction = Vector(x= 0.0, y = 0.0, z = 1.0))

                val result = sphere.intersect(ray)

                assertThat(result.intersect).isTrue()
                assertThat(result.intersection?.x as Double).isCloseTo(0.0, 0.000001)
                assertThat(result.intersection?.y as Double).isCloseTo(0.0, 0.000001)
                assertThat(result.intersection?.z as Double).isCloseTo(-10.0, 0.000001)

                assertThat(result.normal.x).isCloseTo(0.0, 0.000001)
                assertThat(result.normal.y).isCloseTo(0.0, 0.000001)
                assertThat(result.normal.z).isCloseTo(-1.0, 0.000001)
            }
        }
    }
})