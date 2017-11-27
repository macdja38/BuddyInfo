import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class BuddyInfoTest {
    @Test
    void testToString() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        assertEquals("name: foo\naddress: address\n", buddyInfo.toString());
    }

    @Test
    void testGetPhoneNumbers() {
        ArrayList<Integer> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(1234567);
        BuddyInfo buddyInfo = new BuddyInfo(phoneNumbers, "name", "address", "relation");
        assertArrayEquals(phoneNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
    }

    @Test
    void testSetPhoneNumbers() {
        ArrayList<Integer> phoneNumbers = new ArrayList<>();
        phoneNumbers.add(1234567);
        BuddyInfo buddyInfo = new BuddyInfo(phoneNumbers, "name", "address", "relation");
        assertArrayEquals(phoneNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
        ArrayList<Integer> alternateNumbers = new ArrayList<>();
        alternateNumbers.add(987654321);
        buddyInfo.setPhoneNumbers(alternateNumbers);
        assertArrayEquals(alternateNumbers.toArray(), buddyInfo.getPhoneNumbers().toArray());
    }

    @Test
    void testGetName() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "bar");
        assertEquals("foo", buddyInfo.getName());
    }

    @Test
    void testSetName() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        buddyInfo.setName("bar");
        assertEquals("bar", buddyInfo.getName());
    }

    @Test
    void testGetAddress() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals("address", buddyInfo.getAddress());
    }

    @Test
    void testSetAddress() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals("address", buddyInfo.getAddress());
        buddyInfo.setAddress("alternate address");
        assertEquals("alternate address", buddyInfo.getAddress());
    }

    @Test
    void testGetRelation() {
        BuddyInfo buddyInfo = new BuddyInfo("foo", "address");
        assertNull(buddyInfo.getRelation());
    }

    @Test
    void testSetRelation() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setRelation("bar");
        assertEquals("bar", buddyInfo.getRelation());
    }

    @Test
    void testUpdate() {
        BuddyInfo buddyInfo = new BuddyInfo("initialName", "initialAddress");
        buddyInfo.update("name", "address");
        assertEquals("name", buddyInfo.getName());
        assertEquals("address", buddyInfo.getAddress());
    }

    @Test
    void testGreeting() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "initialAddress");
        assertEquals("Hello name!", buddyInfo.getGreeting());
    }

    @Test
    void testCopyConstructor() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        BuddyInfo buddyCopy = new BuddyInfo(buddyInfo);
        assertEquals(buddyInfo.getName(), buddyCopy.getName());
        assertEquals(buddyInfo.getAddress(), buddyCopy.getAddress());
        assertEquals(buddyInfo.getPhoneNumbers(), buddyCopy.getPhoneNumbers());
        assertEquals(buddyInfo.getRelation(), buddyCopy.getRelation());
    }

    @Test
    void testGetAgeZero() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        assertEquals(0, buddyInfo.getAge());
    }

    @Test
    void testSetAge() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(5);
        assertEquals(5, buddyInfo.getAge());
    }

    @Test
    void testOver18Under() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(5);
        assertFalse(buddyInfo.isOver18());
    }

    @Test
    void testOver18Over() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(25);
        assertTrue(buddyInfo.isOver18());
    }

    @Test
    void testOver18Exactly() {
        BuddyInfo buddyInfo = new BuddyInfo("name", "address");
        buddyInfo.setAge(18);
        assertFalse(buddyInfo.isOver18());
    }
}
