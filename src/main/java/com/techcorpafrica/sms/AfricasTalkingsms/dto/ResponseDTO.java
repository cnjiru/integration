package com.techcorpafrica.sms.africastalkingsms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ResponseDTO {

    private String statusCode;
    private String statusDescription;
    private String mnoSMSReference;

}