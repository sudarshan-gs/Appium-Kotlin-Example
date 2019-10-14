package calculator.library

import componentInterfaces.IButton
import componentInterfaces.ITextField
import components.Button
import components.TextField

class CalculatorScreen {
    private var digitSelector = "digit_"
    private val resultTextField: ITextField = TextField(locatorValue = "result")
    private val addButton: IButton = Button(locatorValue = "plus")
    private val minusButton: IButton = Button(locatorValue = "minus")
    private val deleteButton: IButton = Button(locatorValue = "del")

    fun addNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(locatorValue = digitSelector.replaceAfterLast("_", num.toString()))
            digitButton.tap()

            if (index != numbers.size - 1) {
                addButton.tap()
            }
        }

        return this
    }

    fun subtractNumbers(vararg numbers: Int): CalculatorScreen {
        numbers.forEachIndexed { index, num ->
            val digitButton = Button(locatorValue = digitSelector.replaceAfterLast("_", num.toString()))
            digitButton.tap()

            if (index != numbers.size - 1) {
                minusButton.tap()
            }
        }

        return this
    }

    fun getResultText(): String? {
        return resultTextField.getTextValue()
    }

    fun clearResult(): CalculatorScreen {
        deleteButton.longPress()
        return this
    }

    fun inputDigits(vararg numbers: Int): CalculatorScreen {
        numbers.forEach { num ->
            val digitButton = Button(locatorValue = digitSelector.replaceAfterLast("_", num.toString()))
            digitButton.tap()
        }

        return this
    }
}