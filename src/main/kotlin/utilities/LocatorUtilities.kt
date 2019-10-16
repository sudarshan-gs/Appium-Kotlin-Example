package utilities

import org.openqa.selenium.By

const val Id = "id"
const val Xpath = "xpath"
const val Text= "text"

object LocatorUtilities {
    fun getLocator(locatorType: String, locatorValue: String): By = when (locatorType) {
        Id -> By.id(locatorValue)
        Xpath -> By.xpath(locatorValue)
        Text -> By.xpath("//*[@text=$locatorValue]")
        else -> throw Exception("$locatorType not a valid locator Type")
    }
}