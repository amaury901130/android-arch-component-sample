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
    //but you have to postValue every time to callBack all observers
    var otherCurrentUser: MutableLiveData<User> = MutableLiveData()
    var otherAllUser: MutableLiveData<List<User>> = MutableLiveData()

    fun saveUser(user: User) {
        ioThread.execute {
            userDao.createOrUpdate(user)
            //in case you are not using room db
            otherCurrentUser.postValue(user)
        }
    }

    fun saveUsers(users: List<User>) {
        ioThread.execute {
            userDao.createOrUpdate(users)
            //in case you are not using room db
            otherAllUser.postValue(users)
        }
    }

    fun loadCurrentUser() {
        //this data come from Api
        val user = User("Armando", "Mesas", true)
        saveUser(user)
    }

    fun getAllUser() {
        //this data come from Api
        val users = ArrayList<User>()
        saveUsers(users)
    }
}
