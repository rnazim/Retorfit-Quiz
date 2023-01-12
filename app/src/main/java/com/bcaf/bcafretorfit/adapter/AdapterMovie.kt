package com.bcaf.recycleviewbcaf.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bcaf.bcafretorfit.R
import com.bcaf.bcafretorfit.model.SearchItem


class AdapterMovie (val movie: List<SearchItem>): RecyclerView.Adapter<ViewHolderMovie>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMovie {

        context = parent.context

        return ViewHolderMovie(
            LayoutInflater.from(parent.context).inflate(R.layout.itemmovie, parent, false)
        )

    }

    override fun onBindViewHolder(holder: ViewHolderMovie, position: Int) {
        holder.setData(context, movie.get(position), position)
    }

    override fun getItemCount(): Int {
        return movie.size

    }
}