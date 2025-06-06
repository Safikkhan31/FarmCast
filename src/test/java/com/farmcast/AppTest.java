package com.farmcast;
import com.farmcast.data_generetor.data_generator;
import com.farmcast.messenger.messenger;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;

import com.farmcast.Main;

public class AppTest{
    // @Test
    public void test_data_generator() throws ClassNotFoundException{
        data_generator data_generator = new data_generator();
        System.out.println(data_generator.get_data(24.000715306825846, 72.48892137646993));
    }

    // @Test
    public void test_message(){
        messenger messenger = new messenger();
        messenger.send("+917874483501", "today's day is rainy");
        messenger.send("+919313463501", "today's day is rainy");
    }

    @Test
    public void test_main(){
        Main m = new Main();
        m.routine();
        // Main.main(null);
    }
}