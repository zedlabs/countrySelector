package me.zed.countryselect;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

interface CountryListApi {

    @GET("countries")
    Call<CountryList> listCountries();
}

