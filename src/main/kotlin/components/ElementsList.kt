package components

import appium.core.AndroidDriverProvider
import componentInterfaces.IElement
import componentInterfaces.IElementsList
import utilities.LocatorUtilities
import utilities.Xpath

class ElementsList(private val locatorType: String, private var locatorValue: String) : IElementsList {

    private val driver = AndroidDriverProvider.getInstance()

    override fun getElements(): List<IElement> {
        return try {
            val elements = arrayListOf<Element>()
            val elementsCount = driver.findElements(LocatorUtilities.getLocator(locatorType, locatorValue)).size
            for (counter in 1..elementsCount) {
                elements.add(Element(Xpath, "($locatorValue)[$counter]"))
            }
            elements
        } catch (error: Exception) {
            throw Exception("Error locating elements with LocatorType: $locatorType and locatorValue: $locatorValue. ${error.message}")
        }
    }

    override fun getTextValues(): List<String?> {
        return try {
            val textValues = arrayListOf<String?>()
            val elements = getElements()
            elements.mapTo(textValues) { it.getTextValue()?.trim() }
            textValues
        } catch (error: Exception) {
            throw Exception("Error getting text values. ${error.message}")
        }
    }

    override fun getAttributeValues(attribute: String): List<String?> {
        return try {
            val attributeValues = arrayListOf<String?>()
            val elements = getElements()
            elements.mapTo(attributeValues) { it.getAttribute(attribute)?.trim() }
            attributeValues
        } catch (error: Exception) {
            throw Exception("Error getting attribute values. ${error.message}")
        }
    }

}