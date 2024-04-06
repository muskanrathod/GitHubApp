package com.tookbook.ui.mainModel.account

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.databinding.FragmentAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AccountFragment : BaseFragment<AccountViewModel, FragmentAccountBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_account

    override fun getViewModel(): AccountViewModel = ViewModelProvider(this)[AccountViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: AccountViewModel,
        binder: FragmentAccountBinding,
        savedInstanceState: Bundle?
    ) {
        setGreenStatusBar()
        binder.llOrder.setOnClickListener {
            findNavController().navigate(R.id.action_accountFragment_to_ordersFragment)
        }
    }

}