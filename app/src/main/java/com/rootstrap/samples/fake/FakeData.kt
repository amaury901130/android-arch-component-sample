package com.rootstrap.samples.fake

import com.rootstrap.samples.model.User
import com.rootstrap.samples.repository.AppDatabase

class FakeData {

    fun loadFakeUsers() {
       val userDao = AppDatabase.getInstance().userDao()
       for (i in 1..1000) userDao.createOrUpdate(User("User "+ i, "Lastname " + i))
       //load fake current user
        userDao.createOrUpdate(User("Armando", "Mesas", true))
    }
}
