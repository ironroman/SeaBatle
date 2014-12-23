/*
 * Created on Feb 23, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package src.view;






import java.applet.Applet;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


/**
 * @author Roman
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class MainView extends Applet implements ActionListener
{

	URL url;
	JPanel topPanel = new JPanel();
	JPanel leftPanel = new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel rightPanel = new JPanel();
	JTextField inputNameField = new JTextField("",1);

	JCheckBox highBox = new JCheckBox();
	JCheckBox midleBox = new JCheckBox();
	JCheckBox lowBox= new JCheckBox();
	
	JButton startButton = new JButton("PLAY");
	JButton stopButton = new JButton("STOP");
	
	JLabel mapLable;
	
	Clock clock = null;
	ScoreWindow score = null;
	int level ;
	StartGame startGame;
	JPanel clockPanel;
	JLabel scoreLabel;
	
	
	public void init()
	{
		url=getClass().getResource(Constants.mapFileName);
		setLayout(null);
		setSize(1152,864);
		level = Constants.lowTime;
//  general

// top panel		
		this.topPanel.setLayout(null);
		this.topPanel.setBackground(Color.gray);
		this.topPanel.setForeground(Color.white);
		this.topPanel.setBounds(0,0,1152,125);
		
		this.highBox.setText("High");
		highBox.setBackground(Color.lightGray);
		highBox.setBounds(250,75,75,20);
		highBox.setActionCommand("High");
		highBox.addActionListener(new ActionListener()
		    {
            public void actionPerformed(ActionEvent e)
            {
              level = Constants.highTime;
              unCheckBoxes();
              highBox.setSelected(true);
            }
            });
		
		this.midleBox.setText("Midle");
		midleBox.setBackground(Color.lightGray);
		midleBox.setBounds(350,75,75,20);
		midleBox.addActionListener(new ActionListener()
			    {
	            public void actionPerformed(ActionEvent e)
	            {
	              level = Constants.midleTime;
	              unCheckBoxes();
	              midleBox.setSelected(true);
	            }
	            });
		this.lowBox.setText("Low");
		lowBox.setBackground(Color.lightGray);
		lowBox.setBounds(450,75,75,20);
		lowBox.setSelected(true);
		lowBox.addActionListener(new ActionListener()
			    {
	            public void actionPerformed(ActionEvent e)
	            {
	              level = Constants.lowTime;
	              unCheckBoxes();
	              lowBox.setSelected(true);
	            }
	            });
		JLabel playerNameL = new JLabel(" ENTER   YOUR   NAME :");
		playerNameL.setBounds(50,15,150,20);
		this.inputNameField.setBounds(50,35,150,30);
		
		
		topPanel.add(highBox);
		topPanel.add(midleBox);
		topPanel.add(lowBox);
		topPanel.add(playerNameL);
		topPanel.add(inputNameField);
		
		clockPanel = new JPanel(null);
        clockPanel.setBounds(900,50,175,65);
        clockPanel.setBackground(Color.lightGray);
        clockPanel.setVisible(true);
        clock = new Clock(this.clockPanel,this.level);
        topPanel.add(clockPanel);
		
//center panel		
		JPanel picPanel = new JPanel();
		picPanel.setBounds(0,0,606,566);
		
		ImageIcon icon = createImageIcon("resource/map.jpg","a pretty but meaningless splat");
//picPanel
		mapLable = new JLabel(icon);
        mapLable.setBounds(0,0,606,566);
        picPanel.add(mapLable);
		
		centerPanel.setBackground(Color.YELLOW);
		centerPanel.setLayout(null);
		centerPanel.setBounds(300,125,606,566);
		
		centerPanel.add(picPanel);
// left panel
		
		this.startButton.setBackground(Color.white);
		startButton.setBounds(150,430,100,20);
		startButton.setActionCommand("PLAY");
		
		leftPanel.setLayout(null);
		leftPanel.setBackground(Color.cyan);
		leftPanel.setBounds(0,125,300,566);
		leftPanel.add(startButton);
		
//right panel
		this.stopButton.setBackground(Color.white);
		stopButton.setBounds(50,430,100,20);
		stopButton.setActionCommand("STOP");
		stopButton.setEnabled(false);
		
		rightPanel.setLayout(null);
		rightPanel.setBackground(Color.cyan);
		rightPanel.setBounds(906,125,311,566);
		rightPanel.add(stopButton);

		scoreLabel = new JLabel("0");
		scoreLabel.setBackground(Color.darkGray);
		scoreLabel.setFont(new Font("Dialog",1,48));
		scoreLabel.setBounds(100,50,100,100);
		rightPanel.add(scoreLabel);
// main panel		
		add(topPanel);
		add(centerPanel);
		add(leftPanel);
		add(rightPanel);
		
		startButton.addActionListener(this);
		stopButton.addActionListener(this);
		
		//********************************************************************

        
	}
	private void disableBoxes() {

		highBox.setEnabled(false);
		midleBox.setEnabled(false);
		lowBox.setEnabled(false);
	}
	private void enableBoxes() {

		highBox.setEnabled(true);
		midleBox.setEnabled(true);
		lowBox.setEnabled(true);
	}
	private void unCheckBoxes()
	{
		highBox.setSelected(false);
		midleBox.setSelected(false);
		lowBox.setSelected(false);
	}
	public void actionPerformed(ActionEvent e)
	  {
	    if(e.getActionCommand().equals("PLAY"))
	    {
	    	if(inputNameField.getText().length()>0)
	    	{
	    		System.out.println("PLAY");
		    	startGame = new StartGame(leftPanel,this.mapLable,this.level, clock ,"Roman");
		    	startButton.setEnabled(false);
		    	stopButton.setEnabled(true);
		    	disableBoxes();
	    	}
	    	else
	    		JOptionPane.showMessageDialog(this, "PLEASE ENTER NAME", "Error", JOptionPane.ERROR_MESSAGE);
	        
	    }
	    if(e.getActionCommand().equals("STOP"))
	    {
	    	System.out.println("STOP");
	    	DataOwnPlayer.getInstance().stopGame();
	    	clock.stop();
	    	unCheckBoxes();
	    	enableBoxes();
	    	startButton.setEnabled(true);
	    	stopButton.setEnabled(false);
	    	lowBox.setSelected(true);
	    	level = Constants.lowTime;
	    	startGame.removeImages();
	    	inputNameField.setText("");
	    	scoreLabel.setText(""+0);
	    }
	  }
	protected static ImageIcon createImageIcon(String path, String description) 
	{
		java.net.URL imgURL = MainView.class.getResource(path);
		if (imgURL != null) {
		return new ImageIcon(imgURL, description);
		} else {
		System.err.println("Couldn't find file: " + path);
		return null;
		}
	}
	
}
	
	
  


