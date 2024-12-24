package com.cesar.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.cesar.data.database.dao.RecipeDao
import com.cesar.data.database.model.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1,exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun RecipeDao():RecipeDao

}