package dj.dynamic.card.retrofit

import dj.dynamic.card.BuildConfig
import dj.dynamic.card.DynamicCardApplication
import dj.dynamic.card.util.network.NetworkUtil.Companion.isConnectedToNetwork
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.Level
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

/**
 * Retrofit singleton class
 */
object RetrofitClient {

    private const val baseUrl = "https://run.mocky.io/v3/"

    private val retrofitClient: Retrofit.Builder by lazy {

        val levelType: Level = if (BuildConfig.BUILD_TYPE.contentEquals("debug"))
            Level.BODY else Level.NONE

        val logging = HttpLoggingInterceptor()
        logging.setLevel(levelType)

        val cacheDirectory =
            File(DynamicCardApplication.getInstance().cacheDir, "okHttpCachedApiResponse")
        val cache = Cache(cacheDirectory, 5 * 1024 * 1024) // 5 MiB

        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.addInterceptor(logging)
        okHttpClient.cache(cache)
        okHttpClient.addInterceptor { chain ->
            var request = chain.request()
            request = if (isConnectedToNetwork(DynamicCardApplication.getInstance()))
                request.newBuilder().header("Cache-Control", "public, max-age=" + 5)
                    .build()
            else
                request.newBuilder().header(
                    "Cache-Control",
                    "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 30
                ).build()
            chain.proceed(request)
        }

        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient.build())
            .addConverterFactory(GsonConverterFactory.create())
    }

    val apiInterface: ApiInterface by lazy {
        retrofitClient
            .build()
            .create(ApiInterface::class.java)
    }
}