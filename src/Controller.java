import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Controller {
    AddressBook addressBook;
    View view;
    private int targetIndex;
    private BuddyInfo targetValue;

    public static void main(String args[]) {
        Controller controller = new Controller();
        controller.init();
    }

    void init() {
        addressBook = new AddressBook();
        view = new View(this);
        view.initUI();
    }

    void create() {
        view.showBuddyMethods(addressBook);
        view.showCreate(false);
        view.showSave(true);
    }

    void addContact() {
        view.showEditPopup("name", "address", (String name, String address) -> {
            BuddyInfo buddy = new BuddyInfo(new ArrayList<>(), name, address, "friend");
            addressBook.add(buddy);
        });
    }

    void delete() {
        addressBook.remove(targetIndex);
    }

    void edit() {
        view.showEditPopup(targetValue.getName(), targetValue.getAddress(), targetValue);
    }

    void itemSelected(JList list, ListSelectionEvent e) {
        if (!e.getValueIsAdjusting()) {
            if (list.getSelectedIndex() == -1) {
                //No selection, disable fire button.
                view.showEdit(false);
            } else {
                //Selection, enable the fire button.
                view.showEdit(true);
                targetIndex = list.getSelectedIndex();
                targetValue = (BuddyInfo) list.getSelectedValue();
            }
        }
    }

    void save() {
        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(String.join("", addressBook.toString()));
            printWriter.close();
        } catch (IOException error) {
            System.err.println(error);
        }
    }
}
