package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;
import javax.swing.text.DefaultEditorKit.DefaultKeyTypedAction;

import functions.Functions;
import products.Product;
import users.User;
import java.lang.Math;

import java.awt.event.KeyAdapter;

import java.awt.CardLayout;
public class Pay {
    JFrame frame = new JFrame();//new java frame
    JLabel label = new JLabel("SELECT PAYMENT METHOD");//label
    JPanel paypPanel = new JPanel(null);//paypal panel
    JPanel credPanel = new JPanel(null);//credit card panel
    JTextPane paypemail = new JTextPane();
    JButton checkout = new JButton("CHECKOUT");//checkout button
    JTextPane cardnum = new JTextPane();
    JTextPane securitycode = new JTextPane(); 
    CardLayout cardLayout = new CardLayout();
    JPanel host = new JPanel(cardLayout);
    JLabel emailJLabel =  new JLabel("Enter Email:");
    JLabel cardnumLabel = new JLabel("ENTER CARD NUMBER:");
    JLabel sec_codeJLabel = new JLabel("SECURITY CODE");
    JButton basket = new JButton("Basket");
    JLabel payHeader = new JLabel("PAYPAL");
    int height=500;
    int width=700;
    JOptionPane finalmessage = new JOptionPane();

 
    

    Pay(){
        
        String[] options = {"Paypal", "Credit card"};//options for the combo box
        JComboBox selectPayment= new JComboBox(options);//select options on combobox
        JPanel border = new JPanel();
        frame.setSize(width,height);
        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//frame exists on close
        border.add(selectPayment);
        selectPayment.setBounds(230,10,150,20);
        label.setBounds(10,10,200,20);
        border.setBounds(0,0,width,height/10);
        border.add(label);
        frame.add(border);
        border.setBackground(Color.GRAY);
        border.add(basket);
        
      //setting component dimensions
        basket.setBounds(250,10,50,20);
        paypPanel.setBounds(150,100,450,300);
        paypPanel.setBackground(Color.lightGray);
        paypPanel.add(paypemail);
        paypPanel.add(emailJLabel);
        paypPanel.add(payHeader);
        paypemail.setBounds((20*width)/96,110,(31*width)/96,20);
        emailJLabel.setBounds((6*width)/96,110,(30*width)/96,20);
        payHeader.setBackground(Color.blue);
        payHeader.setBounds(230,20,300,60);
        credPanel.setBounds(150,100,450,300);
        credPanel.setBackground(Color.lightGray);
        credPanel.add(cardnum).setBounds((20*width)/96,110,(31*width)/96,20);
        credPanel.add(securitycode).setBounds((20*width)/96,150,(31*width)/96,20);
        credPanel.add(cardnumLabel);
        credPanel.add(sec_codeJLabel);
        cardnumLabel.setBounds((3*width)/96,110,(30*width)/96,20);
        sec_codeJLabel.setBounds((4*width)/96,150,(30*width)/96,20);
        

        //adding panels to frame
        host.add(paypPanel,"Paypal_panel");
        host.add(credPanel,"Creditcard_panel");
        host.setBounds(width/8,height/5,(6*width)/8,(6*height)/10);//dimensions
        frame.add(host);

        frame.add(checkout);//adding the check out button
        checkout.setBounds((7*width)/8-150,(8*height)/10+5,150,30);//dimensions
        checkout.setBackground(Color.decode( "#361d00"));
        
    
        checkout.setEnabled(false);//check out button disabled
        
        checkout.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
            	
                List<Product>paiditems = WindowManager.returnBasket();//paid items
                ArrayList<Integer> barcodearray = new ArrayList<Integer>();
                List<Product>whyyy = Functions.getlistOfProducts();
                for(Product product : paiditems){
                             barcodearray.add(product.getBarcode());
                         }
                WindowManager.totalorder();//gets the total cost of the basket
                
              
            
                
                for (Product product :whyyy) {
                	//if the product has a barcode, then update the quantity if it has been paid for
                    if(barcodearray.contains(product.getBarcode()))
                    {int current_stocklevel = product.getQuantity_in_stock();
                    int result;
                    int amount_bought = product.quantity_bought();
                    result = current_stocklevel-amount_bought;//subtracting the amount of item bought from total quantity
                    product.set_new_quantity(result); }
                  //  System.out.print(product.getQuantity_in_stock());
                    }
                    try {
                        Functions.writeProduct(whyyy);//update quantity in the stock file
                    } catch (IOException e2) {
                        // TODO Auto-generated catch block
                        e2.printStackTrace();
                    }
                displayMessage();//display check out message
                WindowManager.clearBasket();//clear basket
                frame.dispose();//close frame
                try {
                    WindowManager.backtoShop();//go back to shopping window
                } catch (FileNotFoundException e1) {
                    
                    e1.printStackTrace();
                }


            }
            
        });
        selectPayment.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                int option = selectPayment.getSelectedIndex();
                if(option==1){
                    cardLayout.show(host, "Creditcard_panel");//if the option is credit card, open
                    //credit card panel
                }
                else{
                    cardLayout.show(host, "Paypal_panel");//else open paypal
                }        
            }
        });
        basket.addActionListener(new ActionListener(){
        	//opens basket window if button is pressed

            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    WindowManager.backTOBasket();
                    frame.dispose();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
                
            }
            
        });
        paypemail.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                validateentry(((JTextPane) e.getSource()).getText());
            }
        });
        cardnum.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                validatesecurity(((JTextPane) e.getSource()).getText());
            }
        });
        securitycode.addKeyListener(new KeyAdapter(){
            @Override
            public void keyReleased(KeyEvent e) {
                validatesecurity(((JTextPane) e.getSource()).getText());
            }
        });

    }
    public void validateentry(String entry){
    	//function to validate email address
        entry =  paypemail.getText();
        if ((0 < entry.indexOf("@") )){//if there is an at after a string
            checkout.setEnabled(true);//enable check out button is syntax is correct


        }
        else
        checkout.setEnabled(false);//else remain disabled
       
    }

    public void validatesecurity(String in){
        boolean valid=true;
   try{
       Float.parseFloat(in);//checks if the credit card is just numbers

      
   }
   catch(Exception E){
    valid = false;
   }
   //checks if its numbers, sc is length of 3 and card number is a length of 6
   if((valid==true)&&(securitycode.getText().length()==3) && (cardnum.getText().length()==6)){
	   
    checkout.setEnabled(true);//enable check out button is syntax is correct
}
else{
    
    checkout.setEnabled(false);}//else remain disabled

    }
    public void displayMessage(){
    	//display check out message, with total amount bought and address being deliverd to
        String message= "£"+Math.round(WindowManager.totalorder()* 100.0) / 100.0 +" Amount paid using paypal,will be delivered to " +WindowManager.userAddress();
        JOptionPane.showMessageDialog(null, message, "PAYMENT ACCEPTED", JOptionPane.PLAIN_MESSAGE);

        
    }
}






