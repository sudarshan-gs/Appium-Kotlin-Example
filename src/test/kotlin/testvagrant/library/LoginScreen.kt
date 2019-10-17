package testvagrant.library

import componentInterfaces.IButton
import componentInterfaces.ITextField
import components.Button
import components.ToastNotification
import utilities.Id

class LoginScreen {

    private val userNameTextField: ITextField = components.TextField(Id, "username_text")
    private val passwordTextField: ITextField = components.TextField(Id, "password_text")
    private val loginButton: IButton = Button(Id, "login_btn")

    fun login(userName: String, password: String): Boolean {
        with(userNameTextField) { setText(userName) }
        with(passwordTextField) { setText(password) }
        with(loginButton) { click() }

        return ToastNotification.getMessage() == "Login Successful."
    }

}