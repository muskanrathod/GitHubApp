package com.tookbook.ui.mainModel.notification

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.tookbook.R
import com.tookbook.base.fragment.BaseFragment
import com.tookbook.base.viewModel.BaseViewModel
import com.tookbook.databinding.FragmentNotificationBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NotificationFragment : BaseFragment<NotificationViewModel, FragmentNotificationBinding>() {
    override val layoutRes: Int
        get() = R.layout.fragment_notification

    override fun getViewModel(): NotificationViewModel = ViewModelProvider(this)[NotificationViewModel::class.java]

    override fun onReadyToRender(
        view: View,
        viewModel: NotificationViewModel,
        binder: FragmentNotificationBinding,
        savedInstanceState: Bundle?
    ) {

    }

}