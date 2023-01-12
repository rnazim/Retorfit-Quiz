package com.bcaf.recycleviewbcaf.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretorfit.model.SearchItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.itemmovie.view.*

class ViewHolderMovie(view: View): RecyclerView.ViewHolder(view) {

    val view = view

    fun setData(context: Context, data: SearchItem, position: Int) {
        view.txtTitle.setText(data.title)
        view.txtTahun.setText(data.year)
        Glide.with(context).load(data.poster).into(view.gambar)
    }
}