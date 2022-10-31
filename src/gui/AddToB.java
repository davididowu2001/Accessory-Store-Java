package gui;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextPane;
import functions.Functions;
import products.Product;
import gui.Shoppingwindow;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyAdapter;


public class AddToB{
    JFrame frame = new JFrame();
    JButton addItem = new JButton("Add Item");
    static JTextPane barcode = new JTextPane();
    
    static JTextPane brand = new JTextPane();
    static JTextPane colour = new JTextPane();
    
   
    static JTextPane quantity = new JTextPane();
    JTextPane originalcost = new JTextPane();
    static JTextPane retailprice = new JTextPane();
    JTextPane addtionalinfo = new JTextPane();
    static JTextPane original_cost = new JTextPane();
    static JTextPane info = new JTextPane();
    JPanel base = new JPanel();
    JLabel barcodLabel = new JLabel("Barcode: ");
    JLabel brandLabel = new JLabel("Brand: ");
    JLabel oriJLabel = new JLabel("Original cost: ");
    JLabel retaJLabel= new JLabel("Retail price: ");
    JLabel quaJLabel = new JLabel("Quantity: ");
    JLabel colourLabel = new JLabel("Colour: ");
    JLabel infolLabel = new JLabel("Info: ");
    static String[] name_options = {"Keyboard", "Mouse"};
    static String[] type_options = {"Standard","gaming","flexible"};
    static String[] connectivity_options= {"wired","wireless"};

    static JComboBox name = new JComboBox(name_options);
    static JComboBox type = new JComboBox(type_options);
    static JComboBox connectivity = new JComboBox(connectivity_options);
    int width = 800;
    int height= 500;
   
   



AddToB(){//was supposed to be called add to product , but its a bit too late to rename
    
    frame.setSize(width,height);
    frame.setVisible(true);
    frame.setLayout(null);
    base.setLayout(null);

   //setting dimensions
    base.setBounds(0,0,width,height);  
    addItem.setBounds(400,400,90,40);
    barcode.setBounds(80,20,120,20);
    original_cost.setBounds(90,240,120,20);
    retailprice.setBounds(80,60,120,20);
    quantity.setBounds(80,120,120,20);
    colour.setBounds(80,160,120,20);
    brand.setBounds(80,200,120,20);
    info.setBounds(80,280,120,20);
    name.setBounds(300,20,100,20);
    type.setBounds(300,60,100,20);
    connectivity.setBounds(300,120,100,20);
    barcodLabel.setBounds(10,20,80,20);
    brandLabel.setBounds(10,200,80,20);
    oriJLabel.setBounds(10,240,80,20);
    retaJLabel.setBounds(10,60,80,20);
    quaJLabel.setBounds(10,120,80,20);
    colourLabel.setBounds(10,160,80,20); 
    infolLabel.setBounds(10,280,120,20);
//adds components to panel
    base.add(name);
    base.add(type);
    base.add(connectivity);
    base.add(barcode);
    frame.add(base);
    base.add(addItem);
   base.add(retailprice);
   base.add(quantity);
   base.add(colour);
   base.add(brand);
   base.add(original_cost);
   base.add(barcodLabel);
   base.add(brandLabel);
   base.add(oriJLabel);
   base.add(retaJLabel);
   base.add(quaJLabel);
   base.add(colourLabel);
   base.add(info);
   base.add(infolLabel);
   

//list of prodcuts is a list of all the entries
addItem.addActionListener((ActionListener) new ActionListener(){

    boolean barcodeexist;
    //checks if barcode exists already, so that two same items cant be added twice
    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        try {
            
            if(barcodeexist==false){
                Functions.addProduct();//if there is no collision
                //then add product
            };
            
            frame.dispose();//close frame
            barcode.setText("");
            WindowManager.backtoShop();      
           
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        
    }
    
});
retailprice.addKeyListener(new KeyAdapter() {
	//validate retail price
    @Override
    public void keyReleased(KeyEvent e) {
        validateprice(((JTextPane) e.getSource()).getText());
    }
});
addItem.setEnabled(false); //if false, add button will be disabled

quantity.addKeyListener(new KeyAdapter() {
	//validates quantity
    @Override
    public void keyReleased(KeyEvent e) {
        validatequantity(((JTextPane) e.getSource()).getText());
    }
});
addItem.setEnabled(false); //if false, add button will be disabled

barcode.addKeyListener(new KeyAdapter() {
    @Override
    //validates barcode
    public void keyReleased(KeyEvent e) {
        validatebarcode(((JTextPane) e.getSource()).getText());
    }
});
addItem.setEnabled(false); //if false, add button will be disabled

original_cost.addKeyListener(new KeyAdapter() {
    @Override
    //validates original cost
    public void keyReleased(KeyEvent e) {
        validateorig(((JTextPane) e.getSource()).getText());
    }
});
addItem.setEnabled(false); //if false, add button will be disabled

}

public static  List<Object> listOfProducts () {
    //entries for attribute of products
    List<Object> listProducts = new ArrayList<>();     
    String brandentry = brand.getText();
    String colourentry = colour.getText();
    int quantityentry = Integer.parseInt( quantity.getText());
    Float originalentry = Float.parseFloat (original_cost.getText());
    String infoentry = info.getText();
    int barcodeentry = Integer.parseInt(barcode.getText());
    Float retailentry = Float.parseFloat(retailprice.getText());
    String nameEntry = (String) name.getSelectedItem();
    String typeEntry = (String) type.getSelectedItem();
    String connectivityentry = (String) connectivity.getSelectedItem();
    //adds all entries to a list
    listProducts.add(barcodeentry);
    listProducts.add(nameEntry);
    listProducts.add(typeEntry);
    listProducts.add(brandentry);
    listProducts.add(colourentry);
    listProducts.add(connectivityentry);
    listProducts.add(quantityentry);
    listProducts.add(originalentry);
    listProducts.add(retailentry);
    listProducts.add(infoentry);
    return listProducts;
}
public void validateprice(String in) {
	//function to validate price
   boolean valid=true;
   try{
       Float.parseFloat(in);  //checks if price is a float   
   }
   catch(Exception E){
    valid = false;
   }
   if(valid==true){//if valid, then add button is enabled
    addItem.setEnabled(true);
}
else{
	//error message pops up
    JOptionPane.showMessageDialog(null, "invalid price", "error", JOptionPane.ERROR_MESSAGE);
    addItem.setEnabled(false);//else button is disabled
}
}
public void validateorig(String in) {
	//function to validate original price
    boolean valid=true;
    try{
        Float.parseFloat(in);  //checks if its a float
    }
    catch(Exception E){
     valid = false;
    }
    if(valid==true){//if valid, then add button is enabled
     addItem.setEnabled(true);
 }
 else{
	//error message pops up
     JOptionPane.showMessageDialog(null, "invalid originalprice", "error", JOptionPane.ERROR_MESSAGE);
     addItem.setEnabled(false);//else button is disabled
 }
 }
 public boolean validatebarcode(String in) {
	 //validate barcode
    boolean notexist=false;//checks if barcode already exists
    try{
        Integer.parseInt(in);
        if(barcode.getText().length()==6){//barcode is 6 digits
           List<Product> items =Functions.readProducts() ;
           for (Product product : items) {
        	   //checks if barcode is an integer and doesnt exist
               if(product.getBarcode()==Integer.parseInt(barcode.getText())){
                   notexist=true;
                   break;
               }
           }
        }      
    }
    
    catch(Exception E){
     notexist= true;
    }
    if(notexist==false){
     addItem.setEnabled(true);//if valid, then add buttion is enabled
 }

 else{
	//error message pops up
     JOptionPane.showMessageDialog(null, "invalid barcode", "error", JOptionPane.ERROR_MESSAGE);
     addItem.setEnabled(false);//else button is disabled
 }
 return notexist;
 }
 public void validatequantity(String in){
	 //validate quantity
    boolean valid=true;
    try{
        Integer.parseInt(in);//checks if quantity is an integer
 
       
    }
    catch(Exception E){
     valid = false;
    }
    if(valid==true){//if correct,
     addItem.setEnabled(true);//if valid, then add button is enabled
 }
 else{
	//error message pops up
     JOptionPane.showMessageDialog(null, "invalid quantity", "Error", JOptionPane.ERROR_MESSAGE);
     addItem.setEnabled(false);//else button is disabled
 }
 }

} 
