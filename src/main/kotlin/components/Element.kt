package components

import appium.core.AndroidDriverProvider
import componentInterfaces.IElement
import componentInterfaces.Touchable
import constants.Id
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.ElementOption
import org.openqa.selenium.By

open class Element(private val locatorType: String, private val locatorValue: String) : IElement, Touchable {

    private val driver = AndroidDriverProvider.instance
    private var touchAction = AndroidTouchAction(driver)

    override fun getAttribute(attributeName: String): String? {
        try {
            return getElement().getAttribute(attributeName)
        } catch (error: Exception) {
            throw Exception("Error getting value of $attributeName. ${error.message}")
        }
    }

    override fun getTextValue(): String? {
        try {
            return getElement().text
        } catch (error: Exception) {
            throw Exception("Error getting text value. ${error.message}")
        }
    }

    override fun click() {
        try {
            getElement().click()
        } catch (error: Exception) {
            throw Exception("Error clicking the element. ${error.message}")
        }
    }

    override fun tap() {
        try {
            touchAction.tap(ElementOption.element(getElement())).perform()
        } catch (error: Exception) {
            throw Exception("Error tapping on element. ${error.message}")
        }
    }

    override fun longPress() {
        try {
            touchAction.longPress(ElementOption.element(getElement())).perform()
        } catch (error: Exception) {
            throw Exception("Error longPressing element. ${error.message}")
        }
    }

    protected fun getElement(): MobileElement {
        try {
            return driver.findElement(getLocator())
        } catch (error: Exception) {
            throw Exception("Error finding element with LocatorType: $locatorType and Value: $locatorValue")
        }
    }

    private fun getLocator(): By {
        when (locatorType) {
            Id -> return By.id(locatorValue)
            else -> throw Exception("$locatorType not a valid locator Type")
        }
    }

}