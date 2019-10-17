package components

import componentInterfaces.ITextField

class TextField(locatorType: String, locatorValue: String) :
    Element(locatorType, locatorValue), ITextField {

    override fun setText(value: String) = try {
        with(getElement()) { sendKeys(value) }
    } catch (error: Exception) {
        throw Exception("Error setting text value. ${error.message}")
    }

}