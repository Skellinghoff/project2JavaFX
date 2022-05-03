package com.example.project2JavaFX;

import com.example.project2JavaFX.Classes.*;
import com.example.project2JavaFX.Exceptions.NegativeStartingBalanceException;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class FileManagement {
    public static Customer[] getCustomers() throws IOException, ClassNotFoundException, InvocationTargetException {
        FileInputStream inStream = new FileInputStream("Customers.dat");

        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

        ArrayList<Customer> customers = new ArrayList<>();

        while (true) {
            try {
                Customer customer = (Customer) objectInputFile.readObject();
                customers.add(customer);
            } catch (EOFException e) {
                System.out.println("EOF Reached.");
                break;
            }
        }

        // Close the file.
        objectInputFile.close();
        System.out.println("The serialized objects were read from the Customers.dat file.");
        return customers.toArray(new Customer[0]);
    }

    public static void setExampleCustomers() throws IOException, NegativeStartingBalanceException {

        DecimalFormat dfMoney = new DecimalFormat("0.00");

        Customer[] customers = {
                new Customer(
                        new User("customer0", "Customer0!"),
                        new PersonalDetails("customer0", "customer0, 0000, 0000, 0000", "What was your childhood nickname?", "customer0"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "0000000000000000")),
                new Customer(
                        new User("customer1", "Customer1!"),
                        new PersonalDetails("customer1", "customer1, 1111, 1111, 1111", "What was your childhood nickname?", "customer1"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "1111111111111111")),
                new Customer(
                        new User("customer2", "Customer2!"),
                        new PersonalDetails("customer2", "customer2, 2222, 2222, 2222", "What was your childhood nickname?", "customer2"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "2222222222222222")),
                new Customer(
                        new User("customer3", "Customer3!"),
                        new PersonalDetails("customer3", "customer3, 3333, 3333, 3333", "What was your childhood nickname?", "customer3"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "3333333333333333")),
                new Customer(
                        new User("customer4", "Customer4!"),
                        new PersonalDetails("customer4", "customer4, 4444, 4444, 4444", "What was your childhood nickname?", "customer4"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "4444444444444444")),
                new Customer(
                        new User("customer5", "Customer5!"),
                        new PersonalDetails("customer5", "customer5, 5555, 5555, 5555", "What was your childhood nickname?", "customer5"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "5555555555555555")),
                new Customer(
                        new User("customer6", "Customer6!"),
                        new PersonalDetails("customer6", "customer6, 6666, 6666, 6666", "What was your childhood nickname?", "customer6"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "6666666666666666")),
                new Customer(
                        new User("customer7", "Customer7!"),
                        new PersonalDetails("customer7", "customer7, 7777, 7777, 7777", "What was your childhood nickname?", "customer7"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "7777777777777777")),
                new Customer(
                        new User("customer8", "Customer8!"),
                        new PersonalDetails("customer8", "customer8, 8888, 8888, 8888", "What was your childhood nickname?", "customer8"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "8888888888888888")),
                new Customer(
                        new User("customer9", "Customer9!"),
                        new PersonalDetails("customer9", "customer9, 9999, 9999, 9999", "What was your childhood nickname?", "customer9"),
                        new BankAccount(dfMoney.format(new Random().nextDouble(1000, 1000000)), "9999999999999999")),
        };


        // Create the stream objects.
        FileOutputStream outStream = new FileOutputStream("Customers.dat");
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

        // Write the serialized objects to the file.
        for (Customer customer : customers) {
            objectOutputFile.writeObject(customer);
        }

        // Close the file.
        objectOutputFile.close();

        System.out.println("The serialized objects were written to the Customers.dat file.");

    }

    public static void addCustomer(Customer customer) throws IOException {
        ArrayList<Customer> customerArrayList = getCustomersList();
        customerArrayList.add(customer);
        Customer[] customers = customerArrayList.toArray(new Customer[0]);

        FileOutputStream outStream = new FileOutputStream("Customers.dat");
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

        for (Customer c : customers) {
            objectOutputFile.writeObject(c);
        }

        objectOutputFile.close();
        System.out.println("Customer added.");
    }

    private static ArrayList<Customer> getCustomersList() throws IOException {
        FileInputStream inStream = new FileInputStream("Customers.dat");
        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);
        ArrayList<Customer> customers = new ArrayList<>();

        while (true) {
            try {
                Customer customer = (Customer) objectInputFile.readObject();
                customers.add(customer);
            } catch (EOFException e) {
                System.out.println("EOF Reached.");
                break;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        // Close the file.
        objectInputFile.close();
        System.out.println("The serialized objects were read from the Customers.dat file.");
        return customers;
    }

    public static void setProducts() throws IOException {
        FileOutputStream outStream = new FileOutputStream("Products.dat");
        ObjectOutputStream objectOutputFile = new ObjectOutputStream(outStream);

        Product products[] = {
                new Product(
                        "Nintendo Entertainment System",
                        "You can go back in time to the roots of video games with the Nintendo Entertainment System Deluxe gray console. This classic Nintendo is a legend from the 1980s. This NES system can make gaming simple and fun for all ages.",
                        172.32),
                new Product(
                        "Super Nintendo Entertainment System",
                        "Nintendo Super Nintendo is a console that provides hours of entertainment. Thanks to the integrated processor, this white NTSC-U/C (US/CA) console offers seamless gaming experience. In addition, the device helps you enjoy your favorite games every time.",
                        159.99),
                new Product(
                        "Nintendo 64",
                        "Enjoy hours of entertainment with this Nintendo 64 console. It is equipped with a reliable NEC VR4300 64-bit processor together with 4 MB RAM. In addition, this NTSC console features wired Internet connectivity and comes in charcoal grey.",
                        102.50),
                new Product(
                        "Nintendo GameCube",
                        "The Nintendo GameCube limited edition platinum console is compact and lightweight, making it easy to transport from room to room. It is powerful with its Gekko 485 MHz processor, meaning you get slick graphics and fast gameplay.",
                        199.99),
                new Product(
                        "Nintendo Wii",
                        "The Nintendo Wii is a home video game console in the color white. It was first released in 2006 by Nintendo and introduced the Wii Remote controller, which can be used as a handheld pointing device and which detects movements in three dimensions.",
                        134.99
                ),
                new Product(
                        "Nintendo Wii U",
                        "The Wii U Game pad breaks down barriers between you and your entertainment with a 6.2\" 16:9 LCD touch screen, motion control system, front-facing camera, microphone, stereo speakers, rumble feature, button controls and analog sticks.",
                        209.99
                ),
                new Product(
                       "Nintendo Switch",
                       "Get the gaming system that lets you play the games you want, wherever you are, however you like. Includes the Nintendo Switch console and Nintendo Switch dock in black, with contrasting left and right Joy‑Con controllers-one red, one blue.",
                        299.00
                ),
                new Product(
                        "Nintendo Game Boy",
                        "The state of the art compact video game system for portable, hand-held video action!",
                        79.99
                ),
                new Product(
                        "Nintendo GameBoy Advance SP",
                        "New illumination feature and sleek flip-screen design making it the most distinctively stylish, compact and portable Game Boy system ever.",
                        214.95
                ),
                new Product(
                        "Nintendo DS Lite",
                        "DS Lite doesn't just play DS games it also features an additional port for Game Boy Advance Game Pak",
                        184.99
                )
        };

        for (Product product : products) {
            objectOutputFile.writeObject(product);
        }
        objectOutputFile.close();

        System.out.println("The serialized objects were written to the Products.dat file.");
    }

    public static Product[] getProducts() throws IOException, ClassNotFoundException, InvocationTargetException {
        FileInputStream inStream = new FileInputStream("Products.dat");

        ObjectInputStream objectInputFile = new ObjectInputStream(inStream);

        ArrayList<Product> products = new ArrayList<>();

        while (true) {
            try {
                Product product = (Product) objectInputFile.readObject();
                products.add(product);
            } catch (EOFException e) {
                System.out.println("EOF Reached.");
                break;
            }
        }

        // Close the file.
        objectInputFile.close();
        System.out.println("The serialized objects were read from the Products.dat file.");
        return products.toArray(new Product[0]);
    }
}
