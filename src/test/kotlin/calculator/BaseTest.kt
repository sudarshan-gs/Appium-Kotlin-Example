package calculator

import appium.core.EmulatorDevice
import appium.core.MobileDriver
import org.testng.ITestContext
import org.testng.annotations.DataProvider


open class BaseTest {

    @DataProvider(name = "Devices", parallel = true)
    fun deviceData(context: ITestContext): MutableIterator<Array<MobileDriver>> {
        val testData: ArrayList<Array<MobileDriver>> = arrayListOf()

        val appPackage = context.currentXmlTest.getParameter("appPackage")
        val appActivity = context.currentXmlTest.getParameter("appActivity")

        val pixel3Android28Emulator = EmulatorDevice("Pixel_3_API_28", appPackage, appActivity)
        val pixelAndroid27Emulator = EmulatorDevice("Pixel_API_27", appPackage, appActivity)

        testData.add(arrayOf(MobileDriver(pixel3Android28Emulator)))
        testData.add(arrayOf(MobileDriver(pixelAndroid27Emulator)))

        return testData.iterator()
    }

}