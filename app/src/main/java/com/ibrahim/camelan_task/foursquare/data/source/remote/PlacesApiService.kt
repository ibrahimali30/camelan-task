package com.ibrahim.camelan_task.foursquare.data.source.remote

import com.ibrahim.camelan_task.base.API_VERSION
import com.ibrahim.camelan_task.base.CLIENT_ID
import com.ibrahim.camelan_task.base.CLIENT_SECRET
import io.reactivex.Single
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.FourSquareResponse
import com.ibrahim.camelan_task.foursquare.data.model.foursquare.Venue
import com.ibrahim.camelan_task.foursquare.data.model.photo.PlacePhotos
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PlacesApiService {

    @GET("explore")
    fun getPlaces(
        @Query("ll") location : String = "40.74224,-73.99386",
        @Query("v") version : String = API_VERSION,
        @Query("radius") radius : String = "10000",
        @Query("client_id") client_id : String = CLIENT_ID,
        @Query("limit") limit : Int = 1,
//        @Query("oauth_token") oauth_token : String = "J02LPKR3COBDHKYVCROBJYX4XJLTLPWQJPXLUHOCZE3NHWYQ",
        @Query("client_secret") client_secret : String = CLIENT_SECRET
    ): Single<FourSquareResponse>


    @GET("{VENUE_ID}/photos")
    fun getPlacePhotos(
        @Path("VENUE_ID") venueId: String,
        @Query("v") version : String = API_VERSION,
        @Query("client_id") client_id : String = CLIENT_ID,
//        @Query("oauth_token") oauth_token : String = "J02LPKR3COBDHKYVCROBJYX4XJLTLPWQJPXLUHOCZE3NHWYQ",
        @Query("client_secret") client_secret : String = CLIENT_SECRET
    ): Single<PlacePhotos>


}// TODO: 5/18/2021 delete oauth token


//Client Id
//D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS
//
//Client Secret
//URWTFV2SIX2RKVIUIGFUGLBFCCNVO32Z0OS3YU4VIKT2040H

// https://api.foursquare.com/v2/venues/explore?client_id=D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS&ll=40.74224,-73.99386&v=20211111
// https://api.foursquare.com/v2/venues/search?ll=40.7,-74&client_id=D4YYKJKBKJJ34RCRIAVLGVFMPTI30TFSEPPNB3GZLSKTJMLS&client_secret=URWTFV2SIX2RKVIUIGFUGLBFCCNVO32Z0OS3YU4VIKT2040H&v=20211111


