package com.githubapp.ui.mainModel

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.githubapp.R
import com.githubapp.base.activity.BaseActivity
import com.githubapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getViewModel(): MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]

    override val layoutRes: Int
        get() = R.layout.activity_main

    override fun onReadyToRender(
        binder: ActivityMainBinding,
        mViewModel: MainViewModel,
        savedInstanceState: Bundle?
    ) {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_navigation_fragment) as NavHostFragment
        val inflater = navHostFragment.navController.navInflater
        val graph = inflater.inflate(R.navigation.main_navigation)

        val navController = navHostFragment.navController
        navController.setGraph(graph, intent.extras)
    }
}