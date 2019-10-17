package components

import utilities.Xpath

object ToastNotification {

    fun getMessage(): String? = try {
        val toastNotificationElement = Element(Xpath, "//android.widget.Toast")
        with(toastNotificationElement) { getTextValue()?.trim() }
    } catch (error: Exception) {
        throw Exception("Error getting toast notification. ${error.message}")
    }

}