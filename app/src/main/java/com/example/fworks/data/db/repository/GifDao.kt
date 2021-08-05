
package com.example.fworks.data.db.repository
import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.fworks.data.db.entity.FavouriteGif


@Dao
interface GifDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //if some data is same/conflict, it'll be replace with new data.
    suspend fun insertGif(item: FavouriteGif)

   /* @Update
    suspend fun updateNote(gifImage: TredingGifImage)*/

    @Delete
    suspend fun deleteGif(item: FavouriteGif)

    @Query("SELECT * FROM favourite_gif")
    fun getAllGifs(): LiveData<MutableList<FavouriteGif>>
    // why not use suspend ? because Room does not support LiveData with suspended functions.
    // LiveData already works on a background thread and should be used directly without using coroutines

    @Query("DELETE FROM favourite_gif")
    suspend fun clearGif()

    @Query("DELETE FROM favourite_gif WHERE id = :id") //you can use this too, for delete image by id.
    suspend fun deleteGifById(id: Int)
}