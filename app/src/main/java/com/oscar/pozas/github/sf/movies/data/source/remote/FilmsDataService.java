package com.oscar.pozas.github.sf.movies.data.source.remote;

import com.oscar.pozas.github.sf.movies.domain.main.model.Films;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FilmsDataService {

    @GET("/resource/wwmu-gmzc.json")
    Call<List<Films>> getListFilmsFromRemote();

}
