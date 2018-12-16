package com.training.interns.mza.whowroteitretrofit.retrofit.model;

import java.io.Serializable;

public class Book implements Serializable {
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}
