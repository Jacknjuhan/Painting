package shape;

import java.awt.*;

public class FillRect extends Shape {

	public FillRect(){

	}

	public void draw(Graphics2D g) {
		g.setPaint(color);
		g.setStroke(new BasicStroke(width));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillRect(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
	}
}