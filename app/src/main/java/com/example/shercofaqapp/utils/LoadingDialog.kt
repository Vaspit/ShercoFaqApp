package com.example.shercofaqapp.utils

import android.app.AlertDialog
import android.content.Context
import androidx.fragment.app.Fragment
import com.example.shercofaqapp.R

class LoadingDialog(val mFragment: Fragment) {
    private lateinit var isDialog: AlertDialog

    fun startLoading() {
        val inflater = mFragment.layoutInflater
        val dialogView = inflater.inflate(R.layout.loading_item, null)
        val builder = AlertDialog.Builder(mFragment.requireContext())

        builder.setView(dialogView)
        builder.setCancelable(false)
        isDialog = builder.create()
        isDialog.show()
    }

    fun isDismiss() {
        isDialog.dismiss()
    }
}