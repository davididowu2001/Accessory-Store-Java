package functions;

import java.io.File;  // Import the File class

import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import products.*;
import users.Customer;
import users.User;
import gui.AddToB;

public class Functions {


    //function to read from accounts file

    private static List<Product> products = new ArrayList<Product>();

    public  static List<Product> readProducts() throws FileNotFoundException {
        // function to read from stock file
        products.clear();
        File UserAccounts = new File("src\\functions\\Stock");
        Scanner reader = new Scanner(UserAccounts);
        Product product;
        
        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] splitdata = data.split(", ");//splits the list by ','
            int barcode = Integer.parseInt(splitdata[0]);
            String name = splitdata [1];//assigns name to index 1
            String type = splitdata[2];//assigns type to index2
            String brand = splitdata[3];//assigns brand to index 3
            String colour = splitdata[4];//assings color to index 4
            String connectivity = splitdata[5];//assigns connectivity to index 5
            int quantity_in_stock = Integer.parseInt(splitdata[6]);//index6
            float original_cost = Float.parseFloat(splitdata[7]);//index7
            float retail_price = Float.parseFloat(splitdata[8]);//index8
            String add_info = splitdata[9];//assigns info to index 9

            if ("keyboard".equals(name)) {
            	//if the product is a keyboards, instantiate it
                product = new Keyboard(
                        barcode, name, type, brand, colour, connectivity, quantity_in_stock,
                        original_cost, retail_price, add_info
                );
            }

            else {
            	//if product is a mouse, instantiate it
                product = new Mouse(
                 barcode, name, type, brand, colour,  connectivity,
                 quantity_in_stock,  original_cost, retail_price, add_info
                );
            }

            products.add(product);
        }
        reader.close();
        return products;
    }



    public static List<User> readUserAccounts() throws FileNotFoundException {
    	//function to read from useraccount
        List<User> users = new ArrayList<User>();
        File UserAccounts = new File("src\\functions\\UserAccounts");
        Scanner reader = new Scanner(UserAccounts);
        User user;

        while (reader.hasNextLine()) {
            String data = reader.nextLine();
            String[] splitdata = data.split(", ");//splits by ','
            int userId = Integer.parseInt(splitdata[0]);//first index is the userid
            String userName = splitdata [1];//second is username
            String name = splitdata[2];//third is name
            int house_number = Integer.parseInt(splitdata[3]);//4th is house number
            String postcode = splitdata[4];//5th is postcode
            String city = splitdata[5];//6th is city
            String role = splitdata[6];//7th is role
            if ("customer".equals(role)) {
            	//if user is a customer, then instantiate
                user = new User(
                        userId, userName, name, house_number, postcode,city, role
                );
            }

            else {
            	//else, just instantiate
                user = new Customer(
                        userId, userName, name, house_number, postcode,city, role
                );
            }

            users.add(user);
        }
        reader.close();
        return users;
    }

    public static void addProduct() throws IOException{
    	//function to write into stock file, to add products to it
        FileWriter writer= new FileWriter("src\\functions\\Stock",true);
        String format = AddToB.listOfProducts().toString().toLowerCase().substring(1);
        format= format.substring(0,format.length()-1 );
        System.out.println(format);
        writer.write(System.lineSeparator());//write in next line
        writer.write(format);
        writer.close();
    }

    public static void writeProduct(List<Product> productsToWrite) throws IOException{
    	//function to write into the stock file , when quantity has been changed
        FileWriter writer = new FileWriter("src\\functions\\Stock");
       
        for (Product product :productsToWrite) {
            
                String producer = product.getBarcode()+", "+ product.getName()+", "+product.getType()+", "+product.getBrand()+", "
                +product.getColour()+", "+product.getConnectivity()+", "+product.getQuantity_in_stock()+", "+product.getOriginal_cost()+", "+product.getRetail_price()+", "
                +product.getAdditional_info()+"\n";
                writer.write(producer);      
        }
          
          writer.close();
            }
          
    public static List <Product> getlistOfProducts(){
        return products;

    }


}
