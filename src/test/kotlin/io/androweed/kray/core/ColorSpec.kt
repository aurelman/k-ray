import assertk.assertThat
import assertk.assertions.hasClass
import assertk.assertions.hasMessage
import assertk.assertions.isCloseTo
import io.androweed.kray.core.Color
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe
import java.lang.IllegalArgumentException


object ColorSpec : Spek({
    describe("a color instance") {

        context("creating a new instance") {

            it("should not raise error when component value are 1.0 or 0.0") {
                assertThat {
                    Color(0.0f, 1.0f, 0.0f)
                }.doesNotThrowAnyException()
            }

            it("should raise error if any of the component is negative") {
                assertThat {
                    Color(-1.0f, -1.0f, -1.0f)
                }.thrownError {
                    hasClass(IllegalArgumentException::class)
                    hasMessage("component value must be in [0.0, 1.0]")
                }
            }

            it("should raise error if any of the component is greater than 1.0") {
                assertThat {
                    Color(2.0f, 2.0f, 2.0f)
                }.thrownError {
                    hasClass(IllegalArgumentException::class)
                    hasMessage("component value must be in [0.0, 1.0]")
                }
            }
        }

        context("when added to another vector") {
            val color by memoized { Color(0.5f, 0.5f, 0.5f) }

            it("should return the resulting vector") {
                val result = color + Color(0.3f, 0.3f, 0.3f)

                assertThat(result.red).isCloseTo(0.8f, 0.0000001f)
                assertThat(result.green).isCloseTo(0.8f, 0.0000001f)
                assertThat(result.blue).isCloseTo(0.8f, 0.0000001f)
            }

            it("should cap component values when resulting components would be higher than 1") {
                val result = color + Color(0.8f, 0.8f, 0.8f)

                assertThat(result.red).isCloseTo(1f, 0.0000001f)
                assertThat(result.green).isCloseTo(1f, 0.0000001f)
                assertThat(result.blue).isCloseTo(1f, 0.0000001f)
            }
        }
    }
})
