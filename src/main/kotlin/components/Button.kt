package components

import componentInterfaces.IButton
import constants.Id

class Button(private var locatorType: String = Id, private var locatorValue: String) :
    IButton, Element(locatorType, locatorValue) {
    override fun submit() {
        try {
            getElement().submit()
        } catch (error: Exception) {
            throw Exception("Error submitting the button. ${error.message}")
        }
    }
}