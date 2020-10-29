package com.techcorpafrica.sms.africastalkingsms.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
public class AppConfigs {

    @Value("${app.africastalkingsms.server}")
    private String server;

    @Value("${app.africastalkingsms.port}")
    private String port;

    @Value("${app.africastalkingsms.url}")
    private String url;

    @Value("${app.africastalkingsms.username}")
    private String username;

    @Value("${app.africastalkingsms.password}")
    private String password;

    @Value("${sdp.successSMSstatus}")
    private String successSMSstatus;

    @Value("${sdp.failedSMSstatus}")
    private String failedSMSstatus;

    @Value("${sdp.retrySMSstatus}")
    private String retrySMSstatus;

    @Value("${app.africastalkingsms.type}")
    private String type;

    @Value("${app.africastalkingsms.drl}")
    private String dlr;

    public String getServer() {
        return server;
    }

    public String getPort() {
        return port;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getSuccessSMSstatus() {
        return successSMSstatus;
    }

    public String getFailedSMSstatus() {
        return failedSMSstatus;
    }

    public String getRetrySMSstatus() {
        return retrySMSstatus;
    }

    public String getType() {
        return type;
    }

    public String getDlr() {
        return dlr;
    }
}
