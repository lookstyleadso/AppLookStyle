package com.example.applookstyle.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applookstyle.databinding.FragmentHomeBinding
import com.example.applookstyle.ui.home.adapter.HomeAdapter
import com.example.cronodepro.core.Constant
import com.example.cronodepro.core.Resource
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private lateinit var adapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initListener()
    }

    private fun initListener() {
        initAdapter()
        initViewModel()
    }

    private fun initViewModel() {
        viewModel.listAppointments.observe(viewLifecycleOwner){
            when(it){
                is Resource.Error -> {
                    println(it.message)
                }
                is Resource.Loading -> {

                }
                is Resource.Success ->{
                    if (it.data != null){
                        println(it.data)

                        adapter.updateList(it.data)
                    }
                }
            }
        }
        viewModel.getAppointment("bearer ${Constant.AUTHO_JWT}")
    }

    private fun initAdapter() {
        adapter = HomeAdapter(emptyList())
        binding.rvAppointment.adapter = adapter
        binding.rvAppointment.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}