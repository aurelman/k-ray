package io.androweed.kray.core

import assertk.assertThat
import assertk.assertions.isCloseTo
import assertk.assertions.isEqualTo
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe


object VectorSpec : Spek({
    val offset = 0.000001

    describe("a single vector") {

        val vec = Vector(1.0, 5.0, -12.0)

        it ("returns the coordinates") {
            assertThat(vec.x).isEqualTo(1.0)
            assertThat(vec.y).isEqualTo(5.0)
            assertThat(vec.z).isEqualTo(-12.0)
        }

        describe("vector operations") {
            context("addition") {
                val vec2 = Vector(2.0, 3.0, 5.0)
                it ("should return the resulting vector") {
                    val vec3 = vec + vec2
                    assertThat(vec3.x).isCloseTo(3.0, offset)
                    assertThat(vec3.y).isCloseTo(8.0, offset)
                    assertThat(vec3.z).isCloseTo(-7.0, offset)
                }
            }

            context("subtraction") {
                val vec2 = Vector(2.0, 3.0, 5.0)
                it ("should return the resulting vector") {
                    val vec3 = vec - vec2
                    assertThat(vec3.x).isCloseTo(-1.0, offset)
                    assertThat(vec3.y).isCloseTo(2.0, offset)
                    assertThat(vec3.z).isCloseTo(-17.0, offset)
                }
            }

            context("multiplication by a scalar") {
                it ("should return the resulting vector") {
                    val vec3 = vec * 2.0
                    assertThat(vec3.x).isCloseTo(2.0, offset)
                    assertThat(vec3.y).isCloseTo(10.0, offset)
                    assertThat(vec3.z).isCloseTo(-24.0, offset)
                }

                it ("should also be a commutative operation") {
                    val vec3 =  2.0 * vec
                    assertThat(vec3.x).isCloseTo(2.0, offset)
                    assertThat(vec3.y).isCloseTo(10.0, offset)
                    assertThat(vec3.z).isCloseTo(-24.0, offset)
                }
            }

            context("computing the opposite") {
                it("should return the opposite vector") {
                    val vec2 = -vec
                    assertThat(vec2.x).isCloseTo(-1.0, offset)
                    assertThat(vec2.y).isCloseTo(-5.0, offset)
                    assertThat(vec2.z).isCloseTo(12.0, offset)
                }
            }

            context("length") {
                it("should return the magnitude of the vector") {
                    assertThat(vec.length).isCloseTo(13.0384048, offset)
                }
            }

            context("squaredLength") {
                it("should return the squared magnitude of the vector") {
                    assertThat(vec.squaredLength).isCloseTo(170.0, offset)
                }
            }

            context("computing dot product") {
                it("should compute the result of the dot product with another vector") {
                    val other = Vector(-5.0, 8.0, 25.0)
                    assertThat(vec dot other).isCloseTo(-265.0, offset)
                }
            }

            context("computing cross product") {
                it("should compute the result of the  cross product with another vector") {
                    val other = Vector(-5.0, 8.0, 25.0)
                    val result = vec cross other
                    assertThat(result.x).isCloseTo(221.0, offset)
                    assertThat(result.y).isCloseTo(35.0, offset)
                    assertThat(result.z).isCloseTo(33.0, offset)
                }
            }

            context("normalized vector") {
                it("should have the right coordinates") {
                    val result = vec.normalized()
                    assertThat(result.x).isCloseTo(0.0766964, offset)
                    assertThat(result.y).isCloseTo(0.3834824, offset)
                    assertThat(result.z).isCloseTo(-0.9203579, offset)
                }

                it("should have à length of 1.") {
                    assertThat(vec.normalized().length).isCloseTo(1.0, offset)
                }
            }

            context("joining two vectors") {
                it("should return a vector joining two positions") {
                    val origin = Position(1.0, 1.0, 1.0)
                    val target = Position(10.0, 10.0, 10.0 )
                    val result = origin toward target
                    assertThat(result.x).isCloseTo(9.0, offset)
                    assertThat(result.y).isCloseTo(9.0, offset)
                    assertThat(result.z).isCloseTo(9.0, offset)
                }
            }
        }
    }
})

