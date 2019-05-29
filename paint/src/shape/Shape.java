package shape;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.JPanel;

public abstract class Shape implements Serializable {

    public int x1, y1, x2, y2;// 绘制图形的坐标
    public Color color;// 画笔颜色
    public int width;// 画笔粗细
    public int length; // 铅笔或橡皮擦的笔迹长度
    public BufferedImage image; // 存放待打开图片
    public JPanel board; // 绘画的画板
    public int  fontSize;//字体大小
    public String fontName;//字体
    public String s;//文本
    //抽象方法
    public abstract void draw(Graphics2D g);

}