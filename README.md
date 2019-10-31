# Appium-Kotlin-Example

Android Mobile App testing with Appium using Kotlin + Gradle + Testng

Project Structure:
===================
1. main/kotlin/appium.core -  Abstraction over Appium ANdroid Driver
2. main/kotlin/componentInterfaces -  contract for UI controls
3. main/kotlin/components - Abstraction over UI controls
4. main/java/utilities - Locator utility and Locator constants

5. test/kotlin/calculator/library - Page objects corresponding the app screens - PageObjectModel
6. test/kotlin/calculator/tests - Calculator App Tests
7. test/kotlin/BaseTest - TestNg Dataprovider to run tests on connceted devices. Create AVDS/Connect real Devices
   and create instance of the AVD/Real device using EmulatorDevice or Device respectively
   
Steps to Run tests:
===================
./gradlew clean runTests
   
