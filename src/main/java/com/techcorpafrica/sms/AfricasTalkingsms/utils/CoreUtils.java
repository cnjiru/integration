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
	try {
              log.info("here is the sourceAddress to be used " + payload.getsourceAddress());
	      log.info("here is the sourceAddress to be used " + payload.getsourceAddress());
              if(payload.getsourceAddress().equals("benchmark")){
                  log.info("inside bentchmark function " + payload.getsourceAddress());
                  TimeUnit.SECONDS.sleep(2);
                  ResponseDTO response = new ResponseDTO("103","Success, Message Submitted Successfully.","tuvgvgv");
                  return new ResponseEntity<>(response, HttpStatus.OK);
              }else {
                  AfricasTalking.initialize(appConfigs.getUsername(), appConfigs.getPassword());

                  /* Get the SMS service */
                  SmsService sms = AfricasTalking.getService(AfricasTalking.SERVICE_SMS);

                  /* Set the numbers you want to send to in international format */
                  String[] recipients = new String[]{
                          "+"+payload.getDestination()
                  };
                  /* Set your message */
                  String message = payload.getMessage();

                  /* Set your shortCode or senderId */
                  String from = payload.getsourceAddress(); // or "ABCDE"

                  /* That’s it, hit send and we’ll take care of the rest */
                      log.info("request to be sent out " + recipients.toString());
                      List<Recipient> response = sms.send(message, from, recipients, false);
                      System.out.println("The response "+Arrays.toString(response.toArray()));
                      ResponseDTO responseQ = null;
                      String status=null;
                      for (Recipient recipient : response) {
                          System.out.print(recipient.number);
                          System.out.print(" : ");
                          System.out.println(recipient.status +" : "+ recipient.messageId);
                          if (recipient.status.equals("Success")){
                              status="101";
                          }
                          responseQ = coreUtils.handleResponse(status,recipient.messageId);
                      }

                  return new ResponseEntity<>(responseQ, HttpStatus.OK);
              }
          } catch (HttpStatusCodeException ex) {
              log.info("RawStatusCode to be returned "+ ex.getMessage());
              ResponseDTO responsedto = new ResponseDTO("400",ex.getMessage(),"-1");
              return new ResponseEntity<>(responsedto, HttpStatus.GATEWAY_TIMEOUT);
          } catch (Exception ex) {
              ResponseDTO responsedto = new ResponseDTO("400",ex.getMessage(),"-1");
              log.info("RawStatusCode to be returned "+ ex.getMessage());
              return new ResponseEntity<>(responsedto, HttpStatus.GATEWAY_TIMEOUT);
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
