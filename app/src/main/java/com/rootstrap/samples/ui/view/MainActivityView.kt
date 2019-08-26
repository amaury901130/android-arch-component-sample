package com.rootstrap.samples.ui.view

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.rootstrap.samples.adapter.UserAdapter
import com.rootstrap.samples.databinding.ActivityMainBinding
import com.rootstrap.samples.model.User
import com.rootstrap.samples.viewmodel.MainActivityViewModel

class MainActivityView(var mainBinding: ActivityMainBinding,
                       var viewModel: MainActivityViewModel? = MainActivityViewModel(),
                       var activity: AppCompatActivity,
                       var userAdapter : UserAdapter? = UserAdapter()) {

    init {
        //init the view
        mainBinding.run {
            view = this@MainActivityView
            viewModel = viewModel
            listUsers.adapter = userAdapter
        }

        //listen all viewmodel observers
        viewModel?.run {
            currentUser.observe(activity,  Observer<User> { mainBinding.user = it } )
            allUsers.observe(activity, Observer<PagedList<User>> { userAdapter?.submitList(it) })
        }
    }

    fun changeUserName() {
        //do something
        mainBinding.run {
            btnChangeCurrentUserName.visibility = View.GONE
            userName.visibility = View.GONE
            userLastName.visibility = View.GONE
        }
        //call the action
        viewModel!!.changeUserName()
    }
}
