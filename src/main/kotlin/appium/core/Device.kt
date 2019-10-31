package appium.core

data class Device(val deviceName: String, val appPackage: String, val appActivity: String) {
    var autoLaunch: Boolean = false
    val automationName: String = "UiAutomator2"
    val platformName: String = "Android"
    val autoGrantPermissions: Boolean = true
}