package com.training.interns.mza.whowroteitretrofit.retrofit.model;

import java.util.List;

public class SearchBooksResponse {
    private List<Book> items;

    public List<Book> getItems() {
        return items;
    }

    public void setResults(List<Book> items) {
        this.items = items;
    }
}

