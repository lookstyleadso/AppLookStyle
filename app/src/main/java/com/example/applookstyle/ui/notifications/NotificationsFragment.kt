package com.example.applookstyle.ui.notifications

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.applookstyle.databinding.FragmentNotificationsBinding
import com.example.applookstyle.ui.activity.MainActivity
import com.example.applookstyle.ui.activity.login.LoginActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!

    private lateinit var animator: ViewPropertyAnimator

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
    }

    private fun initUI() {
        animator = binding.imageView.animate().alpha(0f).setDuration(0).withEndAction {
            animator = binding.imageView.animate().alpha(1f).setDuration(500).withEndAction {
                animator = binding.imageView.animate().alpha(0f).setDuration(500).withEndAction {
                    initUI()
                }
            }
        }

        binding.btnLogout.setOnClickListener(){
            startActivity(Intent(context,LoginActivity::class.java))
            requireActivity().finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        animator.cancel()
        _binding = null
    }
}