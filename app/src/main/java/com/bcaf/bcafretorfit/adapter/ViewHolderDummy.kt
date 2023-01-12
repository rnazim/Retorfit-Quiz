package com.bcaf.bcafretorfit.adapter

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretorfit.model.PostDummyData
import com.bcaf.bcafretorfit.model.SearchItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.itemdummy.view.*
import kotlinx.android.synthetic.main.itemmovie.view.*

class ViewHolderDummy (view: View): RecyclerView.ViewHolder(view) {

    val view = view

    fun setData(context: Context, data: PostDummyData, position: Int) {
        view.dmyText.setText(data.text)
        view.dmyLikes.setText(data.likes.toString())
        view.dmyTags.setText(data.tags.toString())
    }
}