package com.training.interns.mza.whowroteitretrofit;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.training.interns.mza.whowroteitretrofit.retrofit.interfaces.BookRestProvider;
import com.training.interns.mza.whowroteitretrofit.retrofit.model.VolumeInfo;
import com.training.interns.mza.whowroteitretrofit.retrofit.model.SearchBooksResponse;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    // Base URL for Books API.
    private static final String BOOK_BASE_URL =  "https://www.googleapis.com";

    static VolumeInfo getBookInfo(String queryString) {
        VolumeInfo volumeInfo = null;

        try {
            OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
            Gson gson = new GsonBuilder()
                    .create();
            Retrofit.Builder builder = new Retrofit.Builder()
                    .baseUrl(BOOK_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson));
            builder.client(httpClient.build());
            Retrofit retrofit = builder.build();
            BookRestProvider bookRestProvider = retrofit.create(BookRestProvider.class);
            Call<SearchBooksResponse> call = bookRestProvider.getBookInfo(queryString, "10", "books");
            Response<SearchBooksResponse> response = call.execute();
            if (!(response.body().getItems()).isEmpty()) {
                volumeInfo = response.body().getItems().get(0).getVolumeInfo();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return volumeInfo;
    }
}
