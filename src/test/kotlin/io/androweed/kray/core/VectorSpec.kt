package io.androweed.kray.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on


object VectorSpec : Spek({
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
                    assertThat(vec3.x).isEqualTo(3.0, Offset.offset(0.000001))
                    assertThat(vec3.y).isEqualTo(8.0, Offset.offset(0.000001))
                    assertThat(vec3.z).isEqualTo(-7.0, Offset.offset(0.000001))
                }
            }

            on("subtraction") {
                val vec2 = Vector(2.0, 3.0, 5.0)
                it ("should return the resulting vector") {
                    val vec3 = vec - vec2
                    assertThat(vec3.x).isEqualTo(-1.0, Offset.offset(0.000001))
                    assertThat(vec3.y).isEqualTo(2.0, Offset.offset(0.000001))
                    assertThat(vec3.z).isEqualTo(-17.0, Offset.offset(0.000001))
                }
            }

            on("multiplication by a scalar") {
                it ("should return the resulting vector") {
                    val vec3 = vec * 2.0
                    assertThat(vec3.x).isEqualTo(2.0, Offset.offset(0.000001))
                    assertThat(vec3.y).isEqualTo(10.0, Offset.offset(0.000001))
                    assertThat(vec3.z).isEqualTo(-24.0, Offset.offset(0.000001))
                }

                it ("should also be a commutative operation") {
                    val vec3 =  2.0 * vec
                    assertThat(vec3.x).isEqualTo(2.0, Offset.offset(0.000001))
                    assertThat(vec3.y).isEqualTo(10.0, Offset.offset(0.000001))
                    assertThat(vec3.z).isEqualTo(-24.0, Offset.offset(0.000001))
                }
            }

            on("computing the opposite") {
                it("returns the opposite vector") {
                    val vec2 = -vec
                    assertThat(vec2.x).isEqualTo(-1.0, Offset.offset(0.000001))
                    assertThat(vec2.y).isEqualTo(-5.0, Offset.offset(0.000001))
                    assertThat(vec2.z).isEqualTo(12.0, Offset.offset(0.000001))
                }
            }
        }
    }
})

