package com.github.mzule.easyadapter.sample.po;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/22/16.
 */
public class Article implements Serializable {
    public static final int STYLE_BRIEF = 1;
    public static final int STYLE_FULL = 2;
    private String imageUrl;
    private String text;
    private int style;

    public Article(String imageUrl, String text, int style) {
        this.imageUrl = imageUrl;
        this.text = text;
        this.style = style;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStyle() {
        return style;
    }

    public void setStyle(int style) {
        this.style = style;
    }
}
