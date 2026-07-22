import java.util.*;

//========================================
// CONTACT CLASS
//========================================
class Contact {

    private int id;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String category;
    private boolean favorite;

    // Constructor
    public Contact(int id, String name, String phone,
                   String email, String address,
                   String category) {

        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.category = category;
        this.favorite = false;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory() {
        return category;
    }

    public boolean isFavorite() {
        return favorite;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    // Display Contact
    public void displayContact() {

        System.out.println("-----------------------------------------");
        System.out.println("ID        : " + id);
        System.out.println("Name      : " + name);
        System.out.println("Phone     : " + phone);
        System.out.println("Email     : " + email);
        System.out.println("Address   : " + address);
        System.out.println("Category  : " + category);
        System.out.println("Favorite  : " + (favorite ? "Yes" : "No"));
        System.out.println("-----------------------------------------");
    }
}

//========================================
// CONTACT MANAGER CLASS
//========================================
class ContactManager {

    private ArrayList<Contact> contacts = new ArrayList<>();
    private static int nextId = 1;

    // Get Next ID
    public static int getNextId() {
        return nextId++;
    }

    // Add Contact
    public void addContact(Contact contact) {

        contacts.add(contact);

        System.out.println("\nContact Added Successfully!");
    }

    // Search by Category
    public void searchByCategory(String category) {

        boolean found = false;

        System.out.println("\n========== CONTACTS IN CATEGORY: " + category.toUpperCase() + " ==========");

        for (Contact c : contacts) {

            if (c.getCategory().equalsIgnoreCase(category)) {

                c.displayContact();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Contacts Found in this Category.");
        }
    }

    // Display All Contacts
    public void displayContacts() {

        if (contacts.isEmpty()) {
            System.out.println("\nNo Contacts Available.");
            return;
        }

        System.out.println("\n============= CONTACT LIST =============");

        for (Contact c : contacts) {
            c.displayContact();
        }
    }

    // Search by Name
    public Contact searchByName(String name) {

        for (Contact c : contacts) {

            if (c.getName().equalsIgnoreCase(name)) {
                return c;
            }
        }

        return null;
    }

    // Search by Phone
    public Contact searchByPhone(String phone) {

        for (Contact c : contacts) {

            if (c.getPhone().equals(phone)) {
                return c;
            }
        }

        return null;
    }

    // Get Contact List
    public ArrayList<Contact> getContacts() {
        return contacts;
    }

    // Update Contact
    public void updateContact(Scanner sc) {

        if (contacts.isEmpty()) {
            System.out.println("\nNo Contacts Available!");
            return;
        }

        System.out.print("\nEnter Contact ID to Update : ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Contact c : contacts) {

            if (c.getId() == id) {

                System.out.print("Enter New Name : ");
                c.setName(sc.nextLine());

                System.out.print("Enter New Phone : ");
                c.setPhone(sc.nextLine());

                System.out.print("Enter New Email : ");
                c.setEmail(sc.nextLine());

                System.out.print("Enter New Address : ");
                c.setAddress(sc.nextLine());

                System.out.print("Enter New Category : ");
                c.setCategory(sc.nextLine());

                System.out.println("\nContact Updated Successfully!");
                return;
            }
        }

        System.out.println("\nContact Not Found!");
    }

    // Delete Contact
    public void deleteContact(Scanner sc) {

        if (contacts.isEmpty()) {
            System.out.println("\nNo Contacts Available!");
            return;
        }

        System.out.print("\nEnter Contact ID to Delete : ");
        int id = sc.nextInt();

        Iterator<Contact> iterator = contacts.iterator();

        while (iterator.hasNext()) {

            Contact c = iterator.next();

            if (c.getId() == id) {

                iterator.remove();

                System.out.println("\nContact Deleted Successfully!");
                return;
            }
        }

        System.out.println("\nContact Not Found!");
    }

    // Mark as Favorite
    public void markFavorite(Scanner sc) {

        if (contacts.isEmpty()) {
            System.out.println("\nNo Contacts Available!");
            return;
        }

        System.out.print("\nEnter Contact ID : ");
        int id = sc.nextInt();

        for (Contact c : contacts) {

            if (c.getId() == id) {

                c.setFavorite(true);

                System.out.println("\nAdded to Favorite!");
                return;
            }
        }

        System.out.println("\nContact Not Found!");
    }

    // Display Favorites
    public void displayFavorites() {

        boolean found = false;

        System.out.println("\n========== FAVORITE CONTACTS ==========");

        for (Contact c : contacts) {

            if (c.isFavorite()) {

                c.displayContact();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No Favorite Contacts Found.");
        }
    }

    // Sort Contacts by Name
    public void sortContacts() {

        if (contacts.isEmpty()) {
            System.out.println("\nNo Contacts Available!");
            return;
        }

        Collections.sort(contacts, new Comparator<Contact>() {

            @Override
            public int compare(Contact c1, Contact c2) {

                return c1.getName().compareToIgnoreCase(c2.getName());
            }
        });

        System.out.println("\nContacts Sorted Successfully!");
    }
}

//========================================
// MAIN CLASS
//========================================
public class ContactManagementSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ContactManager manager = new ContactManager();

        while (true) {

            System.out.println("\n========================================");
            System.out.println("   CONTACT MANAGEMENT SYSTEM");
            System.out.println("========================================");
            System.out.println("1. Add Contact");
            System.out.println("2. Display All Contacts");
            System.out.println("3. Search Contact by Name");
            System.out.println("4. Search Contact by Phone");
            System.out.println("5. Search Contact by Category");
            System.out.println("6. Update Contact");
            System.out.println("7. Delete Contact");
            System.out.println("8. Mark as Favorite");
            System.out.println("9. Display Favorite Contacts");
            System.out.println("10. Sort Contacts by Name");
            System.out.println("11. Exit");
            System.out.println("========================================");
            System.out.print("Enter Your Choice : ");

            int choice;
            try {
                choice = sc.nextInt();
                sc.nextLine();
            } catch (Exception e) {
                System.out.println("\nInvalid Input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            switch (choice) {

                case 1:
                    System.out.println("\n--- ADD NEW CONTACT ---");
                    System.out.print("Enter Name : ");
                    String name = sc.nextLine();

                    System.out.print("Enter Phone : ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Email : ");
                    String email = sc.nextLine();

                    System.out.print("Enter Address : ");
                    String address = sc.nextLine();

                    System.out.print("Enter Category (Family/Friends/Work/Other) : ");
                    String category = sc.nextLine();

                    Contact contact = new Contact(ContactManager.getNextId(), name, phone, email, address, category);
                    manager.addContact(contact);
                    break;

                case 2:
                    manager.displayContacts();
                    break;

                case 3:
                    System.out.print("\nEnter Name to Search : ");
                    String searchName = sc.nextLine();
                    Contact foundByName = manager.searchByName(searchName);
                    if (foundByName != null) {
                        System.out.println("\n--- CONTACT FOUND ---");
                        foundByName.displayContact();
                    } else {
                        System.out.println("\nContact Not Found!");
                    }
                    break;

                case 4:
                    System.out.print("\nEnter Phone to Search : ");
                    String searchPhone = sc.nextLine();
                    Contact foundByPhone = manager.searchByPhone(searchPhone);
                    if (foundByPhone != null) {
                        System.out.println("\n--- CONTACT FOUND ---");
                        foundByPhone.displayContact();
                    } else {
                        System.out.println("\nContact Not Found!");
                    }
                    break;

                case 5:
                    System.out.print("\nEnter Category to Search : ");
                    String searchCategory = sc.nextLine();
                    manager.searchByCategory(searchCategory);
                    break;

                case 6:
                    manager.updateContact(sc);
                    break;

                case 7:
                    manager.deleteContact(sc);
                    break;

                case 8:
                    manager.markFavorite(sc);
                    break;

                case 9:
                    manager.displayFavorites();
                    break;

                case 10:
                    manager.sortContacts();
                    manager.displayContacts();
                    break;

                case 11:
                    System.out.println("\nThank You for Using Contact Management System!");
                    sc.close();
                    System.exit(0);

                default:
                    System.out.println("\nInvalid Choice! Please try again.");
            }
        }
    }
}
