package com.training.interns.mza.whowroteitretrofit.retrofit.model;

import java.io.Serializable;
import java.util.List;

public class VolumeInfo implements Serializable {
    private String title;
    private List<String> authors;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }
}
