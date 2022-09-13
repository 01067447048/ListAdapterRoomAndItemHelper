package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.databinding.ItemPersonBinding
import com.jaehyeon.listadapterroomanditemhelper.util.ItemTouchCallBackInterface

/**
 * Created by Jaehyeon on 2022/09/14.
 */
class PersonAdapter(
    val onActionDetail: (Int) -> Unit,
    val onActionMark: (Person) -> Unit,
    val onActionDelete: (Person) -> Unit
): ListAdapter<Person, PersonAdapter.PersonViewHolder>(diffUtil), ItemTouchCallBackInterface {

    companion object {
        val diffUtil = object: DiffUtil.ItemCallback<Person>() {
            override fun areItemsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean  = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Person,
                newItem: Person
            ): Boolean = oldItem.mark == newItem.mark

        }
    }

    inner class PersonViewHolder(private val binding: ItemPersonBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(person: Person) {
            binding.model = person

            binding.view.setOnClickListener {
                onActionMark(person)
            }

            binding.root.setOnClickListener {
                person.id ?: return@setOnClickListener
                onActionDetail(person.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PersonViewHolder =
        PersonViewHolder(ItemPersonBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PersonViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    override fun onItemMove(from: Int, to: Int): Boolean {
        return true
    }

    override fun onItemSwipe(position: Int) {
        onActionDelete(currentList[position])
    }

}