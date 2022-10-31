package gui;

import java.io.FileNotFoundException;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import products.Product;
import users.Customer;
import users.User;

import java.lang.Math;
import java.text.DecimalFormat;


import javax.swing.*;

public final class WindowManager {//WindowManager is a class that manages all other windows
    private enum ManagerState {LOGIN_STATE, SHOPPING_STATE, BASKET_STATE,PAYMENT_STATE,AddToBask_STATE};//every state to be transitioned
    private static User current_user;//user that is logged on
    private static ManagerState state = ManagerState.LOGIN_STATE;//login state
    private static List<Product> basket = new ArrayList<Product>();//basket is a list of products

    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
   
        getFrame();
    }

    private static void getFrame() throws FileNotFoundException {
    	/* loads the log in frame*/
        switch (WindowManager.state) {
            case LOGIN_STATE:
                new LogIn();
                break;

            case SHOPPING_STATE:
            	//loads the shopping window
                new Shoppingwindow(current_user.getRole().equals("customer"));
                break;

            case BASKET_STATE:
            	//loads the basket window
                new Basket(basket, current_user.getRole().equals("customer"));
                break;
            case PAYMENT_STATE:
            	//loads the payment window
                new Pay();
                break;   
            default:
            case AddToBask_STATE:
            	//loads the add product window. I mistook the name here
                new AddToB();
                break;
        }
    }

//functions
    public static void signal_login(User user) throws FileNotFoundException {
    	//function is called to open the shopping window when its a user
        state = ManagerState.SHOPPING_STATE;
        current_user = user;
        getFrame();//open the frame
    }
    public static void addToBasket(Product product){
    	//function called to add an item to basket
        int current_quantity;
        WindowManager.basket.add(product);
//        System.out.println(WindowManager.basket);
//        System.out.println(product.getBarcode());
        current_quantity= product.quantity_bought();
        product.set_quantity_bought(current_quantity+1); //increases quantity bought if its bought multiple times
//        System.out.println(product.quantity_bought());
    }

    public static void Basketwindow() throws FileNotFoundException{
        state = ManagerState.BASKET_STATE;    //opens the basket window
        getFrame();
    }
    public static void clearBasket(){//clears item in basket
        basket.clear();
    }
    public static List<Product> returnBasket(){
    	//returns items in basket
        return basket;
    }
    public static void backtoShop() throws FileNotFoundException{
        state = ManagerState.SHOPPING_STATE;
        //function called by a button to go back to Shopping window
        getFrame();
        
    }
    public static void backToLogin() throws FileNotFoundException{
        state = ManagerState.LOGIN_STATE;
        //function to go back to login page
        clearBasket();//clears basket
        getFrame();
    }
    public static void openPay() throws FileNotFoundException{
        state = ManagerState.PAYMENT_STATE;
        //function to go to pay page
        getFrame();
    }
    public static void backTOBasket() throws FileNotFoundException{
        state = ManagerState.BASKET_STATE;
        //opens the basket page
        getFrame();
    }
    public static float totalorder(){
    	//function created to get the total price of items in the basket
        //loop through the basket price colum and sum values
        float total = 0;
        for(Product i: basket){
           float  p=i.getRetail_price();
            total +=p;
            
        }
        System.out.println( total);
       
        return total;
    
    }
    public static String userAddress(){
    	//function to create the user Address
       String address= current_user.getHouse_number()+" "+current_user.getPostcode()+" "+current_user.getCity();
    return address;

        }
    public static void goToAddwindow() throws FileNotFoundException {
    	//function to go to addto product window
        state = ManagerState.AddToBask_STATE;
        getFrame();

    }
    }

    

