package buddyinfo.model;

import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.*;

/**
 * Buddy
 */
@Entity
public class BuddyInfo implements EditInterface {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private ArrayList<Integer> phoneNumbers;
    private String name;
    private String address;
    private String relation;
    private int age;

    public BuddyInfo(ArrayList<Integer> phoneNumbers, String name, String address, String relation) {
        this.phoneNumbers = phoneNumbers;
        this.name = name;
        this.address = address;
        this.relation = relation;
    }

    public static BuddyInfo importMethod(String thing) {
        int i = 0;
        String name = "";
        String address = "";
        String relation = "";
        String age = "";
        for (String str: thing.split("\\$")) {
            switch(i) {
                case 0:
                    name = str;
                case 1:
                    address = str;
            }
            i++;
        }
        return new BuddyInfo(name, address);
    }

    public BuddyInfo() {

    }

    public BuddyInfo(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public BuddyInfo(BuddyInfo buddyInfo) {
        this.name = buddyInfo.getName();
        this.address = buddyInfo.getAddress();
        this.relation = buddyInfo.getRelation();
        this.phoneNumbers = buddyInfo.getPhoneNumbers();
    }

    public String getGreeting() {
        return "Hello " + getName() + "!";
    }

    public String toString() {
        return "name: " + this.name + "$"
                + "address: " + this.address + "$"
                + (this.relation != null ? "relation: " + this.relation + "$" : "")
                + (this.phoneNumbers != null ? "phone numbers:" + String.join(",", phoneNumbers.stream().map(Object::toString).collect(Collectors.toList())) : "");
    }

    public ArrayList<Integer> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(ArrayList<Integer> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    @Override
    public void update(String name, String address) {
        this.setName(name);
        this.setAddress(address);
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isOver18() {
        return age > 18;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
