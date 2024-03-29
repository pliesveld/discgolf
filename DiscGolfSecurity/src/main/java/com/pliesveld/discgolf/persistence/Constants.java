package com.pliesveld.discgolf.persistence;

/**
 * Single source for parameters used in schema generation
 */
public class Constants {
    public static final String ID_GENERATOR = "identity";
    public static final String SEQUENCE_GENERATOR = "sequence";

    public static final int MIN_ACCOUNT_NAME_LENGTH = 3;
    public static final int MAX_ACCOUNT_NAME_LENGTH = 32;

    public static final int MIN_ACCOUNT_EMAIL_LENGTH = 5;
    public static final int MAX_ACCOUNT_EMAIL_LENGTH = 48;

    public static final int MIN_ACCOUNT_PASSWORD_LENGTH = 1;
    public static final int MAX_ACCOUNT_PASSWORD_LENGTH = 60;

    public static final int MAX_STATEMENT_CONTENT_LENGTH = 8192;
    public static final int MIN_STATEMENT_CONTENT_LENGTH = 12;
    public static final int MAX_STATEMENT_ANNOTATIONS_SIZE = 7;

    public static final int MAX_ATTACHMENT_TEXT_FILE_LENGTH = 65536;
    public static final int MAX_ATTACHMENT_BINARY_FILE_LENGTH = 3145728;
    public static final int MIN_ATTACHMENT_FILENAME_LENGTH = 4;
    public static final int MAX_ATTACHMENT_FILENAME_LENGTH = 48;

    public static final int MAX_CATEGORY_NAME_LENGTH = 48;
    public static final int MAX_CATEGORY_DESCRIPTION_LENGTH = 512;

    public static final int MIN_DECK_DESCRIPTION_LENGTH = 8;
    public static final int MAX_DECK_DESCRIPTION_LENGTH = 512;

    public static final int MAX_ACCOUNT_TOKEN_LENGTH = 64;

    public static final long PASSWORD_RESET_TOKEN_DURATION_DAYS = 7;
    public static final long REGISTRATION_TOKEN_DURATION_DAYS = 7;

    public static final int ACCESS_LIMIT_LOGIN_DELAY = 30;

    public static final int MIN_NOTIFICATION_MESSAGE_LENGTH = 12;
    public static final int MAX_NOTIFICATION_MESSAGE_LENGTH = 1024;


    public static final int MD5_HASH_LENGTH = 32;

}
