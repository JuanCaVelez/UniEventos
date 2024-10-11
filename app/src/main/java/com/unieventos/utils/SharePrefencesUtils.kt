package com.unieventos.utils

import android.content.Context
import com.unieventos.model.Role
import com.unieventos.model.dto.UserDto

object SharePrefencesUtils {

    fun savePreferences(context: Context, idUser: String, rol: Role) {
        val sharedPreferences = context.getSharedPreferences("sesión usuario", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString("idUser", idUser)
        editor.putString("rol", rol.name)
        editor.apply()
    }

    fun clearPreferences(context: Context){
        val sharedPreferences = context.getSharedPreferences("sesión usuario", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }

    fun getCurrentUser(context: Context): UserDto?{

        val sharedPreferences = context.getSharedPreferences("sesión usuario",Context.MODE_PRIVATE)
        val idUser = sharedPreferences.getString("idUser","")
        val rol = sharedPreferences.getString("rol","")

        return if(idUser.isNullOrEmpty() || rol.isNullOrEmpty()){
            null
        }else{
            return UserDto(idUser, Role.valueOf(rol))
        }
    }

}