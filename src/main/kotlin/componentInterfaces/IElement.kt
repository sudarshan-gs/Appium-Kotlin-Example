package componentInterfaces

interface IElement {
    fun getAttribute(attributeName: String): String?
    fun getTextValue(): String?
    fun click()
}