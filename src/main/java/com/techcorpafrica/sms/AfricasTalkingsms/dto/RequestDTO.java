package com.techcorpafrica.sms.africastalkingsms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestDTO {

    // Message content that is to be transmitted
    @NotEmpty(message = "Message cannot be empty")
    @NotNull(message = "Message  cannot be blank")
    private String message;

    @NotEmpty(message = "destination cannot be empty")
    @NotNull(message = "destination  cannot be blank")
    @Min(value =10, message = "destination  cannot be blank")
    private String destination;

    // Sender Id to be used for submitting the message
    @NotEmpty(message = "source cannot be empty")
    @NotNull(message = "source  cannot be blank")
    private String sourceAddress;

    private String outboundID;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getsourceAddress() {
        return sourceAddress;
    }

    public void setsourceAddress(String source) {
        this.sourceAddress = source;
    }
}
