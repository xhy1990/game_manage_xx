package com.aceplaying.game.gm_server.user;

/**
 * @author xhy
 * @date 2021/3/26 19:26
 */
public class User {
    private int id;

    /**
     * Username.
     */
    private String username;

    /**
     * Password.
     */
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
