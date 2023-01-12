package com.bcaf.bcafretrofit.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.bcaf.bcafretorfit.MainActivity
import com.bcaf.bcafretorfit.R
import com.bcaf.bcafretorfit.model.SearchItem
import com.bcaf.bcafretrofit.fragment.DetailMovie
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.itemmovie.view.*

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    var data: List<SearchItem> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemmovie, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        holder.bind(data[position])
    }

    override fun getItemCount() = data.size

    class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: SearchItem) = with(itemView) {

            itemView.txtTitle.setText(item.title)
            itemView.txtTahun.setText(item.year)
            Glide.with(itemView.context).load(item.poster).into(itemView.gambar)

            this.setOnClickListener(View.OnClickListener {

                    it ->
                val ft: FragmentTransaction = (context as MainActivity).supportFragmentManager.beginTransaction()
                ft.replace(R.id.frameFragment, DetailMovie.newInstance(item.imdbID.toString(),""),"detail")
                    .addToBackStack("list")
                ft.commit()

            })

        }
    }
}


