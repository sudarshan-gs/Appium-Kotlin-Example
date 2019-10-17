package testvagrant.tests

import appium.core.AndroidDriverProvider
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import testvagrant.library.LoginScreen

class LoginTests {

    private val loginScreen = LoginScreen()

    @Test
    fun validateLogin() {
        with(AndroidDriverProvider) { launchApp() }

        val successful = with(loginScreen) {
            login("foo", "password")
        }

        Assertions.assertTrue(successful)
    }

}