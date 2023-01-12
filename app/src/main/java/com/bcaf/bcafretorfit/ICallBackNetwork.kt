package com.bcaf.bcafretorfit

import com.bcaf.bcafretorfit.model.OMDBDetailResponse
import com.bcaf.bcafretorfit.model.SearchItem

interface ICallBackNetwork {

    fun onFinish(data: List<SearchItem>)
    fun onFinishDetail(data : OMDBDetailResponse)
    fun onFailed()
}