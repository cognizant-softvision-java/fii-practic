package com.fiiPractic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Engine {
    public void run(){
        System.out.println("Vrum, Vrum!");
    }
}
