package calculator.tests

import appium.core.AndroidDriverProvider
import calculator.library.CalculatorScreen
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CalculatorTests {
    private val calculator = CalculatorScreen()

    @BeforeEach
    fun launchApp() = with(AndroidDriverProvider) { launchApp() }

    @Test
    fun validateAddingNumbers() {
        val result = with(calculator) {
            with(addNumbers(1, 2, 3, 4, 5)) { getResultText() }
        }

        Assertions.assertEquals("15", result)
    }

    @Test
    fun validateSubtractingNumbers() {
        val result = with(calculator) {
            with(subtractNumbers(9, 3, 4)) { getResultText() }
        }

        Assertions.assertEquals("2", result)
    }

    @Test
    fun validateClearingResult() {
        val result = with(calculator) {
            inputDigits(1, 2, 3)
                .clearResult()
                .getResultText()
        }

        Assertions.assertTrue(result.isNullOrEmpty())
    }

    @Test
    fun swipeAndTapPiAndValidateValue() {
        with(AndroidDriverProvider) { swipeLeft() }
        val pieValue = with(calculator) { getPieValue() }
        with(AndroidDriverProvider) { swipeRight() }
        Assertions.assertTrue(pieValue == "3.1415926535897")
    }

    @Test
    fun validateHistory() {
        val expectedValues = listOf("Today", "1+2+3+4+5", "15", "9−4−3−1", "1")

        with(calculator) {
            addNumbers(1, 2, 3, 4, 5)
                .clearResult()
                .subtractNumbers(9, 4, 3, 1)
        }

        with(AndroidDriverProvider) { swipeDown() }

        val historyTextValues = with(calculator) { getHistoryTextValues() }

        val differences = expectedValues.filterIndexed { counter, value ->
            historyTextValues[counter] != value
        }

        Assertions.assertTrue(differences.isEmpty())
    }

    @AfterEach
    fun closeApp() = with(AndroidDriverProvider) { closeApp() }
}