package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.unieventos.model.Role
import com.unieventos.model.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class UsersViewModel() : ViewModel() {

    private val _users = MutableStateFlow(emptyList<User>())
    private val _currentUser = MutableStateFlow<User?>(null)
    val users: StateFlow<List<User>> = _users.asStateFlow()
    val currentUser: StateFlow<User?> = _currentUser.asStateFlow()

    init {
        _users.value = getUserList()
    }

    fun loadCurrentUser(user: User){
        _currentUser.value = user
    }

    fun createUser(user: User) {
        _users.value += user
    }


    fun login(email: String, password: String): User? {
        return _users.value.find { it.email == email && it.password == password }
    }

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
}