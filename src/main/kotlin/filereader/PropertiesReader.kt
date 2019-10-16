package filereader

import java.io.FileInputStream
import java.nio.file.Paths
import java.util.*

class PropertiesReader(private val fileName: String) {
    private val properties: Properties = Properties()

    init {
        initialize()
    }

    fun getAllPropertiesWithValues(): Map<Any, Any> {
        return properties.toMap()
    }

    fun getValue(propertyName: String): String {
        return properties.getProperty(propertyName)
    }

    private fun initialize() {
        val file = Paths.get(System.getProperty("user.dir"), "src/main/resources/$fileName.properties").toString()
        val inputStream = FileInputStream(file)
        properties.load(inputStream)
    }

}