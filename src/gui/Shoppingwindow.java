package gui;

import javax.swing.*;
import javax.swing.RowSorter.SortKey;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.TableView;

import functions.Functions;
import products.Keyboard;
import products.Product;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.KeyListener;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;



 class Shoppingwindow{
    JFrame frame = new JFrame();//main swing frame
    Shoppingwindow(Boolean is_customer) throws FileNotFoundException {  
        String col [] = {"BARCODE","NAME","TYPE","BRAND","COLOUR",
        "CONNECTIVITY","STOCK","COST","PRICE","INFO"};//headers for table column
        JPanel border= new JPanel(null);
        JButton button = new JButton("ADD TO BASKET");//add to basket button
        JButton viewBasket = new JButton("BASKET");//basket button
        JButton backButton = new JButton("<");//back to login page button
        JScrollPane scroll;
        JTextPane searchbar = new JTextPane();//search by brand and button bar
        JButton addwindow = new JButton("Add Product");//add product by admin button
        JButton search= new JButton("Search");//search button
        frame.setSize(800,500);//frame size    
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//frame closes when exited
        List <Product >items = Functions.readProducts();
        Object[] data = new Object[10]; 
        DefaultTableModel tableModel = new DefaultTableModel(){//making the table model 
            public Class<?> getColumnClass (int index){
                if(index==8){               
                    return Float.class;
                }
                return super.getColumnClass(index);
            }
        };
        JTable table = new JTable(tableModel);//table
        scroll = new JScrollPane(table);//scroll function
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);
        table.setRowSorter(sorter);//sorter to sort the table in ascending or descending order   
        List<RowSorter.SortKey>sortKeys = new ArrayList<>();

        
        
        frame.setLayout(null);
        frame.add(border);
        frame.add(scroll);
        frame.add(backButton);
        frame.add(button);
        frame.add(viewBasket);
        border.add(backButton);
        border.add(searchbar);
        border.add(addwindow);
        border.add(search);
        
        for(int i= 0; i<col.length;i++){
            tableModel.addColumn(col[i]);//adding each column to the table
        }
        
        if (is_customer){
        	//if user is a customer, disable add product button
            table.removeColumn(table.getColumnModel().getColumn(7));
           addwindow.setEnabled(false);
        }
        
        Product stock;
        for(int i=0; i<items.size(); i++){//adding items to the table
            stock = items.get(i);
            data[0] = Integer.toString(stock.getBarcode());
            data[3]=stock.getBrand();
            data[4] = stock.getColour();
            data[5] = stock.getConnectivity();
            data[1] = stock.getName();
            data[6] = Integer.toString(stock.getQuantity_in_stock());
            data[7] = Float.toString(stock.getOriginal_cost());
            data[8] = stock.getRetail_price();
            data[2] = stock.getType();
            data[9]=stock.getAdditional_info();     
          tableModel.addRow(data);
        }
        
        sortKeys.add(new SortKey(8, SortOrder.ASCENDING));//sorting the table by cost in ascending order
        sorter.setSortKeys(sortKeys);
        border.setBackground(Color.DARK_GRAY);
        //setting sizes to components
        border.setBounds(0, 0, 800, 40);
        scroll.setBounds(0,40,650,400);  
        table.setShowVerticalLines(true);
        table.setShowHorizontalLines(true); 
        button.setBounds(650,50,130,20);
        viewBasket.setBounds(650,70,130,20);
        backButton.setBounds(3,10,40,20);
        addwindow.setBounds(400,10,100,30);
        backButton.setBackground(Color.decode("#f2913d"));
        searchbar.setBounds(80,10,150,20);
        search.setBounds(255,10,100,20);
   
        button.setEnabled(is_customer);//enable basket button for only customer
        viewBasket.setEnabled(is_customer);
        button.addActionListener(new ActionListener(){
        	//adds selected item to basket when the button is pressed
            @Override
            public void actionPerformed(ActionEvent e) {
                int record[] =  table.getSelectedRows();
                for(int i:record){   
                    WindowManager.addToBasket(items.get(table.convertRowIndexToModel(i)));   
                }
                table.clearSelection();//clear selection after adding to basket    
            }
        
        });
        addwindow.addActionListener(new ActionListener(){
        	//add to product button function

            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                try {
                    WindowManager.goToAddwindow();//load new page
                    frame.dispose();//close current frame
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
                
            }        
        });
        

            
        
        search.addActionListener(new ActionListener(){
        	//SEARCH BAR SEARCHES BY BRAND AND BY INFO,BUTTON NUMBER
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                String searchtext = searchbar.getText();
                sorter.setRowFilter(RowFilter.regexFilter(searchtext,3,9));              
            }
        });
        
        viewBasket.addActionListener(new ActionListener(){
        	//view basket button function

            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                try {
                    WindowManager.Basketwindow();//open basket window
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
                
            }
            
        });
        backButton.addActionListener(new ActionListener(){
//BACK TO LOGIN IN PAGE BUTTON	
            @Override
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                frame.dispose();
                try {
                    WindowManager.backToLogin();
                } catch (FileNotFoundException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
            
        });
    
        frame.setVisible(true);
 
    }   
    
}
