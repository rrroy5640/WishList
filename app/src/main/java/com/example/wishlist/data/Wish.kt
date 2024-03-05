package com.example.wishlist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "wish-table")
data class Wish(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "wish-title") val title: String = "",
    @ColumnInfo(name = "wish-description") val description : String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "title", description = "description")
    )
}