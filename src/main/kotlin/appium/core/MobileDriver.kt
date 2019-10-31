package appium.core

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import java.nio.file.Paths
import java.time.Duration
import kotlin.reflect.full.memberProperties

class MobileDriver(private val device: Any) : IMobileDriver {

    private var driver: AndroidDriver<MobileElement>? = null

    override val instance: AndroidDriver<MobileElement> by lazy {
        if (driver == null) {
            driver = AndroidDriverProvider(device).initializeDriver()
        }

        return@lazy driver ?: throw Exception("Couldn't get driver instance")
    }

    override fun launchApp() {
        try {
            instance.launchApp()
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

    override fun installApp(appName: String) = try {
        val file = Paths.get(System.getProperty("user.dir"), "app", "$appName.apk")
        instance.installApp(file.toString())
    } catch (error: Exception) {
        throw Exception("Error installing Application. ${error.message}")
    }

    override fun uninstallApp() {
        try {
            val bundleId: String =
                device::class.memberProperties.firstOrNull { member -> member.name == "appPackage" }.toString()
            instance.removeApp(bundleId)
        } catch (error: Exception) {
            throw Exception("Error installing Application. ${error.message}")
        }
    }


    override fun swipeRight() = try {
        val size = instance.manage().window().size
        val anchor = (size.height * 0.5).toInt()
        val startPoint = (size.width * 0.01).toInt()
        val endPoint = (size.width * 0.9).toInt()
        swipeHorizontal(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping right. ${error.message}")
    }

    override fun swipeLeft() = try {
        val size = instance.manage().window().size
        val anchor = (size.height * 0.5).toInt()
        val startPoint = (size.width * 0.9).toInt()
        val endPoint = (size.width * 0.01).toInt()
        swipeHorizontal(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping left. ${error.message}")
    }

    override fun swipeDown() = try {
        val size = instance.manage().window().size
        val anchor = (size.width * 0.5).toInt()
        val startPoint = (size.height * 0.2).toInt()
        val endPoint = (size.height * 0.8).toInt()
        swipeVertical(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping down. ${error.message}")
    }

    override fun swipeUp() = try {
        val size = instance.manage().window().size
        val anchor = (size.width * 0.5).toInt()
        val startPoint = (size.height * 0.8).toInt()
        val endPoint = (size.height * 0.2).toInt()

        swipeVertical(startPoint, endPoint, anchor)
    } catch (error: Exception) {
        throw Exception("Error swiping Up. ${error.message}")
    }

    private fun swipeHorizontal(startPoint: Int, endPoint: Int, anchor: Int) {
        try {
            var touchAction = AndroidTouchAction(instance)
            touchAction.press(PointOption.point(startPoint, anchor))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endPoint, anchor))
                .release()
                .perform()
        } catch (error: Exception) {
            throw Exception("Error Swiping Horizontal")
        }
    }

    private fun swipeVertical(startPoint: Int, endPoint: Int, anchor: Int) {
        try {
            var touchAction = AndroidTouchAction(instance)
            touchAction.press(PointOption.point(anchor, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(anchor, endPoint))
                .release()
                .perform()
        } catch (error: Exception) {
            throw Exception("Error swiping Vertical")
        }
    }
}