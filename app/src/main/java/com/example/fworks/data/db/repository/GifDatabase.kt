package com.example.fworks.data.db.repository

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.fworks.data.db.entity.FavouriteGif


@Database(
    entities = [FavouriteGif::class],
    version = 1,
    exportSchema = false
)

abstract class GifDatabase: RoomDatabase() {
    abstract fun getGifDao(): GifDao

    companion object {
        private const val DB_NAME = "gif_database.db"
        @Volatile private var instance: GifDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            GifDatabase::class.java,
            DB_NAME
        ).build()
    }
}