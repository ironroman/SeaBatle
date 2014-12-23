package src.view;
import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

class Help extends 	JFrame
{
    
	public Help()
	{
		setTitle( "Help Messenger" );
		setSize( 600, 500 );
		setBackground( Color.gray );
		getContentPane().setLayout( new BorderLayout() );


		JPanel MessegePanel = new JPanel();
		MessegePanel.setLayout( new BorderLayout() );
		getContentPane().add( MessegePanel, BorderLayout.CENTER );

        // Create a text area
		JTextArea area = new JTextArea();
        area.setEditable(false);
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().add( area );
		scrollPane.setBounds( 10, 10, 600, 450 );
		MessegePanel.add( scrollPane, BorderLayout.CENTER );

		// Load a file into the text area, catching any exceptions
			
            area.setText(" * Roman Bronshtein Project"+
 "\n*"+
 "\n*"+
 "\n*"+ 
 "\n*                       My Puzzel created by Roman Bronshtein"+
 "\n* "+
 "\n*                                 Pazzle game have very simple"+ 
 "\n*"+ 
 "\n*"+ 
 "\n*                                           Instructions :"+
	"\n  1. New Button  – to start game. But first of all you must enter your name,"+ 
    "\n                   chouse the picture and the crop style "+
    "\n                   and after that press the New Button"+

	"\n  2. Stop –        to stop the Game, you time and score will be reseted"+
	"\n  "+
	"\n  3. Pause -       you can pause the Game; time will be paused, "+
	"\n"+

	"\n  4. Continue –    to continue paused game you must click on "+
    "\n                   Continue button"+

	"\n  5. Score –       to view score table of previous games user must"+ 
    "\n                   click on Score button"+

	"\n  6.                                    Game Rules !"+ 
	"\n "+  
    "\n            In the left side you will see the puzel's if you choused 3x3"+ 
    "\n            button that will be 9 puzzel's, an so on. You must drag this "+ 
    "\n            puzel's to the rigt side in the place that Icon must be. if you"+ 
    "\n            put in the right place Icon will stayed there, if not - you must  "+ 
    "\n            try again. "+ 
    "\n            When all of the puzels will be in the right place's you finish"+ 
    "\n            the Game and you time and score will listed in the Data Base of "+ 
    "\n            the Game. "+
    "\n            To start next Game press New Button; if you not finished one game"+
    "\n            and want to start other - first press Stop button, and after that New"+
    


	"\n    7.      When user has finished the game he will get correspond message"+ 
    "\n            and his name will automatically insert into score table."+

	"\n "+
		
	"\n	                                            GOOD LUCK!!!!!!!"

);
    	
    	
        setResizable(false);
        show();
	}
}

