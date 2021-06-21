package com.example.pokemonapi.model
import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon (

    @ColumnInfo(name = "name")
    var name: String = "",

    @PrimaryKey()
    var id: Int = 0,

    @ColumnInfo(name = "overview")
    var overview: String = "",

    @ColumnInfo(name = "image_url")
    @Nullable
    var imageUrl: String = ""
)