package com.praim.test.app.interfaces;

import com.praim.test.app.models.Level;

import java.util.ArrayList;

import retrofit.Callback;
import retrofit.http.GET;

public interface ILevelsDownload {
    @GET("/")
    ArrayList<Level> downloadLevels();

    @GET("/")
    void downloadLevels(Callback<ArrayList<Level>> callback);
}
