/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.drugfacts;

/**
 *
 * @author DIT-INTERN
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class ToolBar extends JFrame implements	ActionListener
 {
	private	JPanel		topPanel;
	private	JButton		buttonNew;
	private	JButton		buttonOpen;
	private	JButton		buttonSave;
	private	JButton		buttonCopy;
	private	JButton		buttonCut;
	private	JButton		buttonPaste;

	public ToolBar()
	{
		setTitle( "Basic Toolbar Application" );
		setSize( 400, 250 );
		setBackground( Color.gray );

		topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );

		// Create a new toolbar
		JToolBar myToolbar = new JToolBar();
		topPanel.add( myToolbar, BorderLayout.NORTH );

		// Add some buttons to the toolbar
		buttonNew = addToolbarButton( myToolbar, true, "New",
								"new", "Create a new document" );
//		buttonOpen = addToolbarButton( myToolbar, true, "Open",
//								"open", "Open an existing document" );
//		buttonSave = addToolbarButton( myToolbar, true, "Save",
//								"save", "Open an existing document" );
//		myToolbar.addSeparator();
//		buttonCopy = addToolbarButton( myToolbar, true, null,
//								"copy", "Copy selection to the clipboard" );
//		buttonCut = addToolbarButton( myToolbar, true, null,
//								"cut", "Cut selection to the clipboard" );
//		buttonPaste = addToolbarButton( myToolbar, true, null,
//								"paste", "Paste selection from the clipboard" );

		// Add a text area just to fill up the space
		JTextArea textArea = new JTextArea();
		topPanel.add( textArea, BorderLayout.CENTER );
	}

	// Helper method to create new toolbar buttons
	public JButton addToolbarButton( JToolBar toolBar, boolean bUseImage, String sButtonText,
									String sButton, String sToolHelp )
	{
		JButton b;
                 b=new JButton();
                 b.setIcon(new ImageIcon(getClass().getResource("resources/" +sButton+ ".gif") ));
		// Create a new button
//		if( bUseImage )
//			//b = new JButton( new ImageIcon( "resources/" +sButton+ ".gif") );
//                    b=new JButton();
//                //setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/zeno/projects/simplegui/new.gif")))
//		else
//			b = (JButton)toolBar.add( new JButton() );
//
//		// Add the button to the toolbar
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
		b.addActionListener( this );

		return b;
	}

	public void actionPerformed( ActionEvent event )
	{
		// Add action handling code here
		System.out.println( event );
	}

	public static void main( String args[] )
	{
		// Create an instance of the test application
		ToolBar mainFrame	= new ToolBar();
		mainFrame.setVisible( true );
	}
}