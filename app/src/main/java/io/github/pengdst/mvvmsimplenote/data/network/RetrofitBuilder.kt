package io.github.pengdst.mvvmsimplenote.data.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created on 6/15/21 by Pengkuh Dwi Septiandi (@pengdst)
 *
 * - Github https://github.com/pengdst
 * - Gitlab https://gitlab.com/pengdst
 * - LinkedIn https://linkedin.com/in/pengdst
 */
object RetrofitBuilder {

    private var instance: Retrofit? = null

    fun newInstance() = instance ?: Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().also {
            instance = it
        }

    private const val BASE_URL = "https://private-a5a722-pengkuh.apiary-mock.com/"

}