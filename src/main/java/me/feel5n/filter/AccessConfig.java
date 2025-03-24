package me.feel5n.filter;

public class AccessConfig {

    public static String[] anonymous = {
            "/accounts/signin",
            "/accounts/signup",
    };

    public static String[] admin = {
            "/boards/add",
            "/boards/edit",
            "/boards/remove",
    };

    public static String[] user = {
            "/posts/add",
            "/posts/edit",
            "/posts/remove",
            "/accounts/remove",
            "/accounts/edit",
            "/accounts/signout",
    };
}
