package components

import appium.core.IMobileDriver
import componentInterfaces.ITextField

class TextField(driver: IMobileDriver, locatorType: String, locatorValue: String) :
    Element(driver, locatorType, locatorValue), ITextField {

    override fun setText(value: String) {
        try {
            with(getElement()) { sendKeys(value) }
        } catch (error: Exception) {
            throw Exception("Error setting text value. ${error.message}")
        }
    }

}