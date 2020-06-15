package ru.heathalphaapp.jdls.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "visitorsData")
data class VisitorModel (
    @PrimaryKey(autoGenerate = true)var id: Long? = null,
    val fio: String = "",
    val age: Int = 0,
    val section: String = "",
    val active: Boolean = false
)