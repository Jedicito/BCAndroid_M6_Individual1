package chl.ancud.m6_individual1

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TareaDao {
    @Insert
    suspend fun insertarTarea(tarea: Tarea)

    @Query("Select * from tabla_tarea order by id asc")
    fun getTareas(): LiveData<List<Tarea>>

}