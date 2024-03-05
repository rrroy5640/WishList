package com.example.wishlist.data

import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDao: WishDao) {
    suspend fun addAWish(wish: Wish){
        wishDao.addAWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>>{
        return wishDao.getAllWishes()
    }

    fun getWishById(id: Long): Flow<Wish>{
        return wishDao.getWishById(id)
    }

    suspend fun updateWish(wish: Wish){
        wishDao.updateWish(wish)
    }

    suspend fun deleteWish(wish: Wish){
        wishDao.deleteWish(wish)
    }
}