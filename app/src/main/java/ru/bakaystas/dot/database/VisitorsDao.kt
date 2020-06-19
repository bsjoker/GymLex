package ru.bakaystas.dot.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.bakaystas.dot.models.VisitorModel

@Dao
interface VisitorsDao {
    @Query("SELECT * FROM visitorsData")
    fun getAllVisitors(): LiveData<List<VisitorModel>>

    @Query("SELECT * FROM visitorsData WHERE section LIKE :section")
    fun getVisitorsSection(section:String): LiveData<List<VisitorModel>>

    @Query("SELECT * FROM visitorsData WHERE active LIKE :isActive")
    fun getActiveVisitors(isActive:Boolean): LiveData<List<VisitorModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVisitor(visitorModel: VisitorModel)
}