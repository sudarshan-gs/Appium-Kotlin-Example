package components

import appium.core.AndroidDriverProvider
import componentInterfaces.IElement
import componentInterfaces.ITouchable
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.offset.ElementOption.element
import utilities.LocatorUtilities.getLocator

open class Element(private val locatorType: String, private val locatorValue: String) : IElement, ITouchable {

    private val driver = AndroidDriverProvider.getInstance()
    private var touchAction = AndroidTouchAction(driver)

    override fun getAttribute(attributeName: String): String? {
        return try {
            with(getElement()) { getAttribute(attributeName) }
        } catch (error: Exception) {
            throw Exception("Error getting value of $attributeName. ${error.message}")
        }
    }

    override fun getTextValue(): String? {
        return try {
            with(getElement()) { text }
        } catch (error: Exception) {
            throw Exception("Error getting text value. ${error.message}")
        }
    }

    override fun click() = try {
        with(getElement()) { click() }
    } catch (error: Exception) {
        throw Exception("Error clicking the element. ${error.message}")
    }

    override fun tap(): Unit =
        try {
            with(touchAction) {
                val elementToBeTapped = element(getElement())
                return@with with(
                    tap(elementToBeTapped),
                    { perform() }
                )
            }

        } catch (error: Exception) {
            throw Exception("Error tapping on element. ${error.message}")
        }


    override fun longPress(): Unit =
        try {
            with(touchAction) {
                val elementToBeLongPressed = element(getElement())
                return@with with(
                    longPress(elementToBeLongPressed), { perform() }
                )
            }
        } catch (error: Exception) {
            throw Exception("Error longPressing element. ${error.message}")
        }


    protected fun getElement(): MobileElement {
        return try {
            with(driver) {
                val locator = getLocator(locatorType, locatorValue)
                return@with findElement(locator)
            }
        } catch (error: Exception) {
            throw Exception("Error finding element with LocatorType: $locatorType and Value: $locatorValue. ${error.message}")
        }
    }
}