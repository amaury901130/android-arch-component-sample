package com.rootstrap.samples.adapter

import android.view.LayoutInflater
import androidx.recyclerview.widget.DiffUtil
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.rootstrap.samples.R
import com.rootstrap.samples.databinding.ModelUserBinding
import com.rootstrap.samples.model.User

class UserAdapter : PagedListAdapter<User, UserAdapter.UserViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean =
                    oldItem.userId == newItem.userId

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean =
                    oldItem == newItem
        }
    }

    inner class UserViewHolder(parent :ViewGroup, var bindingView: ModelUserBinding? = null) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.model_user, parent, false)) {

        init {
            bindingView = ModelUserBinding.bind(itemView)
        }

        fun bindTo(user : User?) {
            if (user != null)
                bindingView!!.user = user
        }
    }
}
