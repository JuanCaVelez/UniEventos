package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.Order
import com.unieventos.utils.RequestResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.tasks.await

class OrdersViewModel: ViewModel() {
    private val  db = Firebase.firestore

    private val _orders = MutableStateFlow(emptyList<Order>())
    val orders: StateFlow<List<Order> > = _orders.asStateFlow()

    private val _ordenResult = MutableStateFlow<RequestResult?>(null)
    val orderResult: StateFlow<RequestResult?> = _ordenResult.asStateFlow()

    private suspend fun getOrdersFireBase(): List<Order>{
        val snapshot = db.collection("orders")
            .get()
            .await()

        return snapshot.documents.mapNotNull {
            it.toObject(Order::class.java)?.apply {
                this.id = it.id
            }
        }
    }

    private suspend fun createOrderFireBase(order: Order){
        db.collection("events")
            .add(order)
            .await()
    }
}