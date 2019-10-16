package components

import componentInterfaces.IButton
import utilities.Id

class Button(locatorType: String = Id, locatorValue: String) :
    IButton, Element(locatorType, locatorValue) {

    override fun submit() = try {
        with(getElement()) { submit() }
    } catch (error: Exception) {
        throw Exception("Error submitting the button. ${error.message}")
    }
}