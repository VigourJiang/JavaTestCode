package beanutils;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Employee {
    private Map<String, Address> addresses = new HashMap<String, Address>();
    private List<Employee> subordinates = new ArrayList<Employee>();
    private String firstName;
    private String lastName;

    public Address getAddress(String type) {
        return addresses.get(type);
    }

    public void setAddress(String type, Address address) {
        if (address == null) {
            return;
        }
        addresses.put(type, address);
    }

    public Employee getSubordinate(int index) {
        return subordinates.get(index);
    }

    public void setSubordinate(int index, Employee subordinate) {
        if (this.subordinates.size() > index) {
            this.subordinates.set(index, subordinate);
        } else {
            this.subordinates.add(subordinate);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
