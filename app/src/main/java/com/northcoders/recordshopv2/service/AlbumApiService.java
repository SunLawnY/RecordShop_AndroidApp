package com.northcoders.recordshopv2.service;

import com.northcoders.recordshopv2.model.Album;
import retrofit2.Call;
import retrofit2.http.GET;

import java.util.List;

public interface AlbumApiService {
    @GET("/album")
    Call<List<Album>> getAllAlbums();
}
