package buddyinfo.model;

import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.*;

@Entity
public class AddressBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    List<BuddyInfo> buddies;

    public AddressBook() {
        buddies = new ArrayList<>();
    }

    public List<BuddyInfo> getDefaultListModel() {
        return buddies;
    }

    public List<BuddyInfo> getBuddies() {
        return Stream.of(buddies.toArray()).map(o -> (BuddyInfo) o).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return String.join("\n", getBuddies().stream().map(Object::toString).collect(Collectors.toList()));
    }

    public String export() {
        return toString();
    }

    public int size() {
        return buddies.size();
    }

    public void clear() {
        buddies.clear();
    }

    public BuddyInfo remove(int index) {
        return buddies.remove(index);
    }

    public void add(BuddyInfo buddy) {
        buddies.add(buddy);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
