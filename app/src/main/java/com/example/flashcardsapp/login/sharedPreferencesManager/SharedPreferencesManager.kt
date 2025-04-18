package com.example.flashcardsapp.login.sharedPreferencesManager

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesManager(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("AppPrefs", Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = sharedPreferences.edit()

    fun saveUsername(username: String) {
        editor.putString("username", username)
        editor.apply()
    }

    fun getUsername(): String? {
        return sharedPreferences.getString("username", null)
    }

    fun saveMaterias(materias: Set<String>) {
        editor.putStringSet("materias", materias)
        editor.apply()
    }

    fun getMaterias(): Set<String>? {
        return sharedPreferences.getStringSet("materias", null)
    }

    fun clearAll() {
        editor.clear()
        editor.apply()
    }
}
