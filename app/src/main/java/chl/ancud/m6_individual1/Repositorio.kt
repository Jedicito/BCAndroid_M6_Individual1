package chl.ancud.m6_individual1

import androidx.lifecycle.LiveData

class Repositorio(private val tareaDao: TareaDao) {
    suspend fun insertTarea(tarea: Tarea) {
        tareaDao.insertarTarea(tarea)
    }

    fun listTareas(): LiveData<List<Tarea>> {
        return tareaDao.getTareas()
    }
}