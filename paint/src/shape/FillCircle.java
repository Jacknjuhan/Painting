package shape;

import java.awt.*;

public class FillCircle extends Shape{

	public FillCircle(){

	}

	public void draw(Graphics2D g) {
		g.setPaint(color);
		g.setStroke(new BasicStroke(width));
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.fillOval(Math.min(x1, x2), Math.min(y1, y2), Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)), Math.max(Math.abs(x1 - x2), Math.abs(y1 - y2)));
	}
}