package appium.core

import filereader.PropertiesReader
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL

object AndroidDriverProvider : MobileDriver {
    val instance: AndroidDriver<MobileElement>;
    private val properties = PropertiesReader("androidConfig")

    init {
        instance = initializeDriver()
    }

    override fun launchApp() {
        try {
            AndroidDriverProvider.instance.launchApp()
        } catch (error: Exception) {
            throw Exception("Error launching app")
        }
    }


    override fun closeApp() {
        try {
            instance.closeApp()
        } catch (error: Exception) {
            throw Exception("Error launching app")
        }
    }

    private fun initializeDriver(): AndroidDriver<MobileElement> {
        try {
            val serverAddress = "http://127.0.0.1:4723/wd/hub"
            var capabilities = DesiredCapabilities()
            properties.getAllPropertiesWithValues().forEach { (k, v) ->
                capabilities.setCapability(k as String, v as String)
            }
            return AndroidDriver(URL(serverAddress), capabilities)
        } catch (error: Exception) {
            throw Exception("Error initializing Android Driver. ${error.message}")
        }
    }

}