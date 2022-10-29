package com.example.mobilesecuritynotes.utils

import android.content.Context
import android.widget.Toast

fun showToastCurring(duration: Int): (Context, CharSequence) -> Unit {
    return fun (context: Context, message: CharSequence) {
        Toast.makeText(
            context,
            message,
            duration
        ).show()
    }
}

fun showShortToast(context: Context, message: CharSequence) =
    showToastCurring(Toast.LENGTH_SHORT)(context, message)
fun showLongToast(context: Context, message: CharSequence) =
    showToastCurring(Toast.LENGTH_LONG)(context, message)

object ToastMessages {
    val NO_PASSWORD_SET = "You don't have a password yet. Change Password:)"
    val WRONG_PASSWORD = "Wrong password! Try again :)"
    val SUCCESSFUL_PASSWORD_CHANGE = "Your password was successfully changed:)"
    val REPEAT_PASSWORD_DOESNT_CORRESPEND = " Your new password and repeat password don't match :("
}
