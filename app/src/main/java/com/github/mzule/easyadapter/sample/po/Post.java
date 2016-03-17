package com.github.mzule.easyadapter.sample.po;

import com.github.mzule.easyadapter.TypedValue;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class Post implements Serializable, TypedValue {
    private String name;
    private String avatar;
    private String content;

    public Post(String name, String avatar, String content) {
        this.name = name;
        this.avatar = avatar;
        this.content = content;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public Object getType() {
        return getClass();
    }
}
