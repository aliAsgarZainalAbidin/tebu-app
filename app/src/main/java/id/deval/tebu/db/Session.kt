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
    val nama get() = pref.getString(NAMA, "")
    val wilayah get() = pref.getString(WILAYAH, "")
    val lokasi get() = pref.getString(LOKASI, "")
    val role get() = pref.getString(ROLE, "")
    val id get() = pref.getString(ID,"")

    init {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        editor = pref.edit()
    }

    fun createLoginSession(user: User) {
        editor.putString(KEY_TOKEN, user.token)
        editor.putString(ROLE, user.role)
        editor.putString(ID, user.id)
        editor.putString(NAMA, user.nama)
        editor.putString(WILAYAH, user.wilayah)
        editor.putString(LOKASI, user.lokasi)
        editor.commit()
    }

    fun logout() {
        editor.clear()
        editor.commit()
    }

    companion object {
        val PREF_NAME = "tebu-app"
        val KEY_TOKEN = "token"
        val NAMA = "nama"
        val WILAYAH = "wilayah"
        val LOKASI = "lokasi"
        val ROLE = "role"
        val ID = "id"
    }

}