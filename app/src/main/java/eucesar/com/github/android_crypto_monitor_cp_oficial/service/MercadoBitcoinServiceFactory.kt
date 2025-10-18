package eucesar.com.github.android_crypto_monitor_cp_oficial.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MercadoBitcoinServiceFactory {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://www.mercadobitcoin.net/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun create(): MercadoBitcoinService = retrofit.create(MercadoBitcoinService::class.java)
}
