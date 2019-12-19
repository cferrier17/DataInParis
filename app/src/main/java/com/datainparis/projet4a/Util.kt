package com.example.projet4a

import com.datainparis.rest.ParisAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Util {

    companion object {
        private var retrofit: Retrofit? = null
        private var parisAPI: ParisAPI? = null

        fun getRetrofit(baseURL: String): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(baseURL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }


        fun getParisAPI(): ParisAPI? {

            if (parisAPI == null)
                parisAPI = retrofit?.create(ParisAPI::class.java!!)

            return parisAPI
        }

    }

}