package com.ume.test.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.IOException;
import java.util.Calendar;

public class ComplexTest
{
    @Test
    public void testWrite() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Station station = new Station();
        station.setDate(Calendar.getInstance().getTime());
        Device dev1 = new Device();
        dev1.name = "dev1";
        dev1.status = DevStatus.Normal;
        Device dev2 = new Device();
        dev2.name = "dev2";
        dev2.status = DevStatus.Error;
        station.addDevice(dev1);
        station.addDevice(dev2);

        // write as string
        String jsonString = mapper.writeValueAsString(station);
        System.out.println(jsonString);
        
        Station station2 =
            mapper.readValue(jsonString, Station.class);
        System.out.println(station2);
    }

}
