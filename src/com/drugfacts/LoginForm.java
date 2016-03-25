/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drugfacts;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

/**
 *
 * @author Jude Kikuyu
 */
public class LoginForm extends JFrame implements ActionListener  {
    
    private JButton btnOk, btnCancel;
    private JLabel lblUserName, lblPassword;
    private JTextField txtUserName;
    private JPasswordField txtPassword;
    private JPanel panel;
    private JFrame frame;
    private static String OK = "ok";
    private static String CANCEL = "cancel";
    private int cnt; // keep login counter

     DrugfactsApp dapp;

    public LoginForm(SingleFrameApplication app) {
        //super(app);
        initComponents();
    }
    private void initComponents() {
        cnt = 0;
        lblUserName = new JLabel();
        lblPassword = new JLabel();
        txtUserName = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnOk = new JButton();
        btnCancel = new JButton();
        panel = new JPanel();
        frame= new JFrame();
        panel.setName("panel");
        // use resource to obtain label titles and properties
        ResourceMap resourceMap = Application.getInstance(DrugfactsApp.class).getContext().
                getResourceMap(LoginForm.class);

        // label set up
        lblUserName.setFont(resourceMap.getFont("label.font"));
        lblUserName.setText("User Name");
        lblUserName.setName("lblUsername");
        lblPassword.setFont(resourceMap.getFont("label.font"));
        lblPassword.setText(resourceMap.getString("lblPassword.text"));
        lblPassword.setName("lblPassword");
        // text field setup

        txtUserName.setFont(resourceMap.getFont("inputfield.font"));
        txtUserName.setText(resourceMap.getString(""));
        txtUserName.setName("txtUsername");

        txtPassword.setFont(resourceMap.getFont("inputfield.font"));
        txtPassword.setText(resourceMap.getString(""));
        txtPassword.setName("txtPassword");
        txtPassword.setActionCommand(OK);
        txtPassword.addActionListener(this);
         // button set up

        btnOk.setText(resourceMap.getString("btnOk.text")); 
        btnOk.setName("btnOk");
        btnOk.setActionCommand(OK);
        btnOk.addActionListener(this);
        btnCancel.setText(resourceMap.getString("btnCancel.text")); 
        btnCancel.setName("btnCancel");
        btnCancel.setActionCommand(CANCEL);
        btnCancel.addActionListener(this);

        GridBagLayout layout= new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(lblUserName, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel.add(txtUserName, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        panel.add(lblPassword, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        panel.add(txtPassword, gc);
        JPanel bp = new JPanel();
        bp.add(btnOk);
        bp.add(btnCancel);
        add(panel, BorderLayout.CENTER);
        add(bp, BorderLayout.PAGE_END);

    }

    public void actionPerformed(ActionEvent e) {
                String cmd = e.getActionCommand();

        if (OK.equals(cmd)) { //Process the password.
            char[] pass = txtPassword.getPassword();
            String userName = txtUserName.getText();
            if (isPasswordCorrect(userName, pass)) {

            } else {
                cnt++;
                if(cnt >= 3)
                    System.exit(0);
                JOptionPane.showMessageDialog(frame,"Invalid password. " +
                "Try again.","Error Message",JOptionPane.ERROR_MESSAGE);
            }

            //Zero out the possible password, for security.
            Arrays.fill(pass, '0');
        } else { //The user has asked for help.
        }

    }
    private static boolean isPasswordCorrect(String userName, char[] input ) {
        boolean isCorrect = true;
        char[] correctPassword =new char[]{};
        if (userName.equals("admin")){
            correctPassword = new char[]{ '1', '2', '3' };
            if (input.length != correctPassword.length) {
                isCorrect = false;
            } else {
                isCorrect = Arrays.equals (input, correctPassword);
            }
        }
        else{

        }
        //Zero out the password.
        Arrays.fill(correctPassword,'0');

        return isCorrect;
    }

}
