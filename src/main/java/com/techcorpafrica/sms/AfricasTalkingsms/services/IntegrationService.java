package com.techcorpafrica.sms.AfricasTalkingsms.services;

import com.africastalking.AfricasTalking;
import com.africastalking.SmsService;
import com.africastalking.sms.Recipient;
import com.techcorpafrica.sms.africastalkingsms.config.AppConfigs;
import com.techcorpafrica.sms.africastalkingsms.dto.RequestDTO;
import com.techcorpafrica.sms.africastalkingsms.dto.ResponseDTO;
import com.techcorpafrica.sms.AfricasTalkingsms.utils.CoreUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class IntegrationService {

      @Autowired
      private CoreUtils coreUtils;

      @Autowired
      private AppConfigs appConfigs;

      public ResponseEntity<Object> sendSms(RequestDTO payload){
          StringBuilder dataFromUrl = new StringBuilder();
          String dataBuffer = "";
          try {
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
      }
}
