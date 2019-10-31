package calculator.library

import appium.core.IMobileDriver
import componentInterfaces.IButton
import componentInterfaces.IElementsList
import componentInterfaces.ITextField
import components.Button
import components.ElementsList
import components.TextField
import utilities.ContentDesc
import utilities.Id
import utilities.Xpath

class CalculatorScreen(private var driver: IMobileDriver) {

    private val resultTextField: ITextField = TextField(driver, Id, "result")
    private val addButton: IButton = Button(driver, ContentDesc, "plus")
    private val minusButton: IButton = Button(driver, ContentDesc, "minus")
    private val equalsButton: IButton = Button(driver, ContentDesc, "equals")
    private val deleteButton: IButton = Button(driver, Xpath, "//android.widget.Button[@text ='DEL' or @text ='CLR']")
    private val piButton: IButton = Button(driver, Id, "const_pi")
    private val historyTextViews: IElementsList =
        ElementsList(driver, Xpath, "//*[contains(@resource-id, 'history_') and @class='android.widget.TextView']")
    private var digitSelector = "digit_"

    fun addNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(driver, Id, digitSelector.replaceAfterLast("_", num.toString()))
            digitButton.tap()

            if (index != numbers.size - 1) {
                addButton.tap()
            }
        }

        equalsButton.tap()
        return this
    }

    fun subtractNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(driver, Id, digitSelector.replaceAfterLast("_", num.toString()))
            digitButton.tap()

            if (index != numbers.size - 1) {
                minusButton.tap()
            }
        }

        equalsButton.tap()
        return this
    }

    fun getResultText(): String? = resultTextField.getTextValue()

    fun clearResult(): CalculatorScreen {
        deleteButton.longPress()
        return this
    }

    fun inputDigits(vararg numbers: Int): CalculatorScreen {
        numbers
            .map { Button(driver, Id, digitSelector.replaceAfterLast("_", it.toString())) }
            .forEach { it.tap() }

        return this
    }

    fun getPieValue(): String? {
        piButton.tap()
        return getResultText()
    }

    fun getHistoryTextValues(): List<String?> = historyTextViews.getTextValues()
}