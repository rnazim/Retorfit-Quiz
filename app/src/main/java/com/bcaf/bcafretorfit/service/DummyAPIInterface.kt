package com.bcaf.bcafretorfit.service

import android.telecom.Call
import com.bcaf.bcafretorfit.PostDataDummy
import com.bcaf.bcafretorfit.model.PostDummyData
import com.bcaf.bcafretorfit.model.ResponsePostDummyData
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST

interface DummyAPIInterface {

//    @Headers("app-id:63bcecb03cea2a2870e0291b")
    @POST("post/create/")
    fun postData(@Body dummyData: PostDummyData): retrofit2.Call<ResponsePostDummyData>
}