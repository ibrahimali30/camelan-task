package com.ibrahim.camelan_task.foursquare.data.source.remote

import com.ibrahim.camelan_task.base.API_VERSION
import com.ibrahim.camelan_task.base.CLIENT_ID
import com.ibrahim.camelan_task.base.CLIENT_SECRET
import io.reactivex.Single
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.FourSquareResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface PlacesApiService {

    @GET("explore")
    fun getPlaces(
        @Query("ll") lon : String = "40.74224,-73.99386",
        @Query("v") version : String = API_VERSION,
        @Query("radius") radius : String = "1000",
        @Query("client_id") client_id : String = CLIENT_ID,
        @Query("client_secret") client_secret : String = CLIENT_SECRET
    ): Single<FourSquareResponse>
}


//Client Id
//D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS
//
//Client Secret
//URWTFV2SIX2RKVIUIGFUGLBFCCNVO32Z0OS3YU4VIKT2040H

// https://api.foursquare.com/v2/venues/explore?client_id=D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS&ll=40.74224,-73.99386&v=20211111
// https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS&client_secret=URWTFV2SIX2RKVIUIGFUGLBFCCNVO32Z0OS3YU4VIKT2040H&v=20211111