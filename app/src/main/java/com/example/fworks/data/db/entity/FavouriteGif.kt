
package com.example.fworks.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "favourite_gif")

data class FavouriteGif(
    @ColumnInfo(name = "image_id") val image_id: String?,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "url") val url: String?
)
{
    @PrimaryKey(autoGenerate = true)
    var id:Int =0
}