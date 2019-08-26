package com.rootstrap.samples.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
/**
* Declaring the column info allows for the renaming of variables without implementing a
* database migration, as the column name would not change.
*/
@Entity(
    tableName = "USER"
)
data class User (
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "last_name") var lastName: String,
    @ColumnInfo(name = "current") var isCurrentUser: Boolean? = false) {
        @PrimaryKey(autoGenerate = true)
        @ColumnInfo(name = "id")
        var userId: Long = 0
    }