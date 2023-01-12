package com.bcaf.bcafretorfit

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.bcaf.bcafretorfit.adapter.AdapterDummy
import com.bcaf.bcafretorfit.database.DummyDatabase
import com.bcaf.bcafretorfit.model.PostDummyData
import com.bcaf.bcafretorfit.model.ResponsePostDummyData
import com.bcaf.bcafretorfit.service.NetworkConfig
import kotlinx.android.synthetic.main.activity_post_data_dummy.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostDataDummy : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_data_dummy)
        txtTags.setOnClickListener(View.OnClickListener {
            createMultipleSelectionDialog()
        })

        loadData()
        btnPost.setOnClickListener({
            val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = cm.activeNetworkInfo
            if(networkInfo != null && networkInfo.isConnected == true){
                postData()
            }else{
                var dummyData = PostDummyData(0,"60d0fe4f5311236168a109f4",
                    txtImage.text.toString(),
                    txtText.text.toString(),
                    txtLikes.text.toString().toInt(),
                    selectionList )

                GlobalScope.launch {
                    DummyDatabase.getInstance(applicationContext).dummyDao().insertDummy(dummyData)
                    runOnUiThread {
                        Toast.makeText(applicationContext, "Offline, Data Tesimpan", Toast.LENGTH_LONG).show()
                        loadData()
                    }
                }
            }
        })
    }

    fun loadData(){
        GlobalScope.launch {
            val data: List<PostDummyData> = DummyDatabase.getInstance(applicationContext).dummyDao().getAll()
            Log.d("Data", data.toString())
            runOnUiThread({
                var adapterx = AdapterDummy(data)
                recyclerView2.apply {
                    layoutManager = LinearLayoutManager(context)
                    adapter = adapterx
                }
                recyclerView2.adapter?.notifyDataSetChanged()
            })
        }
    }

    fun postData(){
        var dummyData = PostDummyData(
            0,
            "60d0fe4f5311236168a109f4",
            txtImage.text.toString(),txtText.text.toString(),
            txtLikes.text.toString().toInt(),
            selectionList
        )
        NetworkConfig().getServiceDummy().postData(dummyData)
            .enqueue(object : Callback<ResponsePostDummyData> {
                override fun onResponse(
                    call: Call<ResponsePostDummyData>,
                    response: Response<ResponsePostDummyData>
                ) {
                    if (response.isSuccessful) {
                        Toast.makeText(
                            applicationContext,
                            response.message(),
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }

                override fun onFailure(call: Call<ResponsePostDummyData>, t: Throwable) {
                    Log.e("error post", t.printStackTrace().toString())
                }
            })
    }

    var selectionList = mutableListOf<String>()
    val listItem = arrayOf("Movies"," Actor"," Fun")
    val listChecked = booleanArrayOf(false,false,false)

    fun createMultipleSelectionDialog(){

        val builder = AlertDialog.Builder(this)

        builder.setTitle("Choose Tags")

        builder.setMultiChoiceItems(listItem, listChecked){
            dialog,which,ischecked ->
            listChecked[which] = ischecked
        }

        builder.setCancelable(false)
        builder.setNegativeButton("Cancel"){
                dialog,which ->
            Toast.makeText(applicationContext, "Submit ${which}",Toast.LENGTH_LONG).show()
        }
        builder.setPositiveButton("Submit"){
            dialog,which ->
            for((index,value) in listChecked.withIndex()){
                if(value){
                    selectionList.add(listItem[index])
                }
            }

            for(listItem in selectionList){
                txtTags.append("${listItem},")
            }
            txtTags.setText(txtTags.text.toString().dropLast(1))
        }

        builder.create()

        val alertDialog = builder.create()
        alertDialog.show()
    }
}