package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import com.jaehyeon.listadapterroomanditemhelper.MainViewModel
import com.jaehyeon.listadapterroomanditemhelper.R
import com.jaehyeon.listadapterroomanditemhelper.databinding.FragmentPersonsBinding
import com.jaehyeon.listadapterroomanditemhelper.util.ItemTouchCallBack
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@AndroidEntryPoint
class PersonsFragment: Fragment() {

    private lateinit var binding: FragmentPersonsBinding
    private val activityViewModel: MainViewModel by activityViewModels()
    private val adapter = PersonAdapter(
        { id ->
            findNavController().navigate(PersonsFragmentDirections.actionPersonsFragmentToDetailPersonFragment(id))
        }, { person ->
            activityViewModel.updatePersons(person.copy(mark = !person.mark))
        }, { person ->
            activityViewModel.deletePersons(person)
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_persons, container, false)
        binding.rv.apply {
            adapter = this@PersonsFragment.adapter
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        }

        ItemTouchCallBack(adapter).also {
            ItemTouchHelper(it).apply {
                attachToRecyclerView(binding.rv)
            }
        }

        binding.btnAddPerson.setOnClickListener {
            findNavController().navigate(R.id.action_personsFragment_to_addPersonFragment)
        }

        activityViewModel.getPersons()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launchWhenResumed {
            activityViewModel.state.collectLatest {
                adapter.submitList(it)
            }
        }

    }
}