package com.example.vikaskumar.coccompleteguide.api;

import com.example.vikaskumar.coccompleteguide.Models.BaseDesignModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiCall {
    @GET("basedescriptions/")
    Call<List<BaseDesignModel>> getAllBaseByTownhallIdAndtypeId(@Query("townhallId") int townhallId, @Query("typeId") int typeId, @Query("page") int page);
}
