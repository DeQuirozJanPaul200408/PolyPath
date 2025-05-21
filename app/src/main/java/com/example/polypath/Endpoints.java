package com.example.polypath;

public class Endpoints {
    // Base URL for the local server
    private static final String BASE_URL = "http://192.168.100.12/polypath/";

    // Endpoints for account-related operations
    public static final String CREATE_ACCOUNT = BASE_URL + "SignUp.php?action=create";  // Create account
    public static final String READ_ALL = BASE_URL + "SignUp.php?action=read_all";      // Read all users
    public static final String LOG_IN = BASE_URL + "SignUp.php?action=read_one";        // Log in (verify username and password)
}