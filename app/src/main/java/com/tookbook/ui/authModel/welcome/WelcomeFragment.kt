package com.tookbook.ui.authModel.welcome

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.tookbook.R

class WelcomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_welcome, container, false)
        requireActivity().window.statusBarColor = resources.getColor(R.color.green)
        val btnLogin = rootView.findViewById<TextView>(R.id.btn_login)
        val btnSignup = rootView.findViewById<TextView>(R.id.btn_signup)

        btnLogin.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_loginFragment)
        }

        btnSignup.setOnClickListener {
            findNavController().navigate(R.id.action_welcomeFragment_to_signupFragment)
        }

        return rootView
    }

}