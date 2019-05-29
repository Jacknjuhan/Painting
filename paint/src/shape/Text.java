package shape;

import java.awt.*;

public class Text extends Shape {

	public Text(){

	}

	public void draw(Graphics2D g) {

		g.setColor(color);
		Font font = new Font(fontName, Font.BOLD, fontSize);
		g.setFont(font);
		if (s != null) {
			g.drawString(s, x1, y1);
		}
	}
}