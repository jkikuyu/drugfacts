/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.drugfacts;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import javax.swing.Action;
import java.net.URL;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
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
//import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.TaskMonitor;

/**
 *
 * @author Spring park Hotel
 */
public class DrugfactsView extends JFrame{
    private JPanel lpanelusr,tpanelusr, uinpanel,uoutpanel, statusPanel,toolPanel ;
    private JScrollPane masterScrollPane;

    private JTable masterTable;
    private JLabel lblFirstName, lblLastName, lblUserName,lblEmail,lblAddress,
            statusMessageLabel,statusAnimationLabel;
    private  JTextField txtUserName, txtFirstName, txtLastName, txtEmail,
            txtAddress;
    private JButton btnSave, btnRefresh, btnNew, btnDelete;
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
    private JFrame parent;
    javax.swing.Action aNewRec, aDelRec, aSave, aRefresh;


    int messageTimeout;
    DrugfactsApp dapp;

    public DrugfactsView(final JFrame parent) {

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
        lpanelusr = new JPanel();
        tpanelusr = new JPanel();
        uinpanel = new JPanel();
        uoutpanel = new JPanel();
        toolPanel = new JPanel();

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
        btnDelete = new JButton();
        menuBar = new JMenuBar();
        fileMenu = new JMenu();
        mitNewRecord = new JMenuItem();
        mitDelRecord = new JMenuItem();
        mitSaveRecord = new JMenuItem();
        mitRefesh = new JMenuItem();
        mitExit = new JMenuItem();
        spt1 = new JSeparator();

        setTitle( "Standard Drugfacts Toolbar" );
		setSize( 400, 250 );
		setBackground( Color.gray );

                JToolBar stdtbar = new JToolBar();
		toolPanel.add( stdtbar, BorderLayout.NORTH );

		// Add some buttons to the toolbar
		btnNew = addToolbarButton( stdtbar, false, "Add","add", "Add a new record" );
		btnSave = addToolbarButton( stdtbar, true, "Save","save", "Save record" );
		stdtbar.addSeparator();
		btnDelete = addToolbarButton( stdtbar, true, "delete", "Delete", "Delete record" );
		btnRefresh = addToolbarButton( stdtbar, true, "refresh", "Refresh", "reload record" );

        spt2 = new JSeparator();
        //JMenuItem exitMenuItem = new JMenuItem();
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

        aNewRec = actionMap.get("newRecord");
        aDelRec = actionMap.get("delRecord");
        aSave = actionMap.get("save");
        aRefresh = actionMap.get("refresh");

        menuBar.setName("menuBar");

        fileMenu.setText(resourceMap.getString("fileMenu.text"));
        fileMenu.setName("fileMenu");
        mitNewRecord.setAction(aNewRec);
        mitNewRecord.setName("mitNewRecord");
        fileMenu.add(mitNewRecord);

        mitDelRecord.setAction(aDelRec);
        mitDelRecord.setName("mitDelRecord");
        fileMenu.add(mitDelRecord);

        spt1.setName("spt1");
        fileMenu.add(spt1);

        mitSaveRecord.setAction(aSave);
        mitSaveRecord.setName("mitSaveRecord");
        fileMenu.add(mitSaveRecord);

        mitRefesh.setAction(aRefresh);
        mitRefesh.setName("mitRefesh");
        fileMenu.add(mitRefesh);

        spt2.setName("spt2");
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

        statusPanel.setName("statusPanel");

        statusPanelSeparator.setName("statusPanelSeparator");

        statusMessageLabel.setName("statusMessageLabel");

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel");

        progressBar.setName("progressBar");

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
        txtFirstName.setName("txtUserName");
        txtLastName.setName("txtUserName");
        txtUserName.setName("txtUserName");
        txtEmail.setName("txtUserName");
        txtAddress.setName("txtUserName");

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
        uoutpanel.add(uinpanel);

 //        mainPanel2.add(lblUserName);

//        gc.gridx = 0;
//        gc.gridy = 0;
//        mainPanel2.add(txtUserName, gc);
//
//        gc.gridx = 0;
//        gc.gridy = 1;
//        mainPanel2.add(lblEmail, gc);
//
//        gc.gridx = 1;
//        gc.gridy = 0;
//        mainPanel2.add(txtEmail, gc);
//        gc.gridx = 1;
//        gc.gridy = 1;
//        mainPanel2.add(lblAddress, gc);
//
//        gc.gridx = 2;
//        gc.gridy = 1;
//        mainPanel2.add(txtAddress, gc);
        add(uoutpanel,BorderLayout.WEST);
        //add(mainPanel2);
        //add(menuBar,BorderLayout.NORTH);
        add(stdtbar,BorderLayout.NORTH);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }
    @org.jdesktop.application.Action
    public void newRecord() {
    }

    @org.jdesktop.application.Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = DrugfactsApp.getApplication().getMainFrame();
            aboutBox = new DrugfactsAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        DrugfactsApp.getApplication().show(aboutBox);
    }
	public JButton addToolbarButton( JToolBar toolBar, boolean bUseImage,
            String sButtonText,String sButton, String sToolHelp )
	{
		JButton b;

		// Create a new button
		if( bUseImage ){
                        URL url = getClass().getResource("resources/new.gif");
                        //System.out.println(url.toString());
			b = new JButton( new ImageIcon(url));
                }
		else{
			b = (JButton)toolBar.add( new JButton());
                }
                            
		// Add the button to the toolbar
		toolBar.add( b );

		// Add optional button text
		if( sButtonText != null )
			b.setText( sButtonText );
		else
		{
			// Only a graphic, so make the button smaller
			b.setMargin( new Insets( 0, 0, 0, 0 ) );
		}


		// Add optional tooltip help
		if( sToolHelp != null )
			b.setToolTipText( sToolHelp );

		// Make sure this button sends a message when the user clicks it
		b.setActionCommand( "Toolbar:" + sButton );
		//b.addActionListener( this );
                b.setAction(null);

		return b;
	}


}

