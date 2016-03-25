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
import javax.swing.ActionMap;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import org.jdesktop.application.Application;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.TaskMonitor;

/**
 *
 * @author Spring park Hotel
 */
public class DrugfactsView extends JFrame{
    private JPanel mainPanel, statusPanel;
    private JScrollPane masterScrollPane;
    private JTable masterTable;
    private JLabel lblFirstName, lblLastName, lblUserName,lblEmail,lblAddress, 
            statusMessageLabel,statusAnimationLabel;
    private  JTextField txtUserName, txtFirstName, txtLastName, txtEmail,
            txtAddress;
    private JButton btnSave, btnRefresh, btnNew, btndelete;
    private JMenu fileMenu;
    private JMenuBar   menuBar;
    private JMenuItem mitNewRecord, mitDelRecord, mitSaveRecord, mitRefesh,
            mitExit;
    private JSeparator spt1, spt2;
    private JProgressBar progressBar;
    private final Timer messageTimer, busyIconTimer;
    private final Icon idleIcon;Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;
    private ResourceMap resourceMap;
    private JToolBar toolbar;
    private JDialog aboutBox;
    private boolean saveNeeded;


    int messageTimeout;
    DrugfactsApp dapp;

    public DrugfactsView(final JFrame parent){
        initComponents();

        messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");

        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
	messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");

        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);
        TaskMonitor taskMonitor = new TaskMonitor(dapp.getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });

    }

   

    private void initComponents() {
        dapp = new DrugfactsApp();
        mainPanel = new JPanel();
        masterScrollPane = new JScrollPane();
        masterTable = new JTable();
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

        btnSave = new JButton();
        btnRefresh = new JButton();
        btnNew = new JButton();
        btndelete = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        mitNewRecord = new JMenuItem();
        mitDelRecord = new JMenuItem();
        mitSaveRecord = new JMenuItem();
        mitRefesh = new JMenuItem();
        mitExit = new JMenuItem();
        spt1 = new JSeparator();

        spt2 = new JSeparator();
        JMenuItem exitMenuItem = new JMenuItem();
        JMenu helpMenu = new JMenu();
        JMenuItem aboutMenuItem = new JMenuItem();
        statusPanel = new JPanel();
        JSeparator statusPanelSeparator = new JSeparator();
        //toolbar.add(btnNew);
        statusMessageLabel = new JLabel();
        statusAnimationLabel = new JLabel();
        progressBar = new JProgressBar();
        resourceMap = Application.getInstance(DrugfactsApp.class)
                .getContext().getResourceMap(DrugfactsView.class);
        ActionMap actionMap = Application.getInstance(DrugfactsApp.class)
                .getContext().getActionMap(DrugfactsView.class, this);

        menuBar.setName("menuBar");

        fileMenu.setText(resourceMap.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");

        mitNewRecord.setAction(actionMap.get("newRecord"));
        mitNewRecord.setName("mitNewRecord");
        fileMenu.add(mitNewRecord);

        mitDelRecord.setAction(actionMap.get("deleteRecord"));
        mitDelRecord.setName("mitDelRecord");
        fileMenu.add(mitDelRecord);

        spt1.setName("spt1");
        fileMenu.add(spt1);

        mitSaveRecord.setAction(actionMap.get("save"));
        mitSaveRecord.setName("mitSaveRecord");
        fileMenu.add(mitSaveRecord);

        mitRefesh.setAction(actionMap.get("refresh"));
        mitRefesh.setName("mitRefesh");
        fileMenu.add(mitRefesh);

        spt2.setName("spt2"); // NOI18N
        fileMenu.add(spt2);

        mitExit.setAction(actionMap.get("quit"));
        mitExit.setName("mitExit");
        fileMenu.add(mitExit);

        menuBar.add(fileMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text"));
        helpMenu.setName("helpMenu");

        aboutMenuItem.setAction(actionMap.get("showAboutBox"));
        aboutMenuItem.setName("aboutMenuItem");
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N


        lblLastName.setFont(resourceMap.getFont("label.font"));
        lblLastName.setText(resourceMap.getString("lblLastName.text"));
        lblLastName.setName("lblLastName"); 

        lblLastName.setFont(resourceMap.getFont("label.font"));
        lblFirstName.setText(resourceMap.getString("lblFirtName.text"));
        lblFirstName.setName("lblFirstName");
        lblUserName.setFont(resourceMap.getFont("label.font"));
        lblUserName.setText(resourceMap.getString("lblUserName.text"));
        lblUserName.setName("lblUserName"); 

        lblAddress.setText(resourceMap.getString("lblAddress.text"));
        lblAddress.setName("lblAddress");

        lblEmail.setText(resourceMap.getString("lblEmail.text"));
        lblEmail.setName("lblEmail");
        txtFirstName.setName("txtUserName");
        txtLastName.setName("txtUserName");
        txtUserName.setName("txtUserName");
        txtEmail.setName("txtUserName");
        txtAddress.setName("txtUserName");

        GridBagLayout layout= new GridBagLayout();
        mainPanel.setLayout(layout);
        GridBagConstraints gc = new GridBagConstraints();
        gc.fill = GridBagConstraints.HORIZONTAL;
        gc.insets = new Insets(10, 10, 10, 10);

        gc.gridx = 0;
        gc.gridy = 0;
        mainPanel.add(lblFirstName, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        mainPanel.add(txtFirstName, gc);

        gc.gridx = 0;
        gc.gridy = 1;
        mainPanel.add(lblLastName, gc);

        gc.gridx = 1;
        gc.gridy = 1;
        mainPanel.add(txtLastName, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        mainPanel.add(lblUserName, gc);

        gc.gridx = 1;
        gc.gridy = 2;
        mainPanel.add(txtUserName, gc);

        gc.gridx = 0;
        gc.gridy = 3;
        mainPanel.add(lblEmail, gc);

        gc.gridx = 1;
        gc.gridy = 3;
        mainPanel.add(txtEmail, gc);
        gc.gridx = 0;
        gc.gridy = 4;
        mainPanel.add(lblAddress, gc);

        gc.gridx = 1;
        gc.gridy = 4;
        mainPanel.add(txtAddress, gc);
        add(mainPanel, BorderLayout.CENTER);
        add(menuBar,BorderLayout.NORTH);

    }
    @Action
    public void newRecord() {
    }
    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DrugfactsApp.getApplication().getMainFrame();
            aboutBox = new DrugfactsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DrugfactsApp.getApplication().show(aboutBox);
    }

}
