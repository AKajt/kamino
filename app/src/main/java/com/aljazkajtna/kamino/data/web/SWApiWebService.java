package com.aljazkajtna.kamino.data.web;

import com.aljazkajtna.kamino.data.pojo.Planet;
import com.aljazkajtna.kamino.data.pojo.Resident;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface SWApiWebService {

    @GET("planets/10")
    Call<Planet> getPlanet();

    @GET("residents/{residentId}")
    Call<Resident> getResident(@Path("residentId") int residentId);

}
