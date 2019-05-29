package shape;

import java.awt.*;

public class Rectangle extends Shape {

	public Rectangle(){

	}

	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setStroke(new BasicStroke(width));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2),Math.abs(y1 - y2));
	}
}