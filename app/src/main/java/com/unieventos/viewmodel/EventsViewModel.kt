package com.unieventos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.unieventos.model.Event
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class EventsViewModel:ViewModel() {

    val db = Firebase.firestore
    private val _events = MutableStateFlow(emptyList<Event>())
    val events : StateFlow<List<Event>> = _events.asStateFlow()

    init {
        loadEvents()
    }

    fun loadEvents(){
        viewModelScope.launch {
            _events.value = getEventsList()
        }
    }

    private suspend fun getEventsList(): List<Event>{
        val snapshot = db.collection("events")
            .get()
            .await()

        return snapshot.documents.mapNotNull {
            val event = it.toObject(Event::class.java)
            requireNotNull(event)
            event.id = it.id
            event
        }
    }

    fun createEvent(event: Event){
        viewModelScope.launch {
            db.collection("events")
                .add(event)
                .await()

            _events.value = getEventsList()
        }
    }

    fun deleteEvent(eventId: String){
        viewModelScope.launch {
            db.collection("events")
                .document(eventId)
                .delete()
                .await()

            _events.value = getEventsList()
        }

    }

    fun updateEvent(event: Event){
        viewModelScope.launch{
            db.collection("events")
                .document(event.id)
                .set(event)
                .await()

            _events.value = getEventsList()
        }
    }

    suspend fun findById(id: String): Event?{
        val snapshot = db.collection("events")
            .document(id)
            .get()
            .await()

        val event = snapshot.toObject(Event::class.java)
        event?.id = snapshot.id
        return event
    }

    fun searchEvent(query: String): List<Event>{
        return _events.value.filter { event -> event.title.contains(query, ignoreCase = true) }
    }

    /* private fun getEventsList(): List<Event>{

        return listOf(
            Event(
                id = "12",
                title = "Concierto Ferxxo",
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
    }*/
}