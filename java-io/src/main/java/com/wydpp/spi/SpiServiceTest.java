package com.wydpp.spi;

import java.util.Iterator;
import java.util.ServiceLoader;

public class SpiServiceTest {

    public static void main(String[] args) {
        ServiceLoader<SpiService> serviceLoader = ServiceLoader.load(SpiService.class);
        Iterator<SpiService> iterator = serviceLoader.iterator();
        while(iterator.hasNext()){
            SpiService spiService = iterator.next();
            spiService.say();
        }
    }
}
