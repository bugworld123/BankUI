package co.wedevx.digitalbank.automation.ui.utils;

import com.github.javafaker.Faker;
import com.github.javafaker.service.FakeValuesService;
import com.github.javafaker.service.RandomService;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

public class MockData {

    private FakeValuesService fakeValuesService =new FakeValuesService(
            new Locale("en-US"),new RandomService());

    public String generateRandomEmail(){
        String email=fakeValuesService.bothify(new Faker().name().firstName()+"####@gmail.com");//4 digits
        return email;
    }

    public String generateRandomSsn(){
        String ssn=String.format("%09d",new Random().nextInt(1000000000));// 9 digits create
        return ssn;
    }

    public Map<String,String> generateRandomNameAndEmail(){
        String name=fakeValuesService.bothify(new Faker().name().firstName());//Keenan
        String email=fakeValuesService.bothify(name+"##@gmail.com");//Keenan80@gmail.com

        // we can do like this also:
//        int randomInt=new Random().nextInt(4);
//        String email2=name+randomInt+"@gmail.com";

        Map<String,String> data=new HashMap<>();
        data.put("name",name);
        data.put("email",email);
        return data;
    }







}
