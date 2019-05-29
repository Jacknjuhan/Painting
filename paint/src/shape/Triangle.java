package shape;

import java.awt.*;

public class Triangle extends Shape{

	public Triangle(){

	}

	public void draw(Graphics2D g){
		g.setPaint(color);
		g.setStroke(new BasicStroke(width, BasicStroke.CAP_ROUND, BasicStroke.JOIN_BEVEL));
		int[] x = { (x1 + x2) / 2, x1, x2 };
		int[] y = { y1, y2, y2 };
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawPolygon(x, y, 3);
	}
}