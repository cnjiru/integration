package com.techcorpafrica.sms.AfricasTalkingsms.controller;

import com.techcorpafrica.sms.africastalkingsms.dto.RequestDTO;
import com.techcorpafrica.sms.AfricasTalkingsms.services.IntegrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/")
public class AfricasTalkingSmsController {

    @Autowired
    private IntegrationService integrationService;

    @PostMapping(value = "/sendsms")
    public ResponseEntity<Object> sendSms(@Valid @RequestBody RequestDTO payload) {
        //Logger log = null;
        log.info("request received "+payload.toString());
        return integrationService.sendSms(payload);
    }
}
