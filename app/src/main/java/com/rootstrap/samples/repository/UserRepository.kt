package com.rootstrap.samples.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Config
import androidx.paging.toLiveData
import com.rootstrap.samples.model.User
import com.rootstrap.samples.model.UserDao
import com.rootstrap.samples.util.ioThread

class UserRepository(var userDao: UserDao) {

    companion object {
        var instance: UserRepository? = null

        fun getInstace() =
            instance ?: synchronized(this) {
                instance ?: UserRepository(AppDatabase.getInstance().userDao()).also { instance = it }
            }

    }

    // when you save|update any user in the db this call all the live data observers
    fun currentUser(): LiveData<User> = userDao.getCurrentUser()
    fun allUser() = userDao.getAllUsers().toLiveData(Config(
        pageSize = 5,
        enablePlaceholders = false,
        initialLoadSizeHint = 5
    ))

    //other alternative without DB
    fun saveUser(user: User) {
        //update the user in the server and save
        //......................................
        ioThread.execute {
            userDao.createOrUpdate(user)
        }
    }

    fun saveUsers(users: List<User>) {
        ioThread.execute {
            userDao.createOrUpdate(users)
            otherAllUser.postValue(users)
        }
    }

    var otherCurrentUser: MutableLiveData<User> = MutableLiveData()
    var otherAllUser: MutableLiveData<List<User>> = MutableLiveData()

    fun loadCurrentUser() {
        //this data come from Api
        val user = User("Armando", "Mesas", true)
        saveUser(user)
        otherCurrentUser.postValue(user)
    }

    fun getAllUser() {
        //this data come from Api
        val users = ArrayList<User>()
        saveUsers(users)
    }
}
