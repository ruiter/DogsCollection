package com.rmmobile.dogscollection.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rmmobile.dogscollection.data.source.local.dao.HomeDao
import com.rmmobile.dogscollection.data.source.local.model.LocalBreed

@Database(entities = [LocalBreed::class], version = 1, exportSchema = false)
abstract class DogsCollectionDatabase : RoomDatabase() {
    abstract fun homeDao(): HomeDao
}