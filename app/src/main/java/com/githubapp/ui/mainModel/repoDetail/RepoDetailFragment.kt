package com.githubapp.ui.mainModel.repoDetail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.githubapp.R
import com.githubapp.base.fragment.BaseFragment
import com.githubapp.databinding.FragmentRepoDetailBinding
import com.githubapp.ui.mainModel.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RepoDetailFragment : BaseFragment<RepoViewModel, FragmentRepoDetailBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_repo_detail

    override fun getViewModel(): RepoViewModel =
        ViewModelProvider(this)[RepoViewModel::class.java]
    override fun onReadyToRender(
        view: View,
        viewModel: RepoViewModel,
        binder: FragmentRepoDetailBinding,
        savedInstanceState: Bundle?
    ) {
        val avatar = arguments?.getString("avatar")
        val name = arguments?.getString("name")
        val desc = arguments?.getString("desc")
        val link = arguments?.getString("link")
        val language = arguments?.getString("language")

        Glide.with(this)
            .load(avatar)
            .apply(RequestOptions.circleCropTransform())
            .into(binder.ivAvatar)
        binder.tvNameValue.text = name
        binder.tvDescValue.text = desc
        binder.tvUrlValue.text = link
        binder.tvLangValue.text = language

        binder.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binder.tvUrlValue.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(link)
            startActivity(intent)
        }
    }

}