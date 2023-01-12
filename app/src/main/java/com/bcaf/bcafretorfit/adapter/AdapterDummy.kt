package com.bcaf.bcafretorfit.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretorfit.R
import com.bcaf.bcafretorfit.model.PostDummyData
import com.bcaf.bcafretorfit.model.SearchItem


class AdapterDummy (val dummy: List<PostDummyData>): RecyclerView.Adapter<ViewHolderDummy>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderDummy {

        context = parent.context

        return ViewHolderDummy(
            LayoutInflater.from(parent.context).inflate(R.layout.itemdummy, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolderDummy, position: Int) {
        holder.setData(context, dummy[position], position)
    }

    override fun getItemCount(): Int {
        return dummy.size

    }
}