package demo;
import java.util.*;

public class BakeryManagementSystem {
    private List<Product> products;
    private List<Order> orders;
//    private LoginSystem loginSystem;
    
    //Login Page using Array of String
    static String[] usernames = {"admin","user1","user2"};
    static String[] passwords = {"root","root1","root2"};

    public BakeryManagementSystem() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
      //  loginSystem = new LoginSystem();
    }
    
    public void run() {
    	
    	Scanner scanner = new Scanner(System.in);
    	System.out.println("Welcome to the Bakery Management System");
        
        // Loop to allow multiple login attempts
        for (int i = 0; i < 3; i++) {
            System.out.print("Username: ");
            String usernameInput = scanner.nextLine();
            System.out.print("Password: ");
            String passwordInput = scanner.nextLine();
            
            // Check if username and password match
            if (checkLogin(usernameInput, passwordInput)) {
                System.out.println("Login successful!");
                System.out.println("Welcome, " + usernameInput + "!");
                startBakeryManagement();
                break; // Exit loop if login successful
            } else {
                System.out.println("Invalid username or password. Please try again.");
            }
        }
    }
    static boolean checkLogin(String username, String password) {
        for (int i = 0; i < usernames.length; i++) {
            if (username.equals(usernames[i]) && password.equals(passwords[i])) {
                return true;
            }
        }
        return false; 
    }
    private void startBakeryManagement() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Bakery Management System Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Display Products");
            System.out.println("3. Place Order");
            System.out.println("4. Display Orders");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add Product
                    addProduct(getProductDetails());
                    break;
                case 2:
                    // Display Products
                    displayProducts();
                    break;
                case 3:
                    // Place Order
                    placeOrder();
                    break;
                case 4:
                    // Display Orders
                    displayOrders();
                    break;
                case 5:
                    // Exit
                    System.out.println("Exiting Bakery Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    private Product getProductDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product details:");
        System.out.print("Name: ");
        String name = scanner.next();
        name = name.toUpperCase();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        return new Product(name, price);
    }

    private void addProduct(Product product) {
    	
    	 for (Product i : products) {
    	        if (i.getName().equals(product.getName())) {
    	            System.out.println("ERROR product already exists! so cann't Add");
    	            return;
    	        }
    	    }
        products.add(product);
        System.out.println("Product added successfully!");
    }

    private void displayProducts() {
        System.out.println("	Available Products:		");
        for (Product product : products) {
            System.out.println("	"+product.getName() + " ₹ " + product.getPrice());
        }
    }

    private void placeOrder() {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order();

        System.out.println("Enter the number of items you want to order:");
        int itemCount = scanner.nextInt();

        for (int i = 0; i < itemCount; i++) {
            System.out.println("Enter product name for item " + (i + 1) + ":");
            String productName = scanner.next();
            Product selectedProduct = findProduct(productName);

            if (selectedProduct != null) {
                System.out.println("Enter quantity for item " + (i + 1) + ":");
                int quantity = scanner.nextInt();
                OrderItem item = new OrderItem(selectedProduct, quantity);
                order.addItem(item);
            } else {
                System.out.println("Product not found. Please check the product name.");
                i--;  // Retry the current item
            }
        }

        orders.add(order);
        System.out.println("Order placed successfully!");
    }

    private Product findProduct(String productName) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    private void displayOrders() {
        System.out.println("	Orders:");
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            System.out.println("	Order " + (i + 1) + ":");
            for (OrderItem item : order.getItems()) {
                System.out.println("	"+item.getProduct().getName() + " - ₹ " + item.getProduct().getPrice() +
                        " x " + item.getQuantity());
            }
            System.out.println("	Total: - ₹" + order.calculateTotal());
            System.out.println();
        }
    }
}
