/*
 * Created on Mar 1, 2007
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package src.view;

/**
 * @author Roman
 *
 * TODO To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class Point {
	private int x;
	private int y;
	private int index;
	
	/**
	 * @param x
	 * @param y
	 */
	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		
	}
	
	
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String format()
	{
		return "("+getX()+";"+getY()+")";
	}
	public Point getMapPoints()
	{
		int mapX = Constants.mapX+this.getX()*Constants.mapXshift;
		int mapY =Constants.mapY-Constants.indexShift*this.getIndex()-this.getY()*Constants.mapYshift;
		
		Point p = new Point(mapX,mapY);
		p.setIndex(this.getIndex());
		return p;
	}
	public Point getUnMapPoint()
	{
		int cX = Constants.X0+this.getX()*Constants.OneX-17;
		int cY = Constants.Y0-this.getY()*Constants.OneY-10;
		Point p = new Point(cX,cY);
		p.setIndex(this.getIndex());
		return p;
	}
}
