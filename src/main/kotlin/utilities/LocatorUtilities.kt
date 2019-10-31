package utilities

import org.openqa.selenium.By

const val Id = "id"
const val Xpath = "xpath"
const val Text = "text"
const val ResourceId = "resourceId"
const val ContentDesc = "contentDesc"

object LocatorUtilities {
    fun getLocator(locatorType: String, locatorValue: String): By = when (locatorType) {
        Id -> By.id(locatorValue)
        Xpath -> By.xpath(locatorValue)
        Text -> By.xpath("//*[@text='$locatorValue']")
        ResourceId -> By.xpath("//*[@resource-id='$locatorValue']")
        ContentDesc -> By.xpath("//*[@content-desc='$locatorValue']")
        else -> throw Exception("$locatorType not a valid locator Type")
    }
}