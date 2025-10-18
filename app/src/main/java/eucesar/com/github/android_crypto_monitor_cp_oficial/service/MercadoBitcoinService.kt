package eucesar.com.github.android_crypto_monitor_cp_oficial.service

import eucesar.com.github.android_crypto_monitor_cp_oficial.model.TickerResponse
import retrofit2.Response
import retrofit2.http.GET

interface MercadoBitcoinService {

    @GET("api/BTC/ticker/")
    suspend fun getTicker(): Response<TickerResponse>
}
