package componentInterfaces

interface IElementsList {

    fun getElements(): List<IElement>
    fun getTextValues(): List<String?>
    fun getAttributeValues(attribute: String): List<String?>

}