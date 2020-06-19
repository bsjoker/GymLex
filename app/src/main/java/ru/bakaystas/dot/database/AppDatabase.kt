package ru.bakaystas.dot.database

import android.annotation.SuppressLint
import android.content.Context
import android.os.AsyncTask
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.bakaystas.dot.models.VisitorModel

@Database(entities = [VisitorModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun visitorsDao(): VisitorsDao

//    private class ScoreDatabaseCallback(
//        private val scope: CoroutineScope
//    ) : RoomDatabase.Callback() {
//
//        override fun onOpen(db: SupportSQLiteDatabase) {
//            super.onOpen(db)
//            INSTANCE?.let { database ->
//                scope.launch(Dispatchers.IO) {
//                    var visitorsDao = database.visitorsDao()
//                }
//            }
//        }
//    }

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, "gymDB").build()
        }

        @SuppressLint("StaticFieldLeak")
        fun insertData(mydata: AppDatabase, visitor: VisitorModel) {
            object : AsyncTask<Void, Void, Void>() {
                override fun doInBackground(vararg voids: Void): Void? {
                    mydata.visitorsDao().addVisitor(visitor)
                    return null
                }
            }.execute()
        }

//        fun getAppDataBase(context: Context
//        ): AppDatabase {
//            val tempInstance = INSTANCE
//            if (tempInstance != null) {
//                return tempInstance
//            }
//            synchronized(AppDatabase::class) {
//                val instance = Room.databaseBuilder(
//                    context.applicationContext,
//                    AppDatabase::class.java,
//                    "gymDB"
//                )
//                    .addCallback(ScoreDatabaseCallback(scope))
//                    .build()
//                INSTANCE = instance
//                return instance
//            }
//        }

        fun destroyDataBase() {
            INSTANCE = null
        }
    }
}