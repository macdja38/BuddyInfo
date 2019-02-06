package buddyinfo;

import buddyinfo.model.AddressBook;
import buddyinfo.model.BuddyInfo;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AddressBookTest {
    @Test
    public void defaultListLengthIsZero() {
        AddressBook addressBook = new AddressBook();
        assertEquals(0, addressBook.size());
    }

    @Test
    public void testAddingBuddyIncreasesSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.add(new BuddyInfo("name", "address"));
        assertEquals(1, addressBook.size());
    }

    @Test
    public void testClearingBuddyListZerosSize() {
        AddressBook addressBook = new AddressBook();
        addressBook.add(new BuddyInfo("name", "address"));
        addressBook.clear();
        assertEquals(0, addressBook.size());
    }

    @Test
    public void checkDefaultToString() {
        AddressBook addressBook = new AddressBook();
        assertEquals("", addressBook.toString());
    }

    @Test
    void addBuddy() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddyInfo = new BuddyInfo("Fred", "Flinstone");
        addressBook.add(buddyInfo);
        assertTrue(addressBook.getBuddies().contains(buddyInfo));
    }

    @Test
    void removeBuddy() {
        AddressBook addressBook = new AddressBook();
        BuddyInfo buddyInfo = new BuddyInfo("Fred", "Flinstone");
        assertEquals(0, addressBook.getBuddies().size());
        addressBook.add(buddyInfo);
        assertTrue(addressBook.getBuddies().contains(buddyInfo));
        assertEquals(1, addressBook.getBuddies().size());
        addressBook.remove(0);
        assertEquals(0, addressBook.getBuddies().size());
        assertFalse(addressBook.getBuddies().contains(buddyInfo));
    }

    @Test
    public void checkOneEntryTooString() {
        AddressBook addressBook = new AddressBook();
        addressBook.add(new BuddyInfo("john", "fake"));
        assertEquals(
                "name: john$" +
                        "address: fake$",
                addressBook.toString()
        );
    }

    @Test
    public void checkTwoEntryToString() {
        AddressBook addressBook = new AddressBook();
        addressBook.add(new BuddyInfo("john", "fake"));
        addressBook.add(new BuddyInfo("second", "john"));
        assertEquals("name: john$" +
                        "address: fake$" +
                        "\n" +
                        "name: second$" +
                        "address: john$",
                addressBook.toString()
        );
    }

    @Test
    public void getDefaultListModel() {
        AddressBook addressBook = new AddressBook();
        List buddies = addressBook.getDefaultListModel();
    }
}
