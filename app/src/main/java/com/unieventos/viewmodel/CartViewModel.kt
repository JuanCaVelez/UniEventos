package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.unieventos.model.CartItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

    class   CartViewModel: ViewModel() {

        private val _events = MutableStateFlow( emptyList<CartItem>() )
        val events: StateFlow< List<CartItem> > = _events.asStateFlow()

        init{
            _events.value = addItems()
        }
        fun addToCart(event: CartItem){
            _events.value += event
        }

        fun removeFromCart(eventId: String){
            _events.value = _events.value.toMutableList().apply {
                removeIf { event -> event.id == eventId }
            }
        }

        fun updateEvent(event: CartItem){
            _events.value = _events.value.toMutableList().apply {
                removeIf { it.id == event.id }
                add(event)
            }
        }

        fun getItems(): List<CartItem>{
            return _events.value
        }

        fun countItems(): Int{
            return _events.value.size
        }

        /*
        fun totalPrice(): Float{
            return _events.value.sumOf {  }
        }
         */

        fun clearCart(){
            _events.value = emptyList()
        }

        fun addItems(): List<CartItem>{
            return emptyList()
        }
    }