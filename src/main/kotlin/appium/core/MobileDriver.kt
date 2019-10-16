package appium.core

interface MobileDriver {
    fun installApp(appName: String)
    fun launchApp()
    fun closeApp()
    fun uninstallApp()
    fun swipeRight()
    fun swipeLeft()
    fun swipeUp()
    fun swipeDown()
}