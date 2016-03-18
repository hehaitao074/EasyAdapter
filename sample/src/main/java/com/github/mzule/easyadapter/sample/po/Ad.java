package com.github.mzule.easyadapter.sample.po;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class Ad implements Serializable {
    private String imageUrl;

    public Ad(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
