package io.androweed.kray.core

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.data.Offset
import org.jetbrains.spek.api.Spek
import org.jetbrains.spek.api.dsl.describe
import org.jetbrains.spek.api.dsl.it
import org.jetbrains.spek.api.dsl.on

object QuadraticEquationSpec : Spek({
    val offset = Offset.offset(0.0000001)

    describe("a quadratic equation") {

        describe("with no solution") {

            val equ = QuadraticEquation(2.0, -1.0, 2.0)

            on("solving it") {
                it ("should return 0 as number of roots") {
                    assertThat(equ.hasNoRoot()).isTrue()
                    assertThat(equ.hasOneRoot()).isFalse()
                    assertThat(equ.hasTwoRoot()).isFalse()
                }
            }
        }

        describe("with only one solution (because a is 0)") {

            val equ = QuadraticEquation(0.0, 5.0, 2.0)

            on("solving it") {
                it ("should return 0 as number of roots") {
                    assertThat(equ.hasNoRoot()).isFalse()
                    assertThat(equ.hasOneRoot()).isTrue()
                    assertThat(equ.hasTwoRoot()).isFalse()

                    assertThat(equ.firstRoot()).isEqualTo(-5.0 / 2, offset)
                }
            }
        }

        describe("with only one solution (and a is not equal to 0)") {

            val equ = QuadraticEquation(1.0, 2.0, 1.0)

            on("solving it") {
                it ("should return 0 as number of roots") {
                    assertThat(equ.hasNoRoot()).isFalse()
                    assertThat(equ.hasOneRoot()).isTrue()
                    assertThat(equ.hasTwoRoot()).isFalse()

                    assertThat(equ.firstRoot()).isEqualTo(-1.0, offset)
                    assertThat(equ.secondRoot()).isEqualTo(-1.0, offset)
                }
            }
        }

        describe("with two solutions") {
            val equ = QuadraticEquation(2.0, 5.0, 2.0)

            it ("should return 2 as number of roots and roots should be correct") {
                assertThat(equ.hasNoRoot()).isFalse()
                assertThat(equ.hasOneRoot()).isFalse()
                assertThat(equ.hasTwoRoot()).isTrue()

                assertThat(equ.firstRoot()).isEqualTo(-2.0, offset)
                assertThat(equ.secondRoot()).isEqualTo(-0.5, offset)
            }

            it ("should avoid cancellation issues") {
                val withCancellationIssues = QuadraticEquation(1.0, 200.0, -0.000015)

                assertThat(withCancellationIssues.hasNoRoot()).isFalse()
                assertThat(withCancellationIssues.hasOneRoot()).isFalse()
                assertThat(withCancellationIssues.hasTwoRoot()).isTrue()

                assertThat(withCancellationIssues.firstRoot()).isEqualTo(-200.000000075, Offset.offset(0.000000000001))
                assertThat(withCancellationIssues.secondRoot()).isEqualTo(0.000000075, Offset.offset(0.000000000001))
            }
        }
    }
})

