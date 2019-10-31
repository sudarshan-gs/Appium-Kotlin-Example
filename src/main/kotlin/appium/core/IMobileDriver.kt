package appium.core

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver

interface IMobileDriver {
    val instance: AndroidDriver<MobileElement>
    fun installApp(appName: String)
    fun launchApp()
    fun closeApp()
    fun uninstallApp()
    fun swipeRight()
    fun swipeLeft()
    fun swipeUp()
    fun swipeDown()
}