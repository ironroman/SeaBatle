/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package src.view;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author Roman
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class RandomPoints {

	private int coordX;
	private int coordY;
	private ArrayList pointsArray;
	private Point point;
	
	
	
	/**
	 * 
	 */
	public RandomPoints() {
		pointsArray = new ArrayList();
		lotCoordinates();
	}


	/**
	 * 
	 */
	private void lotCoordinates() {
	    Random generator = new Random();
	    int r =0;
	    for(int i = 0; i< Constants.bombcount;i++){
		    int n = generator.nextInt(Constants.mapSize);
		    int k = generator.nextInt(Constants.mapSize);
	    	setCoordX(Constants.mapSize/2-n);
		    setCoordY(Constants.mapSize/2-k);
		    point = new Point(getCoordX(), getCoordY());
		    pointsArray.add(i,point);
	    }
	};
	
	
	public int getCoordX() {
		return coordX;
	}
	public void setCoordX(int coordX) {
		this.coordX = coordX;
	}
	
	public int getCoordY() {
		return coordY;
	}
	public void setCoordY(int coordY) {
		this.coordY = coordY;
	}
	
	
	public ArrayList getPointsArray() {
		return pointsArray;
	}
}
