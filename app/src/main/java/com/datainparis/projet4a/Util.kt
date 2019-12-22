package com.example.projet4a

import com.datainparis.rest.ParisAPI
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Util {

    companion object {
        private var retrofit: Retrofit? = null
        private var parisAPI: ParisAPI? = null
        private var projectConfig: ProjectConfig? = null

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

        fun getConfig(): ProjectConfig? {
            println("Working Directory = " +
                    System.getProperty("user.dir"));
            val mapper = ObjectMapper(YAMLFactory())

            if (projectConfig == null) {
                try {
                    return mapper.readValue(File("application-config.yml"), ProjectConfig::class.java)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return null;
            }
            else {
                return projectConfig
            }


        }

    }

}
