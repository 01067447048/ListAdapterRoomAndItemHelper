package com.jaehyeon.listadapterroomanditemhelper.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.jaehyeon.listadapterroomanditemhelper.MainViewModel
import com.jaehyeon.listadapterroomanditemhelper.R
import com.jaehyeon.listadapterroomanditemhelper.databinding.FragmentPersonDetailBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Jaehyeon on 2022/09/14.
 */
@AndroidEntryPoint
class DetailPersonFragment: Fragment() {

    private lateinit var binding: FragmentPersonDetailBinding
    private val args by navArgs<DetailPersonFragmentArgs>()
    private val viewModel: DetailPersonViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_person_detail, container, false)

        viewModel.getPerson(args.personId)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.person.observe(viewLifecycleOwner) {
            binding.model = it
        }
    }
}