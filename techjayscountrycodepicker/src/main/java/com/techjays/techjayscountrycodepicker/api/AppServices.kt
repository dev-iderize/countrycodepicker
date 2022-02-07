package com.techjays.techjayscountrycodepicker.api

import android.content.Context
import android.util.Log

import com.google.gson.Gson
import com.google.gson.JsonObject
import com.sj.covidradar.util.AppDialogs
import com.techjays.techjayscountrycodepicker.app.models.CountryCode
import com.techjays.techjayscountrycodepicker.util.Utility
import okhttp3.CookieJar
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.lang.reflect.Type

class AppServices {

    object API {

        internal val APP_URL =
            "http://34.224.39.147/api/portal/"

        fun constructUrl(urlKey: String): String {
            return String.format("%s%s", APP_URL, urlKey)
        }

        const val getcountrycode = "get_country_code/"

    }

    private interface ApiInterface {
        @POST
        fun POST(
            @Url url: String,
            @Body body: JsonObject
        ): Call<ResponseBody>

        @GET
        fun GET(
            @Url url: String,
            @QueryMap param: Map<String, String>
        ): Call<ResponseBody>

        @GET
        fun GET(
            @Url url: String
        ): Call<ResponseBody>
    }

    companion object {
        private var retrofit: Retrofit? = null
        private var okHttpClient: OkHttpClient? = null
        private fun getClient(): Retrofit {

            if (okHttpClient == null) {
                okHttpClient = OkHttpClient.Builder()
                    .cookieJar(CookieJar.NO_COOKIES)
                    .build()
            }

            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(API.APP_URL)
                    .client(okHttpClient!!)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit as Retrofit
        }

        fun getCovidData(c: Context, listener: ResponseListener) {
            try {
                val apiService = getClient().create(ApiInterface::class.java)
                val mHashCode = API.getcountrycode
                val mURL = API.constructUrl(mHashCode)
                val call = apiService.GET(mURL)
                initService(c, call, CountryCode::class.java, mHashCode, listener)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }


        private fun initService(
            c: Context,
            call: Call<ResponseBody>,
            mSerializable: Type,
            mHashCode: String,
            listener: ResponseListener
        ) {
            Log.d("URL --> ", call.request().url().toString())
            Log.d("METHOD --> ", call.request().method())
            call.enqueue(object : retrofit2.Callback<ResponseBody> {
                override fun onResponse(
                    call: Call<ResponseBody>,
                    response: retrofit2.Response<ResponseBody>
                ) {
                    listener.onResponse(getResponse(c, response, mSerializable, mHashCode))
                }

                override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                    // listener.onResponse(getErrorMsg(t, mHashCode.hashCode()))
                }
            })
        }

        private fun getResponse(
            context: Context,
            mResponse: retrofit2.Response<ResponseBody>,
            mSerializable: Type,
            mHashCode: String
        ): Response? {
            val response: Response

            if (!Utility.isInternetAvailable(context)) {
                okHttpClient?.dispatcher()?.cancelAll()
                return null
            }

            if (mResponse.isSuccessful) {
                val body = mResponse.body()?.string()!!
                Log.d("success", body)
                response = Gson().fromJson(body, mSerializable)
            } else {
                try {
                    if (mResponse.code() == 401) { // Unauthorized User / Invalid Token
                        Log.e("unauthorized", mResponse.errorBody()!!.string())
                        Log.e("unauthorized url", mResponse.raw().request().url().toString())
                        okHttpClient?.dispatcher()?.cancelAll()
                        return null
                    } else {
                        val errorBody = mResponse.errorBody()?.string()!!
                        Log.e("fail", errorBody)
                        response = Gson().fromJson(errorBody, mSerializable)
                        response?.responseStatus = true
                    }
                } catch (e: Exception) {
                    AppDialogs.okAction(context, e.message!!)
                    return null
                }
            }
            return response
        }
    }
}
