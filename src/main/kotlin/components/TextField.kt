package components

import componentInterfaces.ITextField
import constants.Id

class TextField(private val locatorType: String = Id, private val locatorValue: String) :
    Element(locatorType, locatorValue), ITextField {
    override fun setText(value: String) {
        try {
            getElement().sendKeys()
        } catch (error: Exception) {
            throw Exception("Error setting text value. ${error.message}")
        }
    }
}