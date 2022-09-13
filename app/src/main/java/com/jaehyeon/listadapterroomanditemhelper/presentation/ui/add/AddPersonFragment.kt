package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.add

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.jaehyeon.listadapterroomanditemhelper.MainViewModel
import com.jaehyeon.listadapterroomanditemhelper.R
import com.jaehyeon.listadapterroomanditemhelper.data.entity.Person
import com.jaehyeon.listadapterroomanditemhelper.databinding.FragmentPersonAddBinding
import com.jaehyeon.listadapterroomanditemhelper.util.EventObserver
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@AndroidEntryPoint
class AddPersonFragment : Fragment() {

    private lateinit var binding: FragmentPersonAddBinding
    private val viewModel: AddPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_add, container, false)
        binding.btnAdd.setOnClickListener {
            viewModel.addPerson(
                Person(
                    name = binding.etName.text.toString(),
                    birth = binding.etBirth.text.toString(),
                    mark = false
                )
            )
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, EventObserver {
            findNavController().navigate(R.id.action_addPersonFragment_to_personsFragment)
        })

        viewModel.message.observe(viewLifecycleOwner, EventObserver {
            Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
        })
    }
}