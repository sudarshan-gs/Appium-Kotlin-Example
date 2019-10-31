package appium.core

import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.remote.DesiredCapabilities
import java.net.URL
import java.nio.file.Paths
import kotlin.reflect.full.memberProperties


class AndroidDriverProvider(private var deviceInfo: Any) {

    internal fun initializeDriver(): AndroidDriver<MobileElement> {
        return try {
            val serverAddress = "http://127.0.0.1:4723/wd/hub"

            val capabilities = DesiredCapabilities()

            deviceInfo::class.memberProperties.forEach {
                val capability: String = it.name
                val capabilityValue: String = it.getter.call(deviceInfo).toString()

                if (capability == "app") {
                    val path =
                        Paths.get(
                            System.getProperty("user.dir"),
                            "app", "${deviceInfo}}.apk"
                        )

                    capabilities.setCapability(capability, path.toString())
                } else {
                    capabilities.setCapability(capability, capabilityValue)
                }
            }

            AndroidDriver(URL(serverAddress), capabilities)
        } catch (error: Exception) {
            throw Exception("Error initializing Android Driver. ${error.message}")
        }
    }

}