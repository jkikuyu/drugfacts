
package com.drugfacts;

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.jdesktop.application.Application;
import org.jdesktop.application.ResourceMap;

/**
 *
 * @author Jude Kikuyu
 */
public class UserDetailsForm extends JPanel {
    protected JPanel lpanelusr,tpanelusr, uinpanel,uoutpanel;

    private JLabel lblFirstName, lblLastName, lblUserName,lblEmail,lblAddress,
            statusMessageLabel,statusAnimationLabel;
    private  JTextField txtUserName, txtFirstName, txtLastName, txtEmail,
            txtAddress;
    private ResourceMap resourceMap;

    public UserDetailsForm(){
        initComponents();
    }
     private void initComponents() {
        lpanelusr = new JPanel();
        tpanelusr = new JPanel();
        uinpanel = new JPanel();
        uoutpanel = new JPanel();

        lblFirstName = new JLabel();
        lblLastName = new JLabel();
        lblUserName= new JLabel();
        lblAddress= new JLabel();
        lblEmail = new JLabel();
        txtUserName = new JTextField(20);
        txtFirstName = new JTextField(25);
        txtLastName = new JTextField(25);
        txtEmail = new JTextField(30);
        txtAddress = new JTextField(25);
        resourceMap = Application.getInstance(DrugfactsApp.class)
                .getContext().getResourceMap(DrugfactsView.class);

        lblFirstName.setFont(resourceMap.getFont("label.font"));
        lblFirstName.setText(resourceMap.getString("lblFirstName.text"));
        lblFirstName.setName("lblFirstName");
        lblLastName.setFont(resourceMap.getFont("label.font"));
        lblLastName.setText(resourceMap.getString("lblLastName.text"));
        lblLastName.setName("lblLastName");

        lblUserName.setFont(resourceMap.getFont("label.font"));
        lblUserName.setText(resourceMap.getString("lblUserName.text"));
        lblUserName.setName("lblUserName");

        lblAddress.setText(resourceMap.getString("lblAddress.text"));
        lblAddress.setName("lblAddress");

        lblEmail.setText(resourceMap.getString("lblEmail.text"));
        lblEmail.setName("lblEmail");
        txtFirstName.setName("txtFirstName");
        txtLastName.setName("txtUserName");
        txtUserName.setName("txtLastName");
        txtEmail.setName("txtEmail");
        txtAddress.setName("txtAddress");

        GridLayout layout= new GridLayout(2,1,3,3);
        lpanelusr.setLayout(layout);
        tpanelusr.setLayout(layout);

      //  mainPanel.setBorder(BorderFactory.createEtchedBorder());
      //  mainPanel2.setBorder(BorderFactory.createEtchedBorder());


       lpanelusr.add(lblFirstName);

       lpanelusr.add(lblLastName) ;

       tpanelusr.add(txtFirstName);

       tpanelusr.add(txtLastName);
       uinpanel.setBorder(BorderFactory.createTitledBorder(
               BorderFactory.createEtchedBorder(),
               resourceMap.getString("uinpanel.border.title"),
               javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
               javax.swing.border.TitledBorder.DEFAULT_POSITION,
               resourceMap.getFont("border.title.Font"),
               resourceMap.getColor("border.title.Color")));
        uinpanel.add(lpanelusr);
        uinpanel.add(tpanelusr);
        add(uinpanel);

     }

    public String getTxtFirstName() {
        return txtFirstName.getText();
    }
    public String getTxtLastName() {
        return txtLastName.getText();
    }

    public void setTxtFirstName(String txtFirstName) {
        this.txtFirstName.setText(txtFirstName);
    }


    public void setTxtLastName(String txtLastName) {
        this.txtLastName.setText(txtLastName);
    }
     
}
