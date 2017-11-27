import javax.swing.*;
import java.awt.*;

public class View extends JFrame {
    private JMenuItem jMenuItemDisplay;
    private JMenuItem jMenuItemSave;
    private JMenuItem jMenuItemCreate;
    private AddressBook addressBook;
    private JMenuBar jMenuBar;
    private JMenuItem jMenuItemAddContact;
    private Controller controller;
    private JMenu jMenuEdit;

    View(Controller controller) {
        this.controller = controller;
    }

    void showEditPopup(String name, String address, EditInterface onComplete) {
        JTextField nameField = new JTextField(name);
        JTextField addressField = new JTextField(address);

        JPanel jPanel = new JPanel();
        jPanel.add(new JLabel("name"));
        jPanel.add(nameField);
        jPanel.add(new JLabel("address"));
        jPanel.add(addressField);

        int result = JOptionPane.showConfirmDialog(null, jPanel, "Please do something", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            onComplete.update(nameField.getText(), addressField.getText());
        }
    }

    void showCreate(boolean enable) {
        jMenuItemCreate.setEnabled(enable);
    }

    void showSave(boolean enable) {
        jMenuItemCreate.setEnabled(enable);
    }

    void showEdit(boolean enable) {
        jMenuEdit.setEnabled(enable);
    }

    void showBuddyMethods(AddressBook addressBook) {
        jMenuItemCreate.setEnabled(false);

        JMenu jMenuAddress = new JMenu("Address Book");

        jMenuBar.add(jMenuAddress);

        jMenuItemAddContact = new JMenuItem("Add Contact");
        jMenuAddress.add(jMenuItemAddContact);
        jMenuItemAddContact.addActionListener(e -> this.controller.addContact());

        JList<BuddyInfo> list = new JList<>(addressBook.getDefaultListModel()); //data has type Object[]
        list.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        list.setLayoutOrientation(JList.HORIZONTAL_WRAP);
        list.setVisibleRowCount(-1);
        list.addListSelectionListener(e -> this.controller.itemSelected(list, e));

        JScrollPane listScroller = new JScrollPane(list);
        listScroller.setPreferredSize(new Dimension(250, 80));

        add(list);

        revalidate();
        repaint();
    }

    public void initUI() {
        setSize(400, 400);
        setTitle("Buddy Info");
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        jMenuBar = new JMenuBar();
        setJMenuBar(jMenuBar);

        JMenu jMenu = new JMenu("Menu");
        jMenuBar.add(jMenu);

        jMenuItemCreate = new JMenuItem("Create");
        jMenu.add(jMenuItemCreate);
        jMenuItemCreate.addActionListener(e -> this.controller.create());

        jMenuItemSave = new JMenuItem("Save");
        jMenu.add(jMenuItemSave);
        jMenuItemSave.addActionListener(e -> this.controller.save());

        jMenuEdit = new JMenu("edit");
        jMenuBar.add(jMenuEdit);
        showEdit(false);

        JMenuItem jMenuItemDelete = new JMenuItem("delete");
        jMenuEdit.add(jMenuItemDelete);
        jMenuItemDelete.addActionListener(e -> this.controller.delete());

        JMenuItem jMenuItemEdit = new JMenuItem("edit");
        jMenuEdit.add(jMenuItemEdit);
        jMenuItemEdit.addActionListener(e -> this.controller.edit());

        setVisible(true);
    }
}
