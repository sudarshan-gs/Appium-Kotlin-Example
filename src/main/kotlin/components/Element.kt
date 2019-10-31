package components

import appium.core.IMobileDriver
import componentInterfaces.IElement
import componentInterfaces.ITouchable
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.ElementOption.element
import utilities.LocatorUtilities.getLocator

open class Element(
    private val driver: IMobileDriver,
    private val locatorType: String,
    private val locatorValue: String
) : IElement, ITouchable {

    private var touchAction = AndroidTouchAction(driver.instance)

    override fun getAttribute(attributeName: String): String? {
        return try {
            getElement().getAttribute(attributeName)
        } catch (error: Exception) {
            throw Exception("Error getting value of $attributeName. ${error.message}")
        }
    }

    override fun getTextValue(): String? {
        return try {
            getElement().text
        } catch (error: Exception) {
            throw Exception("Error getting text value. ${error.message}")
        }
    }

    override fun click() = try {
        getElement().click()
    } catch (error: Exception) {
        throw Exception("Error clicking the element. ${error.message}")
    }

    override fun tap() {
        try {
            touchAction.tap(element(getElement())).perform()
        } catch (error: Exception) {
            throw Exception("Error tapping on element. ${error.message}")
        }
    }

    override fun longPress() {
        try {
            touchAction.longPress(element(getElement())).perform()
        } catch (error: Exception) {
            throw Exception("Error longPressing element. ${error.message}")
        }
    }

    protected fun getElement(): MobileElement {
        return try {
            driver.instance.findElement(getLocator(locatorType, locatorValue))
        } catch (error: Exception) {
            throw Exception("Error finding element with LocatorType: $locatorType and Value: $locatorValue. ${error.message}")
        }
    }
}