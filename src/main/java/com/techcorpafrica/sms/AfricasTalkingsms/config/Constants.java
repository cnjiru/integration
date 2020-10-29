package com.techcorpafrica.sms.africastalkingsms.config;

public class Constants {

    //StatusCodes
    public static final String Success = "101";
    public static final String processed = "100";
    public static final String Invalid_Credentials = "1703";
    public static final String Invalid_parameter = "1704";
    public static final String Invalid_message = "1705";
    public static final String Invalid_destination = "403";
    public static final String Invalid_source = "402";
    public static final String Invalid_dlr = "404";
    public static final String User_validation_failed = "406";
    public static final String Internal_error = "1710";
    public static final String Insufficient_credit = "405";
    public static final String Response_timeout = "501";
    public static final String DND_reject = "502";
    public static final String Spam_message = "1728";

    //Messages
    public static final String Success_message = "Success, Message Submitted Successfully.";
    public static final String Invalid_URL_message = "Processed.";
    public static final String Invalid_Credentials_message = "Invalid value in username or password parameter.";
    public static final String Invalid_parameter_message = "Invalid value in type parameter.";
    public static final String Invalid_message_message = "Invalid message.";
    public static final String Invalid_destination_message = "Invalid destination.";
    public static final String Invalid_source_message = "Invalid source (Sender).";
    public static final String Invalid_dlr_message = "Unsupported NumberType.";
    public static final String User_validation_failed_message = "UserInBlacklist.";
    public static final String Internal_error_message = "Internal error.";
    public static final String Insufficient_credit_message = "Insufficient credit.";
    public static final String Response_timeout_message = "Response timeout.";
    public static final String DND_reject_message = "DND reject.";
    public static final String Spam_message_message = "Spam message.";

}
