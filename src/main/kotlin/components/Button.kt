package components

import appium.core.IMobileDriver
import componentInterfaces.IButton

class Button(driver: IMobileDriver, locatorType: String, locatorValue: String) :
    IButton, Element(driver, locatorType, locatorValue) {

    override fun submit() = try {
        getElement().submit()
    } catch (error: Exception) {
        throw Exception("Error submitting the button. ${error.message}")
    }
}