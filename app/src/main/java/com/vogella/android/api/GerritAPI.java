package com.vogella.android.api;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GerritAPI {
    @GET("/films")
    Call<List<Films>> getListFilms();
}
