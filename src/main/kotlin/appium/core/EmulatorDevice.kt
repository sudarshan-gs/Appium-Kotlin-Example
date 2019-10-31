package appium.core

data class EmulatorDevice(val avd: String, val appPackage: String, val appActivity: String) {
    var autoLaunch: Boolean = false
    val deviceName: String = "Android Emulator"
    val automationName: String = "UiAutomator2"
    val platformName: String = "Android"
    val autoGrantPermissions: Boolean = true
}