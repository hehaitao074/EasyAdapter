package com.github.mzule.easyadapter.sample.po;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class Repost implements Serializable {

    private String name;
    private String avatar;
    private String content;
    private Post post;

    public Repost(String name, String avatar, String content, Post post) {
        this.name = name;
        this.avatar = avatar;
        this.content = content;
        this.post = post;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }
}
