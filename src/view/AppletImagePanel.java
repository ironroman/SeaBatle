/*
 * Created on Feb 28, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package src.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Panel;
import java.awt.Toolkit;
import java.net.URL;

/**
 * @author Roman
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
class AppletImagePanel extends Panel
{
    Image image;
    MediaTracker tracker;
    
    public AppletImagePanel()
    {
        loadImage();
    }
 
    public void paint(Graphics g)
    {
        super.paint(g);
        int w = getWidth();
        int h = getHeight();
        int imageWidth = image.getWidth(this);
        int imageHeight = image.getHeight(this);
        System.out.println("w ="+w+"\nh= "+h +"\n imageWidth = "+imageWidth+ "\n imageHeight= "+imageHeight);

        g.drawImage(image, 0, 0, this);
    }
 
    public Dimension getPreferredSize()
    {
        return new Dimension(image.getWidth(this), image.getHeight(this));
    }
 
    private void loadImage()
    {
        String fileName = "resource/map.jpg";
        tracker = new MediaTracker(this);
        URL url = getClass().getResource(fileName);
        image = Toolkit.getDefaultToolkit().getImage(url);
        tracker.addImage(image, 0);
        try
        {
            tracker.waitForID(0);
        }
        catch(InterruptedException ie)
        {
            System.err.println("interrupt: " + ie.getMessage());
        }
    }
    
    
	public MediaTracker getTracker() {
		return tracker;
	}
	public void setTracker(MediaTracker tracker) {
		this.tracker = tracker;
	}
}