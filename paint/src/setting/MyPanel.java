package setting;

import shape.*;
import shape.Rectangle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static setting.MyFrame.*;

class MyPanel extends JPanel {

    public MyPanel() {
        // 设置光标类型，为十字形
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
        //设置背景颜色
        setBackground(Color.white);
        //设置鼠标监听
        addMouseListener(new mouseAction());
        addMouseMotionListener(new MouseMotion());
    }

    //重写paintComponent方法，使得画板每次刷新时可将之前的所有图形重新画出来。
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g; // 定义画板
        int j = 0;

        while (j <= index) {
            draw(g2d, itemList[j]);
            j++;

        }
    }

    void draw(Graphics2D g2d, shape.Shape shape) {

        shape.draw(g2d); // 将画笔传入到各个子类中，用来完成各自的绘图
    }

    // 撤销操作的实现
    public void undo() {
        index--;
        if (index >= 0) {
            if (currentChoice == 3 || currentChoice == 13 || currentChoice == 14) {
                index -= itemList[index].length;
            }
            else {
                index--;
            }
            repaint();

        }
        index++;
        createNewGraphics();
    }

    // 新建一个画图基本单元对象的程序段
    public void createNewGraphics() {

        this.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        switch (currentChoice) {
            case 0:
                itemList[index] = new Images();
                break;
            case 3:
                itemList[index] = new Pencil();
                break;
            case 4:
                itemList[index] = new Line();
                break;
            case 5:
                itemList[index] = new Rectangle();
                break;
            case 6:
                itemList[index] = new FillRect();
                break;
            case 7:
                itemList[index] = new Oval();
                break;
            case 8:
                itemList[index] = new FillOval();
                break;
            case 9:
                itemList[index] = new Circle();
                break;
            case 10:
                itemList[index] = new FillCircle();
                break;
            case 11:
                itemList[index] = new Triangle();
                break;
            case 12:
                itemList[index] = new FillTriangle();
                break;
            case 13:
                itemList[index] = new Rubber();
                break;
            case 14:
                itemList[index] = new Brush();
                break;
            case 15:
                this.setCursor(Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR));
                itemList[index] = new Text();
                itemList[index].s = JOptionPane.showInputDialog("请输入文字");
                itemList[index].fontSize = fSize;
                itemList[index].fontName = fontName;
                this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                break;
        }
        itemList[index].color = color;
        itemList[index].width = stroke;

    }

    // 鼠标事件mouseAction类，继承了MouseAdapter，用来完成鼠标相应事件操作
    class mouseAction extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            itemList[index].x1 = e.getX();
            itemList[index].x2 = e.getX();
            itemList[index].y1 = e.getY();
            itemList[index].y2 = e.getY();
            // 如果当前选择的图形是画笔或者橡皮檫，则进行下面的操作
            if (currentChoice == 3 || currentChoice == 13 || currentChoice == 14) {
                index++;
                lengthCount=1;
                createNewGraphics();
            }
        }

        public void mouseReleased(MouseEvent e) {

            if (currentChoice == 3 || currentChoice == 13 || currentChoice == 14) {
                itemList[index].x1 = e.getX();
                itemList[index].y1 = e.getY();
                lengthCount++;
                itemList[index].length = lengthCount;
            }
            itemList[index].x2 = e.getX();
            itemList[index].y2 = e.getY();
            repaint();
            index++;
            createNewGraphics();
        }
        public void mouseEntered(MouseEvent e) {

        }

        public void mouseExited(MouseEvent e) {

        }
    }

    // 鼠标事件mouseMotion类继承了MouseMotionAdapter,用来完成鼠标拖动和鼠标移动时的响应操作
    class MouseMotion extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            itemList[index].x2 = e.getX();
            itemList[index].y2 = e.getY();
            if (currentChoice == 3 || currentChoice == 13 || currentChoice == 14) {
                itemList[index - 1].x1 = e.getX();
                itemList[index - 1].y1 = e.getY();
                itemList[index].x1 = e.getX();
                itemList[index].y1 = e.getY();
                index++;
                lengthCount++;
                createNewGraphics();
            }
            repaint();
        }
        public void mouseMoved(MouseEvent e) {

        }
    }

}

