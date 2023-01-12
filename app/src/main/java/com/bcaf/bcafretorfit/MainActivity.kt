package com.bcaf.bcafretorfit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretorfit.model.OMDBDetailResponse
//import com.bcaf.bcafretorfit.fragment.MovieList
import com.bcaf.bcafretorfit.model.OMDBResponse
import com.bcaf.bcafretorfit.model.SearchItem
import com.bcaf.bcafretorfit.service.NetworkConfig
import com.bcaf.bcafretrofit.fragment.DetailMovie
import com.bcaf.bcafretrofit.fragment.ListMovie
import com.bcaf.recycleviewbcaf.adapter.AdapterMovie
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    // tambahin rcycleView di main lyout
//    lateinit var adapterBCAF: AdapterMovie
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
//        searchMovie("Naruto")
//    }
//
//    fun searchMovie(title:String){
//
//        NetworkConfig().getServiceOMDB().searchMovie(title).enqueue(object : retrofit2.Callback<OMDBResponse>{
//                override fun onResponse( call: Call<OMDBResponse>, response: Response<OMDBResponse>
//                ) {
//                    Log.d("OMDB API search respon", response.toString())
//                    setupList(response.body()?.search as List<SearchItem>)
//                }
//                override fun onFailure(call: Call<OMDBResponse>, t: Throwable) {
//                    Log.e("Failed Response", t.message.toString())
//                }
//            })
//    }
//
//    fun setupList(list:List<SearchItem>){
//        adapterBCAF = AdapterMovie(list)
//
//        recyclerView.apply{
//            layoutManager = LinearLayoutManager(this@MainActivity)
//            adapter = adapterBCAF
//        }
//    }
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    showListMovieFragment()




}

    fun showListMovieFragment(){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.add(R.id.frameFragment, ListMovie.newInstance("",""),"list")
        ft.commit()
    }

    fun searchMovie(title:String, callbackNetwork : ICallBackNetwork) {

        var data : List<SearchItem>? = null
        NetworkConfig().getServiceOMDB().searchMovie(title).enqueue(object :
            Callback<OMDBResponse> {
            override fun onResponse(call: Call<OMDBResponse>, response: Response<OMDBResponse>) {
                Log.d("Response OMDB APi search", response.toString())

                if(response.body()?.search!=null) {
                    data = (response.body()?.search as List<SearchItem>)
                    callbackNetwork.onFinish(data!!)
                }
            }

            override fun onFailure(call: Call<OMDBResponse>, t: Throwable) {
                Log.e("Failed Response", t.message.toString())
                callbackNetwork.onFailed()
            }

        })
    }

    fun detailMovie(idMovie : String, callbackNetwork: ICallBackNetwork){

        NetworkConfig().getServiceOMDB().detailMovie(idMovie).enqueue(object : Callback<OMDBDetailResponse>{
            override fun onResponse(
                call: Call<OMDBDetailResponse>,
                response: Response<OMDBDetailResponse>
            ) {
                if(response.body() !=null) {
                    callbackNetwork.onFinishDetail(response.body()as OMDBDetailResponse)

                }
            }

            override fun onFailure(call: Call<OMDBDetailResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })


    }


}