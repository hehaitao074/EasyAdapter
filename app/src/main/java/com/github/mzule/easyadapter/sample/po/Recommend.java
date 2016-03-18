package com.github.mzule.easyadapter.sample.po;

import java.io.Serializable;

/**
 * Created by CaoDongping on 3/17/16.
 */
public class Recommend implements Serializable {
    private User[] users;

    public Recommend(User[] users) {
        this.users = users;
    }

    public User[] getUsers() {
        return users;
    }

    public void setUsers(User[] users) {
        this.users = users;
    }

    public static class User {
        private String name;
        private String avatar;

        public User(String name, String avatar) {
            this.name = name;
            this.avatar = avatar;
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
    }
}
