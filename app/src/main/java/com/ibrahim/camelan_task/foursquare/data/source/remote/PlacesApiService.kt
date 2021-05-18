package com.ibrahim.camelan_task.foursquare.data.source.remote

import com.ibrahim.camelan_task.base.API_VERSION
import com.ibrahim.camelan_task.base.CLIENT_ID
import com.ibrahim.camelan_task.base.CLIENT_SECRET
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.FourSquareResponse
import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApiService {

    @GET("explore")
    fun getPlaces(
        @Query("ll") location : String = "40.74224,-73.99386",
        @Query("v") version : String = API_VERSION,
        @Query("radius") radius : String = "1000",
        @Query("client_id") client_id : String = CLIENT_ID,
        @Query("limit") limit : Int = 10,
        @Query("client_secret") client_secret : String = CLIENT_SECRET
    ): Single<FourSquareResponse>


    @GET("{VENUE_ID}/photos")
    fun getPlacePhotos(
        @Path("VENUE_ID") venueId: String,
        @Query("v") version : String = API_VERSION,
        @Query("client_id") client_id : String = CLIENT_ID,
        @Query("client_secret") client_secret : String = CLIENT_SECRET
    ): Single<PlacePhotos>


}