package src.view;
import java.awt.*;
import java.net.URL;

import javax.swing.*;


public class Clock  implements Runnable 
{
    JLabel timeLabel;
    JLabel messageLabel;
    JLabel timeCaption;
    JPanel timePanel;
    int time;
    int levelChoiose;
    boolean paused;
    int push;
//    URL bombBad = getClass().getResource(Constants.bombBadFileName);
    Thread thread;
    
    public Clock(JPanel parent, int t) {

        timePanel=parent;
        
        timeCaption = new JLabel("Time :");
        timeCaption.setFont(new Font("Dialog",1,15));
        timeCaption.setBounds(10,25,55,15);
        time = t;
        timeLabel = new JLabel(""+time);
        timeLabel.setFont(new Font("Dialog", 1, 24));
        timeLabel.setBounds(70,25,150,15);



 
        parent.add(timeLabel);
        parent.add(timeCaption);

        timeLabel.setVisible(true);

    }

    public void initialize() {

         push = 0;

      //   messageLabel.setVisible(false);

         timeLabel.setVisible(true);
         timeCaption.setVisible(true) ;

    }
    /**
     * start clock
     */
    public void startClock()
    {
        if (thread == null) {
            initialize(); // inialize default values

            thread = new Thread(this, "Clock"); // create new clock thread
            paused = false; // set paused flag to false
            thread.start(); // start the thread
        }
    }

    /**
     * function pause the clock thread
     */
    public void pauseClock() {
        paused = true;
    }

    /**
     * function make clock thread active again
     */
    public void continueClock() {
        paused = false;
    }

	public int getLevelChoiose() {
		return levelChoiose;
	}
	public void setLevelChoiose(int levelChoiose) {
		this.levelChoiose = levelChoiose;
	}
    /**
     * function stop thread by set his value to the null
     */
    public void stop()
    {
        thread = null;
    }

    public void run() 
    {

        while ( thread != null ) 
        {
           if (DataOwnPlayer.getInstance().isGameActive() )
           {
            
                // get actual time, format it and update timeLabel
                if (time >=0) 
                {

                    timeLabel.setText( ""+time--);
                 }
                else if (time <=0 && push < Constants.bombcount )
                {
                	JOptionPane.showMessageDialog(null,"Time over","time",JOptionPane.INFORMATION_MESSAGE);
                	time = getLevelChoiose();
                	push++;
                	MainView view = (MainView)timePanel.getParent().getParent();
                	String str = view.scoreLabel.getText();
                	int i = Integer.parseInt(str)-1;
                	view.scoreLabel.setText(""+i);
                }
                if(push == Constants.bombcount)
                {
                	thread = null;
                	JOptionPane.showMessageDialog(null,"Game over","time",JOptionPane.INFORMATION_MESSAGE);
                	push =0; 
                	JButton startButton = (JButton)((JPanel)timePanel.getParent().getParent().getComponent(2)).getComponent(0);
                	startButton.setEnabled(true);
                }              
             // wait 1 sec
            	try
                {
                    Thread.sleep( 1000 );
                  
                }
                catch ( InterruptedException e ) 
                {
                   
                }
           }
        }
    }
	public void setTime(int time) {
		this.time = time;
	}
}


       
       
        
  
