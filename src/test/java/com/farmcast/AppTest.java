package com.farmcast;
import com.farmcast.data_generetor.data_generator;
import com.farmcast.messenger.messenger;
import org.junit.Test;

public class AppTest{
    @Test
    public void test(){
        // System.out.println(data_generator.get_data(24.000715306825846, 72.48892137646993));
        messenger.send("+917874483501", "today's day is rainy");
        messenger.send("+919313463501", "today's day is rainy");

    }
}