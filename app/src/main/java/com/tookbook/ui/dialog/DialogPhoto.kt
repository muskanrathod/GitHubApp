package com.tookbook.ui.dialog

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.LinearLayout
import com.tookbook.R

class DialogPhoto(context: Context) : Dialog(context) {
    var onClickCallback: OnClickCallback? = null
    init {
        try {
            requestWindowFeature(Window.FEATURE_NO_TITLE)
            setContentView(R.layout.dialog_photo)
            this.window!!.setBackgroundDrawable(ColorDrawable(0))
            this.window!!.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.setCanceledOnTouchOutside(true)
            this.setCancelable(true)

            val camera = findViewById<LinearLayout>(R.id.ll_camera)
            val upload = findViewById<LinearLayout>(R.id.ll_upload)
            val delete = findViewById<ImageView>(R.id.iv_delete)

            camera.setOnClickListener {
                if (onClickCallback != null) onClickCallback!!.onSelectCamera()
                dismiss()
            }
            upload.setOnClickListener {
                if (onClickCallback != null) onClickCallback!!.onSelectUpload()
                dismiss()
            }
            delete.setOnClickListener {
                if (onClickCallback != null) onClickCallback!!.onDelete()
                dismiss()
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface OnClickCallback {
        fun onSelectCamera()
        fun onSelectUpload()
        fun onDelete()
    }
}
