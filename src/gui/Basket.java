package gui;

import java.awt.event.ActionEvent;


import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;
import products.Product;


public class Basket {
    
    JFrame frame = new JFrame();
    JPanel border= new JPanel(null);
    JButton button = new JButton("PAY");
    List<Product>basket;
    JTable table;
    JScrollPane scroll;
    JButton clearBasket = new JButton("Clear Basket");
    JButton backToShop = new JButton("<");

    String data [][];
    String col [] = {"BARCODE","NAME","TYPE","BRAND","COLOUR",
    "CONNECTIVITY","QUANTITY IN STOCK","ORIGINAL COST","PRICE","INFO"};//column names for table
    Product stock;
    
  

    Basket(List<Product> basket,boolean is_customer){//basket is a list of product ,accessed by only cust
       frame.setSize(800,500);
       this.basket = basket;
       data = new String[basket.size()][10];
        table = new JTable(data,col);
        scroll = new JScrollPane(table);
        frame.setLayout(null);
        frame.add(border);
        frame.add(button);
        frame.add(scroll);
        scroll.setBounds(0,40,650,400);
        frame.add(clearBasket);

        if (is_customer){//if user is a customer, then remove the original cost column
            table.removeColumn(table.getColumnModel().getColumn(7));
        }

        for(int i=0; i<basket.size(); i++){
        	//assingning each data index to attributes of the product
            stock = basket.get(i);
            data[i][0] = Integer.toString(stock.getBarcode());
            data[i][3]=stock.getBrand();
            data[i][4] = stock.getColour();
            data[i][5] = stock.getConnectivity();
            data[i][1] = stock.getName();
            data[i][6] = Integer.toString(stock.getQuantity_in_stock());
            data[i][7] = Float.toString(stock.getOriginal_cost());
            data[i][8] = Float.toString(stock.getRetail_price());
            data[i][2] = stock.getType();
            data[i][9]=stock.getAdditional_info();
        }
       //dimensions
        
        border.setBounds(0, 0, 800, 40);
        button.setBounds(650,70,130,20);
        backToShop.setBounds(3,10,30,20);
        clearBasket.setBounds(650,50,130,20);
        border.add(backToShop);
        clearBasket.addActionListener((ActionListener) new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                WindowManager.clearBasket();//clear basket when button is pressed
                scroll.setViewportView(new JTable(new String[0][10],col));
            }
            


        });
        backToShop.addActionListener((ActionListener) new ActionListener(){
        	//back to shopping window button
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();//closes current frame
                try {
                    WindowManager.backtoShop();//opens shopping window
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }
          
        });
        button.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    WindowManager.openPay();//opens pay window
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }   
            }
            
        });

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
   
    }


}
