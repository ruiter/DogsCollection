package com.rmmobile.dogscollection.data.source.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "breed"
)
data class LocalBreed(@PrimaryKey val id: Int, val breedName: String, val subname: String)
