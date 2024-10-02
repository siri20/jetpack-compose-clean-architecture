package com.siri.core.data.datasource.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

/** Created by github.com/im-o on 12/27/2022. */

@Entity(tableName = "table_shop", primaryKeys = ["id"])
data class ProductEntity(
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "description")
    val description: String? = null,

    @ColumnInfo(name = "price")
    val price: Double? = null,

    @ColumnInfo(name = "thumbnail")
    val thumbnail: String? = null,

    @ColumnInfo(name = "title")
    val title: String? = null
)
