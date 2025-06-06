package com.farmcast.messenger;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.github.cdimascio.dotenv.Dotenv;

public class messenger {

    Dotenv dotenv = Dotenv.load();
    String ACCOUNT_SID = dotenv.get("ACCOUNT_SID");
    String AUTH_TOKEN = dotenv.get("AUTH_TOKEN");
    String twilio_number = dotenv.get("NUMBER");

    public void send(String phone_no_farmer, String messege){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try{

            Message message = Message.creator(
                    new PhoneNumber("whatsapp:"+phone_no_farmer), // must be verified
                    new PhoneNumber("whatsapp:"+twilio_number),         // Twilio sandbox number
                    messege
            ).create();

            
            System.out.println("Message sent! SID: " + message.getSid() + "\n");

        } catch (final ApiException e) {
            System.err.println(e + "\n");
        }

        
    }
}
