package com.ume.test.jackson;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Station {

    private Date date;
    private Map<String, Device> devices = new HashMap<String, Device>();

    public void addDevice(Device dev) {
        devices.put(dev.name, dev);
    }

    public Map<String, Device> getDevices() {
        return devices;
    }

    public void setDevices(HashMap<String, Device> devs) {
        for (String key : devs.keySet()) {
            devices.put(key, devs.get(key));
        }
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date d) {
        date = d;
    }

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("Station{ ");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append(format.format(date));
        sb.append(" ");
        for (Entry<String, Device> entry : devices.entrySet()) {
            sb.append(entry);
            sb.append(", ");
        }
        sb.append(" }");
        return sb.toString();
    }
}

enum DevStatus {
    Normal,
    Error
}

class Device {
    public String name;
    public DevStatus status;

    @Override
    public String toString() {
        return String.format("Dev{%s, %s}", name, status);
    }
}
