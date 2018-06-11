package io.androweed.kray.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object VectorSpec : Spek({
    val offset = Offset.offset(0.000001)

    describe("a single vector") {

        val vec = Vector(1.0, 5.0, -12.0)

        it ("returns the coordinates") {
            assertThat(vec.x).isEqualTo(1.0)
            assertThat(vec.y).isEqualTo(5.0)
            assertThat(vec.z).isEqualTo(-12.0)
        }

        describe("vector operations") {
            on("addition") {
                val vec2 = Vector(2.0, 3.0, 5.0)
                it ("should return the resulting vector") {
                    val vec3 = vec + vec2
                    assertThat(vec3.x).isEqualTo(3.0, offset)
                    assertThat(vec3.y).isEqualTo(8.0, offset)
                    assertThat(vec3.z).isEqualTo(-7.0, offset)
                }
            }

            on("subtraction") {
                val vec2 = Vector(2.0, 3.0, 5.0)
                it ("should return the resulting vector") {
                    val vec3 = vec - vec2
                    assertThat(vec3.x).isEqualTo(-1.0, offset)
                    assertThat(vec3.y).isEqualTo(2.0, offset)
                    assertThat(vec3.z).isEqualTo(-17.0, offset)
                }
            }

            on("multiplication by a scalar") {
                it ("should return the resulting vector") {
                    val vec3 = vec * 2.0
                    assertThat(vec3.x).isEqualTo(2.0, offset)
                    assertThat(vec3.y).isEqualTo(10.0, offset)
                    assertThat(vec3.z).isEqualTo(-24.0, offset)
                }

                it ("should also be a commutative operation") {
                    val vec3 =  2.0 * vec
                    assertThat(vec3.x).isEqualTo(2.0, offset)
                    assertThat(vec3.y).isEqualTo(10.0, offset)
                    assertThat(vec3.z).isEqualTo(-24.0, offset)
                }
            }

            on("computing the opposite") {
                it("should return the opposite vector") {
                    val vec2 = -vec
                    assertThat(vec2.x).isEqualTo(-1.0, offset)
                    assertThat(vec2.y).isEqualTo(-5.0, offset)
                    assertThat(vec2.z).isEqualTo(12.0, offset)
                }
            }

            on("length") {
                it("should return the magnitude of the vector") {
                    assertThat(vec.length).isCloseTo(13.0384048, offset)
                }
            }

            on("squaredLength") {
                it("should return the squared magnitude of the vector") {
                    assertThat(vec.squaredLength).isCloseTo(170.0, offset)
                }
            }

            on("computing dot product") {
                it("should compute the result of the dot product with another vector") {
                    val other = Vector(-5.0, 8.0, 25.0)
                    assertThat(vec dot other).isCloseTo(-265.0, offset)
                }
            }

            on("computing cross product") {
                it("should compute the result of the  cross product with another vector") {
                    val other = Vector(-5.0, 8.0, 25.0)
                    val result = vec cross other
                    assertThat(result.x).isCloseTo(221.0, offset)
                    assertThat(result.y).isCloseTo(35.0, offset)
                    assertThat(result.z).isCloseTo(33.0, offset)
                }
            }

            on("normalized vector") {
                it("should have the right coordinates") {
                    val result = vec.normalize()
                    assertThat(result.x).isCloseTo(0.0766964, offset)
                    assertThat(result.y).isCloseTo(0.3834824, offset)
                    assertThat(result.z).isCloseTo(-0.9203579, offset)
                }

                it("should have à length of 1.") {
                    assertThat(vec.normalize().length).isCloseTo(1.0, offset)
                }
            }

            on("joining two vectors") {
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

