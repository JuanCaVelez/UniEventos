package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import com.unieventos.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class EventsViewModel:ViewModel() {
    private val _events = MutableStateFlow(emptyList<Event>())
    val events : StateFlow<List<Event>> = _events.asStateFlow()

    init {
        _events.value = getEventsList()
    }

    fun createEvent(event: Event){
        _events.value +=event
    }

    fun deleteEvent(event: Event){
        _events.value -=event
    }

    fun findById(id: String): Event?{
        return _events.value.find { it.id == id }
    }

    fun searchEvent(query: String): List<Event>{
        return _events.value.filter { it.title.contains(query, ignoreCase = true) }
    }

    private fun getEventsList(): List<Event>{

        return listOf(
            Event(
                id = "12",
                title = "Concierto FEID",
                city = "Medellin",
                address = "Cll 27N - 45",
                category = "Conciertos",
                description = "Concierto del Ferxxo",
                quantity = 100,
                price = 250000,
                date = "Mayo 02",
                imageUrl = "https://imagenes.eltiempo.com/files/image_1200_600/files/fp/uploads/2024/03/23/65ffb21030b10.r_d.722-303-0.jpeg",
            ),
            Event(
                id = "23",
                title = "Obra de teatro",
                city =  "Bogotá",
                address = "Cra 80 Av caracas",
                category = "Teatros",
                description = "Obra Vanguardista",
                quantity = 100,
                price = 60000,
                date = "Septiembre 15",
                imageUrl = "https://definicion.de/wp-content/uploads/2017/02/cuadro-teatral.jpg",
            ),
            Event(
                id = "50",
                title = "Once Caldas vs Deportivo Peréira",
                city = "Manizales",
                address = "Av Paralela Estadio Palogrande",
                category = "Deportes",
                description = "Clasico Cafetero",
                quantity = 250,
                price = 40000,
                date = "Octubre 23",
                imageUrl = "https://media.tycsports.com/files/2021/08/12/318926/once-caldas-recibira-a-pereira-por-la-fecha-5_862x485_wmk.jpg",
            )
        )
    }
}