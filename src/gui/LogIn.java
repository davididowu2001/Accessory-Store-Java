
package gui;


import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import functions.Functions;
import users.User;

public class LogIn implements ActionListener {
	
    JFrame frame = new JFrame();//creates frame
    JButton[] buttons= new JButton[4];//buttons for admins
    List<User> users; //list of users
    LogIn() throws FileNotFoundException {
        users = Functions.readUserAccounts();//read from userAccoint file
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JLabel label = new JLabel("TECHSHOP");
        ImageIcon profile_icon;//profile icon
        JPanel profile;
        JButton profile_button;
        JLabel profile_label;
        Image image;
        label.setFont(new Font(",",Font.BOLD,20));
        label.setBounds(180,100,200,35);
        label.setForeground(Color.white);

        frame.add(label);

        for (int i =0; i< buttons.length; i++) {
            // create picture button object
            profile = new JPanel(null);
            profile_label = new JLabel(users.get(i).getUserName());
            profile_icon = new ImageIcon("src\\gui\\user"+(i+1)+".png");
            image = profile_icon.getImage().getScaledInstance(68, 54, Image.SCALE_SMOOTH);
            profile_button = new JButton(new ImageIcon(image));
            profile_button.setBounds(2, 2, 73, 60);
            profile_label.setBounds(23, 65, 32, 15);
            buttons[i] = profile_button;
            profile.add(profile_button);//add button to the icon
            profile.add(profile_label);//adds text to the icon
            profile.setBounds(37 + 112*i, 200, 75, 80);
            profile_button.addActionListener(this);
            profile_button.setActionCommand(Integer.toString(i));
            frame.add(profile);
        }  
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    	//get users from file
       User user = users.get(Integer.parseInt(e.getActionCommand()));
        try {
            WindowManager.signal_login(user);//function to signal the type of user
            frame.dispose();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}