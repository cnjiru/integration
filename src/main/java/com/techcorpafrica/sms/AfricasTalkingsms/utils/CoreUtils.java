package com.techcorpafrica.sms.AfricasTalkingsms.utils;

import com.techcorpafrica.sms.africastalkingsms.config.Constants;
import com.techcorpafrica.sms.africastalkingsms.dto.ResponseDTO;
import com.techcorpafrica.sms.africastalkingsms.config.AppConfigs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Pattern;


@Slf4j
@Component
public class CoreUtils {

    @Autowired
    private AppConfigs appConfigs;

    private static StringBuffer convertToUnicode(String regText) {
        char[] chars = regText.toCharArray();
        StringBuffer hexString = new StringBuffer();
        for (char aChar : chars) {
            String iniHexString = Integer.toHexString(aChar);
            if (iniHexString.length() == 1)
                iniHexString = "000" + iniHexString;
            else if (iniHexString.length() == 2)
                iniHexString = "00" + iniHexString;
            else if (iniHexString.length() == 3)
                iniHexString = "0" + iniHexString;
            hexString.append(iniHexString);
        }
        return hexString;
    }

    public ResponseDTO handleResponse(String statusCodes,String mnoID){
        String statusCode = null;
        String statusDescription = null;
        try{
            Pattern pattern = Pattern.compile("\\|");
            //String[] words = pattern.split(response);

            //statusCode=words[0];
            switch (statusCodes){
                case Constants.Success: statusDescription=Constants.Success_message; statusCode=appConfigs.getSuccessSMSstatus(); break;
                case Constants.processed: statusDescription=Constants.Invalid_URL_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_Credentials: statusDescription=Constants.Invalid_Credentials_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_parameter: statusDescription=Constants.Invalid_parameter_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_message: statusDescription=Constants.Invalid_message_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_destination: statusDescription=Constants.Invalid_destination_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_source: statusDescription=Constants.Invalid_source_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Invalid_dlr: statusDescription=Constants.Invalid_dlr_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.User_validation_failed: statusDescription=Constants.User_validation_failed_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Internal_error: statusDescription=Constants.Internal_error_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Insufficient_credit: statusDescription=Constants.Insufficient_credit_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Response_timeout: statusDescription=Constants.Response_timeout_message; statusCode=appConfigs.getRetrySMSstatus() ;break;
                case Constants.DND_reject: statusDescription=Constants.DND_reject_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                case Constants.Spam_message: statusDescription=Constants.Spam_message_message; statusCode=appConfigs.getFailedSMSstatus(); break;
                default:throw new IllegalStateException("Unexpected value: " + statusCode);
            }
            if(statusCodes.equals(Constants.Success)){
                log.info("The response in an array "+mnoID);
                return new ResponseDTO(statusCode,statusDescription,mnoID);
            }
            return new ResponseDTO(statusCode,statusDescription,null);
            
        }catch (Exception e){
            return new ResponseDTO(statusCode,statusDescription,null);
        }
        
    }

}
