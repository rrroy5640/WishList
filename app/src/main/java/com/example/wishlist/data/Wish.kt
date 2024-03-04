package com.example.wishlist.data

data class Wish(
    val id: Long = 0,
    val title: String = "",
    val description : String = ""
)

object DummyWish {
    val wishList = listOf(
        Wish(title = "title", description = "description")
    )
}