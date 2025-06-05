package com.farmcast.messenger;

import com.twilio.Twilio;
import com.twilio.exception.ApiException;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import io.github.cdimascio.dotenv.Dotenv;

public class messenger {

    // static Dotenv dotenv = Dotenv.configure()
    //                  .directory("../.gitignore/")
    //                  .filename(".env")
    //                  .load();
    Dotenv dotenv = Dotenv.load();
    public final String ACCOUNT_SID = dotenv.get("TWILIO_ACCOUNT_SID");
    public final String AUTH_TOKEN = dotenv.get("TWILIO_AUTH_TOKEN");
    public final String twilio_number = dotenv.get("TWILIO_NUMBER");

    public void send(String phone_no_farmer, String messege){

        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        try{

            Message message = Message.creator(
                    new PhoneNumber("whatsapp:"+phone_no_farmer), // must be verified
                    new PhoneNumber("whatsapp:"+twilio_number),         // Twilio sandbox number
                    messege
            ).create();
            
            System.out.println("Message sent! SID: " + message.getSid());

        } catch (final ApiException e) {
            System.err.println(e);
        }

        
    }
}
