package chl.ancud.m6_individual1

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface TareaDao {
    @Insert
    suspend fun insertarTarea(tarea: Tarea)
}