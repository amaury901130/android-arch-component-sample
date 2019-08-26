package com.rootstrap.samples.viewmodel

import androidx.lifecycle.ViewModel
import com.rootstrap.samples.repository.UserRepository
import kotlin.random.Random

class MainActivityViewModel(private val userRepository: UserRepository = UserRepository.getInstace()) : ViewModel() {

    var currentUser = userRepository.currentUser()
    var allUsers = userRepository.allUser()

    fun changeUserName() {
        currentUser.value!!.name = "User " + Random.nextInt(5000)
        userRepository.saveUser(currentUser.value!!)
    }
}
