package com.githubapp.ui.mainModel.home

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.githubapp.R
import com.githubapp.base.fragment.BaseFragment
import com.githubapp.data.model.Items
import com.githubapp.data.model.RepositoryDTO
import com.githubapp.databinding.FragmentHomeBinding
import com.githubapp.ui.mainModel.home.adapter.RepoListAdapter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>(), RepoListAdapter.CellClickListener {
    override val layoutRes: Int
        get() = R.layout.fragment_home

    override fun getViewModel(): HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]

    private var mList = ArrayList<Items>()
    lateinit var repoListAdapter: RepoListAdapter

    override fun onReadyToRender(
        view: View,
        viewModel: HomeViewModel,
        binder: FragmentHomeBinding,
        savedInstanceState: Bundle?
    ) {
        setGreenStatusBar()
        initView(binder)
        observers()
    }

    private fun initView(binder: FragmentHomeBinding){
        var network = activity?.let { isNetworkConnected(it.applicationContext) }
        if(network!!){
            getViewModel().getData("a")
        }else {
            val gson = Gson()
            val personListType = object : TypeToken<ArrayList<Items>>() {}.type
            val jsonArrayString = getViewModel().appDataManager.getRepoDTO("dto")
            val repositoryDTO: ArrayList<Items> = gson.fromJson(jsonArrayString, personListType)
            repoListAdapter = RepoListAdapter(repositoryDTO, requireContext(), this)
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binder!!.rvList.layoutManager = layoutManager
            binder!!.rvList.adapter = repoListAdapter
        }

        binder.etSearch.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                // This method is called before the text is changed.
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // This method is called when the text is changed.
                val newText = s.toString()
                // Do something with the new text
            }

            override fun afterTextChanged(s: Editable?) {
                // This method is called after the text has been changed.
                getViewModel().getData(s.toString())
            }
        })
    }

    private fun observers() {
        getViewModel().successData.observe(viewLifecycleOwner) {
            if (it != null) {
                hideProgressDialog()
                mList.clear()
                mList.addAll(it.items)

                val gson = Gson()
                val json = gson.toJson(it.items)

                getViewModel().appDataManager.saveRepoDTO("dto", json)
                repoListAdapter = RepoListAdapter(mList, requireContext(), this)
                val layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binder!!.rvList.layoutManager = layoutManager
                binder!!.rvList.adapter = repoListAdapter
                getViewModel().successData.value = null
            }
        }

        getViewModel().loading.observe(this){
            if (it){
                showProgressDialog(requireContext())
            }
            else {
                hideProgressDialog()
            }
        }
    }

    override fun onCellClickListener(itemsOne: Items) {
        val bundle = Bundle().apply {
            putString("avatar", itemsOne.owner?.avatarUrl)
            putString("name", itemsOne.name)
            putString("desc", itemsOne.description)
            putString("link", itemsOne.htmlUrl)
            putString("language", itemsOne.language)
        }
        findNavController().navigate(R.id.action_homeFragment_to_repoDetailFragment, bundle)
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
            return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET) == true
        } else {
            val networkInfo = connectivityManager.activeNetworkInfo
            return networkInfo?.isConnectedOrConnecting == true
        }
    }

}