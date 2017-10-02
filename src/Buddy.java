/**
 * Buddy
 */
public class Buddy {
    private Array<Int> phoneNumbers;
    private String name;
    private String address;
    private String relation;

    public Buddy(Array<Int> phoneNumbers, String name, String address, String relation) {
        this.phoneNumbers = phoneNumbers;
        this.name = name;
        this.address = address;
        this.relation = relation;
    }

    public Array<Int> getPhoneNumbers() {
        return phoneNumbers;
    }

    public void setPhoneNumbers(Array<Int> phoneNumbers) {
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
}
