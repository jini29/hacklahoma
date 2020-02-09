import java.awt.Color;
import java.awt.Graphics;

public class SnakeObject {


	private int xPosition;
	private int yPosition;
	private int width;
	private int height;
	
	public SnakeObject(int x, int y, int width, int height){
		xPosition = x;
		yPosition = y;
		this.width = width;
		this.height = height;
		
	}
	
	public void draw(Graphics g, Color color) {
		g.setColor(color);
		g.fillRect(xPosition * width, yPosition * height, width, height);
		
	}
	public int getXPosition() {
		return this.xPosition;
	}
	
	public int getYPosition() {
		return this.yPosition;
	}
	public void setXPosition(int x) {
		xPosition =x;
	}
	public void setYPosition (int y) {
		yPosition = y;
	}
}


