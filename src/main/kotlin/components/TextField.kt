package components

import componentInterfaces.ITextField
import utilities.Id

class TextField(locatorType: String = Id, locatorValue: String) :
    Element(locatorType, locatorValue), ITextField {

    override fun setText(value: String) = try {
        with(getElement()) { sendKeys() }
    } catch (error: Exception) {
        throw Exception("Error setting text value. ${error.message}")
    }

}