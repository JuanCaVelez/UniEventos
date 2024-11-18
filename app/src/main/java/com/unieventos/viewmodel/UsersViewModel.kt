package com.unieventos.viewmodel

import androidx.lifecycle.DEFAULT_ARGS_KEY
import  androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.User
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class UsersViewModel() : ViewModel() {

    private val auth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db = Firebase.firestore

    private val _authResult = MutableStateFlow<RequestResult?>(null)
    val authResult: StateFlow<RequestResult?> = _authResult.asStateFlow()

    private val _currentUser = MutableStateFlow<User?>(null)
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    fun createUser(user: User) {
        viewModelScope.launch {
            _authResult.value = RequestResult.Loading
            _authResult.value = kotlin.runCatching { createUserFirebase(user) }
                .fold(
                    onSuccess = {RequestResult.Success("Usuario creado")},
                    onFailure = {handleAuthError(it)}
                )
        }
    }

    private suspend fun createUserFirebase(user: User){
        val respuesta = auth.createUserWithEmailAndPassword(user.email, user.password).await()
        val userId = respuesta.user?.uid ?: throw Exception("No se creo el usuario")

        val userSave = user.copy(id = userId, password = "")

        db.collection("users")
            .document(userId)
            .set(userSave)
            .await()
    }

    fun deleteUser(userId: String){
        viewModelScope.launch {
            _authResult.value = RequestResult.Loading
            _authResult.value = kotlin.runCatching { deleteUserFirebase(userId) }
                .fold(
                    onSuccess = {RequestResult.Success("Usuario eliminado")},
                    onFailure = {handleAuthError(it)}
                )
        }
    }

    private suspend fun deleteUserFirebase(userId: String){
        db.collection("user")
            .document(userId)
            .delete()
            .await()
    }


    fun login(email: String, password: String){
        viewModelScope.launch {
            _authResult.value = RequestResult.Loading
            _authResult.value = kotlin.runCatching { loginFirebase(email, password) }
                .fold(
                    onSuccess = {RequestResult.Success("Logeo exitoso")},
                    onFailure = {handleAuthError(it)}
                )
        }
    }

    private suspend fun loginFirebase (email: String, password: String){
        val respuesta = auth.signInWithEmailAndPassword(email, password).await()
        val userId = respuesta.user?.uid ?: throw Exception("No se inició sesión")

        val user = getUserById(userId)
        _currentUser.value = user
    }

    private suspend fun getUserById(userId: String): User? {
        return db.collection("users")
            .document(userId)
            .get()
            .await()
            .toObject(User::class.java)
    }

    private fun handleAuthError(e: Throwable): RequestResult.Failure{
        val errorMessage = when(e){
            is FirebaseAuthException -> {
                when(e.errorCode){
                    "ERROR_INVALID_EMAIL" -> "El correo electronico tiene un formato incorrecto."
                    "ERROR_USER_NOT_FOUND" -> "No existe usuario vinculado a este correo."
                    "ERROR_WRONG_PASSWORD" -> "La contarseña es incorrecta."
                    "ERROR_EMAIL_ALREADY_IN_USE" -> "El correo ya se encuentra registrado."
                    else -> "Error de autenticacion: ${e.message}"
                }
            }
            else -> "Error desconocido: ${e.message}"
        }
        return RequestResult.Failure(errorMessage)
    }

    fun resetAuthResult(){
        _authResult.value = null
    }

    /*
    private fun getUserList(): List<User> {
        return listOf(
            User(
                id = "123",
                name = "Camilo",
                city = "Armenia",
                address = "Cra 13 # 16-19 apto 301",
                phone = "3045762396",
                role = Role.CLIENT,
                email = "camilov@email.com",
                password = "123456"
            ),
            User(
                id = "456",
                name = "Administrador",
                city = "Armenia",
                address = "Centro",
                phone = "777",
                role = Role.ADMIN,
                email = "admin@email.com",
                password = "654321"
            )
        )
    }
     */
}