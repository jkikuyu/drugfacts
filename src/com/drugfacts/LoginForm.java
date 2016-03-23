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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;

/**
 *
 * @author Jude Kikuyu
 */
public class LoginForm extends JFrame{
    
    private JButton btnOk, btnCancel;
    private JLabel lblUsername, lblPassword;
    private JTextField txtUsername, txtPassword;
    private JPanel panel;
    private JFrame frame;
    public LoginForm(SingleFrameApplication app) {
        //super(app);
        initComponents();
    }
    private void initComponents() {
        lblUsername = new JLabel();
        lblPassword = new JLabel();
        txtUsername = new JTextField(30);
        txtPassword = new JTextField(30);
        btnOk = new JButton();
        btnCancel = new JButton();
        panel = new JPanel();
        frame= new JFrame();
        panel.setName("panel");
        // use resource to obtain label titles and properties
        ResourceMap resourceMap = Application.getInstance(DrugfactsApp.class).getContext().
                getResourceMap(LoginForm.class);

        // label set up
        lblUsername.setFont(resourceMap.getFont("label.font"));
        lblUsername.setText(resourceMap.getString("lblUsername.text"));
        lblUsername.setName("lblUsername");
        lblPassword.setFont(resourceMap.getFont("label.font"));
        lblPassword.setText(resourceMap.getString("lblPassword.text"));
        lblPassword.setName("lblPassword");
        // text field setup

        txtUsername.setFont(resourceMap.getFont("inputfield.font"));
        txtUsername.setText(resourceMap.getString(""));
        txtUsername.setName("txtUsername");

        txtPassword.setFont(resourceMap.getFont("inputfield.font"));
        txtPassword.setText(resourceMap.getString(""));
        txtPassword.setName("txtPassword");

         // button set up
        btnOk.setText(resourceMap.getString("btnOk.text")); // NOI18N
        btnOk.setName("btnOk"); // NOI18N
        btnOk.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnOkActionPerformed(e);
            }
        });

        btnCancel.setText(resourceMap.getString("btnCancel.text")); // NOI18N
        btnCancel.setName("btnCancel"); // NOI18N
        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnCancelActionPerformed(e);
            }
        });
        // place layout
//        GroupLayout layout= new GroupLayout(panel);
//        panel.setLayout(layout)
//               ;
//        layout.setHorizontalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(43, 43, 43)
//                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//                    .addComponent(lblUsername)
//                    .addComponent(lblPassword))
//                .addGap(31, 31, 31)
//                .addGroup(layout.createParallelGroup(
//                GroupLayout.Alignment.LEADING, false)
//                .addGroup(GroupLayout.Alignment.TRAILING,
//                    layout.createSequentialGroup()
//                .addComponent(btnOk, GroupLayout.PREFERRED_SIZE, 65,
//                    GroupLayout.PREFERRED_SIZE)
//                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED,
//                    GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
//                .addComponent(btnCancel, GroupLayout.PREFERRED_SIZE, 65,
//                    GroupLayout.PREFERRED_SIZE))
//                .addComponent(txtUsername,
//                    GroupLayout.PREFERRED_SIZE, 156, GroupLayout.PREFERRED_SIZE)
//                .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE, 156,
//                GroupLayout.PREFERRED_SIZE))
//                .addContainerGap(107, Short.MAX_VALUE))
//);
//        layout.setVerticalGroup(
//            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
//            .addGroup(layout.createSequentialGroup()
//                .addGap(32, 32, 32)
//                .addGroup(layout.createParallelGroup(
//                    GroupLayout.Alignment.BASELINE)
//                    .addComponent(lblUsername)
//                    .addComponent(txtUsername, GroupLayout.PREFERRED_SIZE,
//                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
//                .addGap(18, 18, 18)
//                .addGroup(layout.createParallelGroup(
//                    GroupLayout.Alignment.BASELINE)
//                    .addComponent(txtPassword, GroupLayout.PREFERRED_SIZE,
//                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
//                    .addComponent(lblPassword))
//                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 23,
//                    Short.MAX_VALUE)
//                .addGroup(layout.createParallelGroup(
//                    GroupLayout.Alignment.BASELINE)
//                    .addComponent(btnOk)
//                    .addComponent(btnCancel))
//                .addContainerGap())
//        );
        GridBagLayout layout= new GridBagLayout();
        panel.setLayout(layout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        panel.add(lblUsername, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        panel.add(txtUsername, gc);

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
     public void btnCancelActionPerformed(ActionEvent e){

     }
     public void btnOkActionPerformed(ActionEvent e){

    }

}
