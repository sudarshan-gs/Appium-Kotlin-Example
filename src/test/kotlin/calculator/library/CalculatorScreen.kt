package calculator.library

import componentInterfaces.IButton
import componentInterfaces.IElementsList
import componentInterfaces.ITextField
import components.Button
import components.ElementsList
import components.TextField
import utilities.Xpath

class CalculatorScreen {
    private var digitSelector = "digit_"
    private val resultTextField: ITextField = TextField(locatorValue = "result")
    private val addButton: IButton = Button(locatorValue = "plus")
    private val minusButton: IButton = Button(locatorValue = "minus")
    private val equalsButton: IButton = Button(locatorValue = "equals")
    private val deleteButton: IButton = Button(Xpath, "//android.widget.Button[@text ='DEL' or @text ='CLR']")
    private val piButton: IButton = Button(locatorValue = "const_pi")
    private val historyTextViews: IElementsList =
        ElementsList(Xpath, "//*[contains(@resource-id, 'history_') and @class='android.widget.TextView']")

    fun addNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(locatorValue = digitSelector.replaceAfterLast("_", num.toString()))
            with(digitButton) { tap() }

            if (index != numbers.size - 1) {
                with(addButton) { tap() }
            }
        }

        with(equalsButton) { tap() }
        return this
    }

    fun subtractNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(locatorValue = digitSelector.replaceAfterLast("_", num.toString()))
            with(digitButton) { tap() }

            if (index != numbers.size - 1) {
                with(minusButton) { tap() }
            }
        }

        with(equalsButton) { tap() }
        return this
    }

    fun getResultText(): String? = with(resultTextField) { getTextValue() }

    fun clearResult(): CalculatorScreen {
        with(deleteButton) { longPress() }
        return this
    }

    fun inputDigits(vararg numbers: Int): CalculatorScreen {
        numbers
            .map { Button(locatorValue = digitSelector.replaceAfterLast("_", it.toString())) }
            .forEach {
                it.tap()
            }

        return this
    }

    fun getPieValue(): String? {
        with(piButton) { tap() }
        return getResultText()
    }

    fun getHistoryTextValues(): List<String?> = with(historyTextViews) { getTextValues() }
}