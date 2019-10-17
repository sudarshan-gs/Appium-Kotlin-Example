package components

import componentInterfaces.IButton

class Button(locatorType: String, locatorValue: String) :
    IButton, Element(locatorType, locatorValue) {

    override fun submit() = try {
        with(getElement()) { submit() }
    } catch (error: Exception) {
        throw Exception("Error submitting the button. ${error.message}")
    }
}