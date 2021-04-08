package co.uk.gkortsaridis.breakingbad_gan.utils

import co.uk.gkortsaridis.breakingbad_gan.entities.BreakingBadCharacter
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import java.util.concurrent.TimeUnit
import kotlin.collections.ArrayList

object GANCodeTestApi {

    const val BASE_URL = "https://breakingbadapi.com/api/"

    private const val TAG = "SparkPoolMinerMonitorAPI"
    private const val NETWORK_TIMEOUT_SECONDS = 30L

    private val ganClient = OkHttpClient.Builder()
        .connectTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(NETWORK_TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .client(ganClient)
        .baseUrl(BASE_URL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
        .build()

    val api = retrofit.create(GANTestCodeAPIinterface::class.java)

    interface GANTestCodeAPIinterface {
        @GET("characters")
        fun getBreakingBadCharacters(): Observable<ArrayList<BreakingBadCharacter>>
    }


}


