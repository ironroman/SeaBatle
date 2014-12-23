/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package src.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.security.jca.GetInstance;


/**
 * @author Roman
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class StartGame {
	
	public JPanel leftPanel;
	public JLabel mapLable;
	public Clock clock;
	public int level;
	public String playerName;
	public int score;

	public int count;
	
	/**
	 * @param leftPanel
	 * @param centerPanel
	 * @param level
	 */
	public StartGame(JPanel leftPanel, JLabel map, int level, Clock c, String playerName) {
		super();
		this.leftPanel = leftPanel;
		this.mapLable = map;
		this.level = level;
		this.clock = c;
		this.playerName = playerName;
//		leftPanel.addMouseListener(new mymouse());
		 map.addMouseListener(new mymouse(new Point(0,0)));
		 map.setName("map");
		 count = Constants.bombcount;
		 init();
		
	}

	private void init()
	{
		DataOwnPlayer.getInstance().startGame();
    	DataOwnPlayer.getInstance().getPlayer().setPlayerName(playerName);  
    	initImages();
    	clock.setTime(this.level);
    	clock.setLevelChoiose(level);
    	clock.startClock();
    	score =0;
    	
	}
	private void initImages()
	{
//		bombs
		URL bomb = getClass().getResource(Constants.bombFileName);
		int shift = 0;
		RandomPoints r = new RandomPoints();
		ArrayList arr = r.getPointsArray();
		for(int i =0; i<Constants.bombcount;i++)
		{
			Point p = (Point)arr.get(i);
			p.setIndex(i);
			JPanel bombPan = new JPanel();
			bombPan.setLayout(null);
			bombPan.setBounds(50,20+shift,80,24);
			Image im = Toolkit.getDefaultToolkit().getImage(bomb);
			ImageIcon image = new ImageIcon(im);
			JLabel lab = new JLabel(image);
			lab.setName("bomb"+i);
			lab.addMouseListener(new mymouse(p));
			JLabel pointL = new JLabel(p.format());
			lab.setBounds(0,0,30,24);
			pointL.setBounds(30,0,50,24);
			bombPan.add(lab);
			bombPan.add(pointL);
			leftPanel.add(bombPan);
			shift+=40;
			
			int w = image.getIconWidth()/2;
			int h = image.getIconHeight()/2;
			
			JLabel shipL = new JLabel(image);
			int cX = Constants.X0+p.getX()*Constants.OneX-w;
			int cY = Constants.Y0-p.getY()*Constants.OneY-h;
			shipL.setName(p.format());
			shipL.setBounds(0,0,cX,cY);
			this.mapLable.add(shipL);
			this.mapLable.repaint();
			
			System.out.println("Ship X =" + cX + " Point x ="+p.getX());
			System.out.println("Ship Y = " + cY + "Point y = "+p.getY());
		}
		leftPanel.repaint();
		//centerPanel.repaint();
		System.out.println("initImages");
	}
	public void removeImages()
	{
		this.mapLable.removeAll();
		JButton temButton =(JButton) this.leftPanel.getComponent(0);
		this.leftPanel.removeAll();
		this.leftPanel.add(temButton);
		this.mapLable.repaint();
		this.leftPanel.repaint();
	}
	class mymouse extends MouseAdapter
	{
    	int counter=0;
		int cordx;
		int cordy;
		int num;
		int num3;
		Point point;
		URL bombGood = getClass().getResource(Constants.bombGoodFileName);
	    URL bombBad = getClass().getResource(Constants.bombBadFileName);
		/**
		 * @param point
		 */
		public mymouse(Point point) {
			super();
			this.point = point;
		}
		public void mousePressed(MouseEvent e)
		{
			cordx=e.getX();//-48;
			cordy=e.getY();//-264;
//			num=getrectnum(cordx,cordy);
//			num2=LeftPanel.myint[num].intValue();
			System.out.println("mymouse.mousePressed()");
			System.out.println("cordx = "+ cordx);
			System.out.println("cordy = "+cordy);
			Component comp = e.getComponent();
			if(!comp.getName().equals("map"))
			{
				System.out.println("Name "+comp.getName());

				comp.setVisible(false);
			}
		}
		public void mouseReleased(MouseEvent e)
		{
		    System.out.println("mymouse.mouseReleased()");   
		    
			JLabel comp = (JLabel) e.getComponent();
			if(!comp.getName().equals("map"))
			{
				if(inRange(e,this.point))
				{
					
					
					clock.time = level;
					count --;
					
					Image img = Toolkit.getDefaultToolkit().getImage(this.bombGood);
					ImageIcon i = new ImageIcon(img);
					comp.setIcon(i);
					comp.setVisible(true);
					MainView view= (MainView)leftPanel.getParent();
					score = Integer.parseInt(view.scoreLabel.getText())+2;
					view.scoreLabel.setText(""+score);
					if(count == 0)
					{
						JOptionPane.showMessageDialog(null,"Game over","time",JOptionPane.INFORMATION_MESSAGE);
	                	clock.stop();
	                	
	                	
	                	view.highBox.setSelected(false);
	                	view.midleBox.setSelected(false);
	                	view.lowBox.setSelected(false);
	                	view.highBox.setEnabled(true);
	                	view.midleBox.setEnabled(true);
	                	view.lowBox.setEnabled(true);
	                	view.startButton.setEnabled(true);
	                	view.stopButton.setEnabled(false);
	                	view.lowBox.setSelected(true);
	        	    	level = Constants.lowTime;
	        	    	removeImages();
	        	    	view.inputNameField.setText("");
	        	    	view.scoreLabel.setText(""+0);
					}
				}
				else
					comp.setVisible(true);
			}
			System.out.println(point.format());
		}
		
		private boolean inRange(MouseEvent event, Point p)
		{
			int cdx=event.getX();//-48;
			int cdy=event.getY();//-264;
			int range = 10;
			int mapX1 = p.getMapPoints().getX()-range;
			int mapX2 = p.getMapPoints().getX()+range;
			
			int mapY1 = p.getMapPoints().getY()- range;
			int mapY2 = p.getMapPoints().getY() + range;
			
			System.out.println("cdx = "+ cdx);
			System.out.println("cdy = "+cdy);
			if(cdx >= mapX1 && cdx <= mapX2 && cdy >=mapY1 && cdy <= mapY2)
			{
				System.out.println("RETURN TRUE inRange");
				Component [] c = mapLable.getComponents();
				for(int i=0; i< c.length;i++)
				{
					JLabel lab = (JLabel) c[i];
					if(lab.getName().equals(p.format()))
					{
						Image img = Toolkit.getDefaultToolkit().getImage(this.bombBad);
						ImageIcon ic = new ImageIcon(img);
						lab.setIcon(ic);
						
					}
						
				}
				
//				
//				JLabel l = new JLabel(i);
//				Point mapP = p.getUnMapPoint();
//				l.setBounds(0,0,mapP.getX(),mapP.getY());
//				mapLable.add(l);
//				mapLable.repaint();
				return true;
			}
			return false;
		}

	}
}
