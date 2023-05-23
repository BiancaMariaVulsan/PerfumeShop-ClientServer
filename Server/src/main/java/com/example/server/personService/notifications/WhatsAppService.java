package com.example.server.personService.notifications;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

public class WhatsAppService {
    public static final String ACCOUNT_SID = "ACd5fa94bc3c9396264c1a8fa52e46d28d";
    public static final String AUTH_TOKEN = "34d73e8634c705c890fc4a7b3390bd40";

    public static void sendMessage(String username, String password) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("whatsapp:+40722260889"),
                        new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
                        "The new login credentials are:\n" +
                                "Username: " + username + "\n" +
                                "Password: " + password)
                .create();
    }

    // you need to go on twilio and activate the sandbox in order for the message to be sent
}
