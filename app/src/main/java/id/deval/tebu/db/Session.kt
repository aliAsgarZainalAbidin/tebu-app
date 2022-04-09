package id.deval.tebu.db

import android.content.Context
import android.content.SharedPreferences
import dagger.hilt.android.qualifiers.ApplicationContext
import id.deval.tebu.db.response.User


class Session(
    context: Context
) {

    var pref: SharedPreferences
    var editor: SharedPreferences.Editor

    val token get() = pref.getString(KEY_TOKEN, "")
    val role get() = pref.getString(ROLE, "")
    val id get() = pref.getInt(ID,0)

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun createLoginSession(user: User) {
        editor.putString(KEY_TOKEN, user.token)
        editor.putString(ROLE, user.role)
        editor.putInt(ID, user.id)
        editor.commit()
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    companion object {
        val PREF_NAME = "tebu-app"
        val KEY_TOKEN = "token"
        val ROLE = "role"
        val ID = "id"
    }

}