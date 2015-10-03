package com.praim.test.app.helpers;

import com.praim.test.app.interfaces.ILevelsDownload;

import retrofit.RestAdapter;

public class DownloadHelper {
    private static ILevelsDownload sDownloader;
    private static final String URL = "https://vk.com/doc7870684_423125405?hash=b2d1d7c1e975454d94&dl=f7c28e4cca13a8f049";

    private DownloadHelper() {

    }

    public static ILevelsDownload getInstance() {
        if (sDownloader == null)
            sDownloader = new RestAdapter.Builder().setEndpoint(URL).setLogLevel(RestAdapter.LogLevel.FULL).build().create(ILevelsDownload.class);
        return sDownloader;
    }
}
