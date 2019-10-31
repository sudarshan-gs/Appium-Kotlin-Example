package calculator.tests

import appium.core.IMobileDriver
import appium.core.MobileDriver
import calculator.BaseTest
import calculator.library.CalculatorScreen
import org.testng.annotations.Factory
import kotlin.test.*

class CalculatorTests {

    private var mobileDriver: IMobileDriver
    private var calculator: CalculatorScreen

    @Factory(dataProvider = "Devices", dataProviderClass = BaseTest::class)
    constructor(driver: MobileDriver) {
        mobileDriver = driver
        calculator = CalculatorScreen(mobileDriver)
    }

    @BeforeTest(alwaysRun = true)
    fun launchApp() = mobileDriver.launchApp()

    @Test(groups = ["calculator_tests"])
    fun validateAddingNumbers() {
        val result = calculator
            .addNumbers(1, 2, 3, 4, 5)
            .getResultText()

        assertEquals("15", result)
    }

    @Test(groups = ["calculator_tests"])
    fun validateSubtractingNumbers() {
        val result = calculator
            .subtractNumbers(9, 3, 4)
            .getResultText()

        assertEquals("2", result)
    }

    @Test(groups = ["calculator_tests"])
    fun validateClearingResult() {
        val result = calculator
            .inputDigits(1, 2, 3)
            .clearResult()
            .getResultText()

        assertTrue { result.isNullOrEmpty() }
    }

    @Test(groups = ["calculator_tests"])
    fun swipeAndTapPiAndValidateValue() {
        mobileDriver.swipeLeft()
        val pieValue = calculator.getPieValue()
        mobileDriver.swipeRight()
        assertTrue { pieValue!!.contains("3.14") }
    }

    @Test(groups = ["calculator_tests"])
    fun validateHistory() {
        val expectedValues = listOf("Today", "1+2+3+4+5", "15", "9−4−3−1", "1")

        calculator
            .addNumbers(1, 2, 3, 4, 5)
            .clearResult()
            .subtractNumbers(9, 4, 3, 1)

        mobileDriver.swipeDown()

        val historyTextValues = calculator.getHistoryTextValues()

        val differences = expectedValues.filterIndexed { counter, value ->
            historyTextValues[counter] != value
        }

        assertTrue { differences.isEmpty() }
    }

    @AfterTest(alwaysRun = true)
    fun closeApp() = mobileDriver.closeApp()
}