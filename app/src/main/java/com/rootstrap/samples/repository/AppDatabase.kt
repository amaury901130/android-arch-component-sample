package com.rootstrap.samples.repository


import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rootstrap.samples.AppContext
import com.rootstrap.samples.model.User
import com.rootstrap.samples.model.UserDao

var DATABASE_NAME = "DB_NAME"

/**
 * The Room database for this app
 * more details in this google sample
 * https://github.com/googlesamples/android-sunflower
 */
@Database(entities = [User::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(AppContext, AppDatabase::class.java, DATABASE_NAME).build()
            }
    }
}
