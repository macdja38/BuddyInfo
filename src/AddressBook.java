import javax.swing.*;
import java.util.List;
import java.util.stream.*;

public class AddressBook {
    DefaultListModel<BuddyInfo> buddies;

    AddressBook() {
        buddies = new DefaultListModel<>();
    }

    DefaultListModel<BuddyInfo> getDefaultListModel() {
        return buddies;
    }

    List<BuddyInfo> getBuddies() {
        return Stream.of(buddies.toArray()).map(o -> (BuddyInfo) o).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join("\n", getBuddies().stream().map(Object::toString).collect(Collectors.toList()));
    }

    public int size() {
        return buddies.size();
    }

    public void clear() {
        buddies.clear();
    }

    BuddyInfo remove(int index) {
        return buddies.remove(index);
    }

    void add(BuddyInfo buddy) {
        buddies.addElement(buddy);
    }
}
