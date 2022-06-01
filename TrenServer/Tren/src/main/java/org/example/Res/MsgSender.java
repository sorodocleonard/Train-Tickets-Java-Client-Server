package org.example.Res;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class MsgSender {
    // Replace these placeholders with your Account Sid and Auth Token
    public static final String ACCOUNT_SID = "AC50423c8295ae946ea411f854219bc265";
    public static final String AUTH_TOKEN = "82dd8473351a8768953c932927a3d41e";

    public MsgSender() {
    }

    public void send(String number, String msg){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+400747911725"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "Hello from your friendly neighborhood Java application!")
                .create();
    }
}