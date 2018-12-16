package com.training.interns.mza.whowroteitretrofit.retrofit.interfaces;

import com.training.interns.mza.whowroteitretrofit.retrofit.model.SearchBooksResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookRestProvider {
    @GET("/books/v1/volumes")
    Call<SearchBooksResponse> getBookInfo(@Query("q") String q, @Query("maxResults") String maxResults,
                                          @Query("printType") String printType);

}
