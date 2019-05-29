package setting;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class ColorPanel extends JPanel {

    // 左边面板添加子面板
    public Color bcolor = Color.WHITE;
    public Color fcolor = Color.BLACK;
    // 颜色数组，用来设置按钮的背景颜色
    private Color[] array = { new Color(255, 255, 255), new Color(0, 0, 0), new Color(127, 127, 127),
            new Color(195, 195, 195), new Color(136, 0, 21), new Color(185, 122, 87), new Color(237, 28, 36),
            new Color(255, 174, 201), new Color(255, 127, 39), new Color(255, 242, 0), new Color(239, 228, 176),
            new Color(34, 117, 76), new Color(181, 230, 29), new Color(0, 162, 232), new Color(153, 217, 234),
            new Color(63, 72, 204), new Color(112, 146, 190), new Color(163, 73, 164), new Color(200, 191, 231),
            new Color(89, 173, 154), new Color(8, 193, 194), new Color(9, 253, 76), new Color(153,217,234),
            new Color(199, 73, 4) };

    public ColorPanel() {
        addColorPanel();
    }

    private void addColorPanel() {
        JPanel panel0 = new JPanel();
        panel0.setPreferredSize(new Dimension(30, 30));
        panel0.setLayout(null);

        final JButton btnB = new JButton();
        final JButton btnW = new JButton();

        btnB.setBounds(5, 5, 15, 15);
        btnW.setBounds(10, 10, 15, 15);

        btnB.setBackground(Color.black);
        btnW.setBackground(Color.white);
        panel0.add(btnB);
        panel0.add(btnW);
        // 将可换颜色面板添加到总的颜色面板上
        this.add(panel0);
        MouseAdapter ma = new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                // 把左面板中的按钮颜色设置成选中按钮的背景颜色
                JButton btn = (JButton) e.getSource();
                //判断当前点击的是否是左键，如果是则改变背景色
                if (e.getButton() == 1) {
                    //获取到按钮上的颜色了
                    fcolor = btn.getBackground();
                    MyFrame.color = fcolor;
                    MyFrame.itemList[MyFrame.index].color = fcolor;
                    //改变背景色按钮上的颜色
                    btnB.setBackground(fcolor);
                }
                else if (e.getButton() == 3) {//点击右键
                    //获取到按钮上的颜色了
                    bcolor = btn.getBackground();
                    MyFrame.color = bcolor;
                    MyFrame.itemList[MyFrame.index].color = bcolor;
                    //改变背景色按钮上的颜色
                    btnW.setBackground(bcolor);
                }
            }
        };

        JPanel panel1 = new JPanel();
        for (int i = 0; i < array.length; i++) {
            JButton jbn = new JButton();
            jbn.setPreferredSize(new Dimension(20, 20));
            jbn.setBackground(array[i]);
            jbn.addMouseListener(ma);
            panel1.add(jbn);
        }
        panel1.setLayout(new GridLayout(2, 24));
        // 设置为从左至右的流式布局
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBackground(null);
        this.add(panel1);
    }
}