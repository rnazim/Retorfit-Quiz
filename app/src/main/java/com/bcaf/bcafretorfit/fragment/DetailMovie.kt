package com.bcaf.bcafretrofit.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bcaf.bcafretorfit.ICallBackNetwork
import com.bcaf.bcafretorfit.MainActivity
import com.bcaf.bcafretorfit.R
import com.bcaf.bcafretorfit.model.OMDBDetailResponse
import com.bcaf.bcafretorfit.model.SearchItem
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail_movie.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DetailMovie.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailMovie : Fragment(), ICallBackNetwork {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_detail_movie, container, false)

        (context as MainActivity).detailMovie(param1.toString(),this)

        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DetailMovie.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DetailMovie().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onFinish(data: List<SearchItem>) {
        TODO("Not yet implemented")
    }

    override fun onFinishDetail(data: OMDBDetailResponse) {

        txtJudul.setText(data.title)
        txtSutradara.setText(data.director)
        txtPlot.setText(data.plot)

        Glide.with(requireActivity()).load(data.poster).into(poster)
    }

    override fun onFailed() {
        TODO("Not yet implemented")
    }
}