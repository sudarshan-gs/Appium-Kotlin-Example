package calculator.tests

import appium.core.AndroidDriverProvider
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import calculator.library.CalculatorScreen
import org.junit.jupiter.api.Assertions

class CalculatorTests {
    private val calculator = CalculatorScreen()

    @BeforeEach
    fun launchApp() {
        AndroidDriverProvider.launchApp()
    }

    @Test
    fun validateAddingNumbers() {
        val result = calculator
            .addNumbers(1, 2, 3, 4, 5)
            .getResultText()

        Assertions.assertEquals("15", result)
    }

    @Test
    fun validateSubtractingNumbers() {
        val result = calculator
            .subtractNumbers(9, 3, 4)
            .getResultText()

        Assertions.assertEquals("2", result)
    }

    @Test
    fun validateClearingResult() {
        val result = calculator
            .inputDigits(1, 2, 3)
            .clearResult()
            .getResultText()

        Assertions.assertTrue(result.isNullOrEmpty())
    }

    @AfterEach
    fun closeApp() {
        AndroidDriverProvider.closeApp()
    }
}