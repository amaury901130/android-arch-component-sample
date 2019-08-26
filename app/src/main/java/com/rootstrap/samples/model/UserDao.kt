package com.rootstrap.samples.model

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*

/**
 * The Data Access Object for the [User] class.
 */
@Dao
interface UserDao {
    @Query("SELECT * FROM USER WHERE current != 1 ORDER BY name ")
    fun getAllUsers(): DataSource.Factory<Int, User>

    //also you can do this in a background thread, no need live data
    @Query("SELECT * FROM USER WHERE current != 1 ORDER BY name ")
    fun getAll(): List<User>

    @Query("SELECT * FROM USER WHERE id = :id")
    fun getById(id: Long): LiveData<User>

    @Query("SELECT * FROM USER WHERE current = 1")
    fun getCurrentUser(): LiveData<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE) // REPLACE|ABORT|IGNORE -> default: ABORT
    fun createOrUpdate(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE) // REPLACE|ABORT|IGNORE -> default: ABORT
    fun createOrUpdate(users: List<User>): List<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE) // REPLACE|ABORT|IGNORE -> default: ABORT
    fun update(user: User)

    @Delete
    fun delete(user: User)
}
