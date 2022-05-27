package co.edu.udistrital.espingsw.huellitaspelitos.data.service

import android.app.Activity
import android.os.AsyncTask
import android.util.Log
import co.edu.udistrital.espingsw.huellitaspelitos.ui.login.LoginViewModel
import me.pushy.sdk.Pushy

class RegisterForPushNotificationsAsync(val activity: Activity, private val loginViewModel: LoginViewModel) :
    AsyncTask<Void, Void, Any>() {

    override fun doInBackground(vararg params: Void): Any {
        return try {
            // Register the device for notifications
            val deviceToken = Pushy.register(activity)

            // Registration succeeded, log token to logcat
            Log.d("Pushy", "Pushy device token: $deviceToken")

            // Save token into database
            loginViewModel.setPushyToken(deviceToken)

            // Provide token to onPostExecute()
            deviceToken
        } catch (exc: Exception) {
            // Registration failed, provide exception to onPostExecute()
            exc
        }
    }

    override fun onPostExecute(result: Any) {
        var message: String

        // Registration failed?
        if (result is Exception) {
            // Log to console
            result.message?.let { Log.e("Pushy", it) }

            // Display error in alert
            message = result.message.toString()

            Log.e("Pushy", message, result)
        } else {
            // Registration success, result is device token
            Log.d("Pushy", "OnPostExecute -> OK")
        }
    }
}