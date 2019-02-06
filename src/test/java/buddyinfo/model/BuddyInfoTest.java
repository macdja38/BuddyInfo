package buddyinfo.model;

import buddyinfo.model.BuddyInfo;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class BuddyInfoTest {
    @Test
    public void testToString() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        assertEquals("name: foo$address: address$", buddyInfo.toString());
    }

    @Test
    public void testGetPhoneNumbers() {
        ArrayList<Integer> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(1234567);
        BuddyInfo buddyInfo = new BuddyInfo(phoneNumbers, "name", "address", "relation");
        assertArrayEquals(phoneNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
    }

    @Test
    public void testSetPhoneNumbers() {
        ArrayList<Integer> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(1234567);
        BuddyInfo buddyInfo = new BuddyInfo(phoneNumbers, "name", "address", "relationship");
        assertArrayEquals(phoneNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
        ArrayList<Integer> alternateNumbers = new ArrayList<>();
        alternateNumbers.add(987654321);
        buddyInfo.setPhoneNumbers(alternateNumbers);
        assertArrayEquals(alternateNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
    }

    @Test
    public void testGetName() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "bar");
        assertEquals("foo", buddyInfo.getName());
    }

    @Test
    public void testSetName() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        buddyInfo.setName("bar");
        assertEquals("bar", buddyInfo.getName());
    }

    @Test
    public void testGetAddress() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals("address", buddyInfo.getAddress());
    }

    @Test
    public void testSetAddress() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals("address", buddyInfo.getAddress());
        buddyInfo.setAddress("alternate address");
        assertEquals("alternate address", buddyInfo.getAddress());
    }

    @Test
    public void testGetRelation() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        assertNull(buddyInfo.getRelation());
    }

    @Test
    public void testSetRelation() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setRelation("bar");
        assertEquals("bar", buddyInfo.getRelation());
    }

    @Test
    public void testUpdate() {
        BuddyInfo buddyInfo = new BuddyInfo("initialName", "initialAddress");
        buddyInfo.update("name", "address");
        assertEquals("name", buddyInfo.getName());
        assertEquals("address", buddyInfo.getAddress());
    }

    @Test
    public void testGreeting() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "initialAddress");
        assertEquals("Hello name!", buddyInfo.getGreeting());
    }

    @Test
    public void testCopyConstructor() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        BuddyInfo buddyCopy = new BuddyInfo(buddyInfo);
        assertEquals(buddyInfo.getName(), buddyCopy.getName());
        assertEquals(buddyInfo.getAddress(), buddyCopy.getAddress());
        assertEquals(buddyInfo.getPhoneNumbers(), buddyCopy.getPhoneNumbers());
        assertEquals(buddyInfo.getRelation(), buddyCopy.getRelation());
    }

    @Test
    public void testGetAgeZero() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals(0, buddyInfo.getAge());
    }

    @Test
    public void testSetAge() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(5);
        assertEquals(5, buddyInfo.getAge());
    }

    @Test
    public void testOver18Under() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(5);
        assertFalse(buddyInfo.isOver18());
    }

    @Test
    public void testOver18Over() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(25);
        assertTrue(buddyInfo.isOver18());
    }

    @Test
    public void testOver18Exactly() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(18);
        assertFalse(buddyInfo.isOver18());
    }
}
