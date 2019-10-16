package appium.core

import filereader.PropertiesReader
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.remote.MobileCapabilityType
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.nio.file.Paths
import java.time.Duration
import org.openqa.selenium.interactions.touch.TouchActions
import javax.swing.Spring.height


object AndroidDriverProvider : MobileDriver {
    private var instance: AndroidDriver<MobileElement>? = null
    private val properties = PropertiesReader("androidConfig")
    private var touchAction: AndroidTouchAction? = null

    init {
        if (instance == null) {
            instance = initializeDriver()
        }
        touchAction = AndroidTouchAction(getInstance())
    }

    fun getInstance(): AndroidDriver<MobileElement> {
        return instance ?: throw Exception("Error getting driver instance. Driver is null")
    }

    override fun launchApp() {
        try {
            getInstance().launchApp()
        } catch (error: Exception) {
            throw Exception("Error launching app")
        }
    }

    override fun closeApp() {
        try {
            getInstance().closeApp()
        } catch (error: Exception) {
            throw Exception("Error launching app")
        }
    }

    override fun installApp(appName: String) = try {
        val file = Paths.get(System.getProperty("user.dir"), "app", "$appName.apk")
        with(getInstance()) {
            installApp(file.toString())
        }
    } catch (error: Exception) {
        throw Exception("Error installing Application. ${error.message}")
    }

    override fun uninstallApp(): Unit = try {
        val bundleId = properties.getValue("appPackage")
        with(getInstance()) {
            removeApp(bundleId)
        }
    } catch (error: Exception) {
        throw Exception("Error installing Application. ${error.message}")
    }


    override fun swipeRight() = try {
        val size = getInstance().manage().window().size;
        val anchor = (size.height * 0.5).toInt()
        val startPoint = (size.width * 0.01).toInt()
        val endPoint = (size.width * 0.9).toInt()
        swipeHorizontal(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping right. ${error.message}")
    }

    override fun swipeLeft() = try {
        val size = getInstance().manage().window().size;
        val anchor = (size.height * 0.5).toInt()
        val startPoint = (size.width * 0.9).toInt()
        val endPoint = (size.width * 0.01).toInt()
        swipeHorizontal(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping left. ${error.message}")
    }

    override fun swipeDown() = try {
        val size = getInstance().manage().window().size;
        val anchor = (size.width * 0.5).toInt()
        val startPoint = (size.height * 0.2).toInt()
        val endPoint = (size.height * 0.8).toInt()
        swipeVertical(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping down. ${error.message}")
    }

    override fun swipeUp() = try {
        val size = getInstance().manage().window().size;
        val anchor = (size.width * 0.5).toInt()
        val startPoint = (size.height * 0.8).toInt()
        val endPoint = (size.height * 0.2).toInt()

        swipeVertical(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping Up. ${error.message}")
    }

    private fun swipeHorizontal(startPoint: Int, endPoint: Int, anchor: Int) {
        touchAction!!.run {
            press(PointOption.point(startPoint, anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release()
                .perform()
        }
    }

    private fun swipeVertical(startPoint: Int, endPoint: Int, anchor: Int) {
        touchAction!!.run {
            press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release()
                .perform()
        }
    }

    private fun initializeDriver(): AndroidDriver<MobileElement> {
        return try {
            val serverAddress = "http://127.0.0.1:4723/wd/hub"

            val capabilities = DesiredCapabilities()
            val configProperties = properties.getAllPropertiesWithValues().toMutableMap()

            configProperties.forEach { (k, v) ->

                with(capabilities) {
                    if (k == "app") {
                        val path =
                            Paths.get(
                                System.getProperty("user.dir"),
                                "app", "${with(configProperties) { get("app").toString() }}.apk"
                            )
                        configProperties[k] = path.toString()
                    }

                    setCapability(
                        k as String,
                        v as String
                    )
                }
            }

            AndroidDriver(URL(serverAddress), capabilities)
        } catch (error: Exception) {
            throw Exception("Error initializing Android Driver. ${error.message}")
        }
    }

}