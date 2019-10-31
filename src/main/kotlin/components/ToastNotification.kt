package components

import appium.core.IMobileDriver
import utilities.Xpath

class ToastNotification(private val driver: IMobileDriver) {
    fun getMessage(): String? = try {
        val toastNotificationElement = Element(driver, Xpath, "//android.widget.Toast")
        with(toastNotificationElement) { getTextValue()?.trim() }
    } catch (error: Exception) {
        throw Exception("Error getting toast notification. ${error.message}")
    }
}