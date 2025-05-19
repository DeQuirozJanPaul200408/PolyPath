package com.example.polypath;

public class Endpoints {
    // Base URL should point to the root folder where your PHP scripts are located
    private static final String BASE_URL = "http://192.168.45.62/FinalActivity/";

    public static final String CREATE_ACCOUNT = BASE_URL + "php_final_activity.php?action=create";
    public static final String READ_ALL = BASE_URL + "php_final_activity.php?action=read_all";
    public static final String LOG_IN = BASE_URL + "php_final_activity.php?action=read_one";
}
