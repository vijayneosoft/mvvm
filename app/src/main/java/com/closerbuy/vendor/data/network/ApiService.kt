package com.nanosoft.linie.dataProvider

import com.closerbuy.vendor.data.network.CustomerService
import com.closerbuy.vendor.presentation.utils.Constant
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.util.concurrent.TimeUnit

/**
 * Created by Vijay on 3/12/19.
 */

class ApiService {

    companion object {
        var apiService: ApiService? = null
        private var retrofit: Retrofit? = null
        lateinit var services: CustomerService

        fun getInstance(): ApiService {

            if (apiService == null) {
                synchronized(ApiService::class.java) {

                    apiService = ApiService()
                    val interceptor = HttpLoggingInterceptor()
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
                    val httpClient = OkHttpClient.Builder()
                    httpClient.addInterceptor(interceptor)
                    httpClient.networkInterceptors().add(StethoInterceptor())
                    httpClient.connectTimeout(30, TimeUnit.SECONDS)
                    httpClient.writeTimeout(30, TimeUnit.SECONDS)
                    httpClient.readTimeout(30, TimeUnit.SECONDS)

                    httpClient.addInterceptor(object : Interceptor {
                        @Throws(IOException::class)
                        override fun intercept(chain: Interceptor.Chain): Response {
                            val original = chain.request()

                            val requestBuilder = original.newBuilder()

                            /*requestBuilder = requestBuilder.header("Accept", "application/json")
                            .method(original.method(), original.body())*/

                            val request = requestBuilder
//                                .header("Authorization","Bearer ya29.a0Ae4lvC20KrukbN1LmzImXABKeixGsqVFM_6FKLt_FhXNKFSPIIe-e3ojTFxF-Slhim4QY5g3-S41lob-6l0rlRx4h10O84TmC-zMtWykXCo24pTYxonccNXiWbcBCNqF3mBgS3L5SB2DcAu3UD_AwTvPNqqCi0Ce2H0")
                                .build()
                            return chain.proceed(request)
                        }
                    })

                    val gson = GsonBuilder()
                        .setLenient()
                        .create()
                    retrofit = Retrofit.Builder()
                        .baseUrl(Constant.BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                        .build()
                }
            }
            return apiService!!
        }
    }

    fun call(): CustomerService {
        services = retrofit!!.create(CustomerService::class.java)
        return services
    }


}