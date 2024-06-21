package com.northcoders.recordshopv2.model;

import android.app.Application;
import android.widget.Toast;
import androidx.lifecycle.MutableLiveData;
import com.northcoders.recordshopv2.service.AlbumApiService;
import com.northcoders.recordshopv2.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

public class AlbumRepository {
    private MutableLiveData<List<Album>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public AlbumRepository(Application application) {
        this.application = application;
        mutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Album>> getMutableLiveData() {
        AlbumApiService albumApiService = RetrofitInstance.getService();
        Call<List<Album>> call = albumApiService.getAllAlbums();

        call.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    mutableLiveData.setValue(response.body());
                    Toast.makeText(application.getApplicationContext(),
                            "Album get from Database", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Error: " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Unable to get album from Database: " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });

        return mutableLiveData;
    }
}
