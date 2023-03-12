package com.nekol.domain.enumeration;

public enum AlertMessage {
    // General
    SUCCESSFUL                  ("Successful."),
    INTERNAL_ERROR            ("Sorry, there seems to be an internal problem."),

    // User Login
    INVALID_CREDENTIALS    ("You have entered invalid credentials."),
    LOGOUT_SUCCESSFUL    ("Logout successful."),

    // User Registration
    REGISTER_SUCCESSFUL  ("You have successfully registered."),
    REGISTER_FAIL ("Username is already taken"),

    // Accounts
    OPEN_SUCCESSFUL         ("You have successfully open an account."),
    ;

    private final String message;

    AlertMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
