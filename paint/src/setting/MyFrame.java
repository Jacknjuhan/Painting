package setting;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import shape.Shape;

public class MyFrame extends JFrame {

    public static int saved = 0;
    // 声明颜色属性，并赋默认值
    public static Color c = Color.black;
    public static int lengthCount = 0; // 铅笔或橡皮擦图形的存储长度
    public static String fontName = new String(" 宋体 ");
    public static int fSize = 16;
    public static int index = 0;// 图形形状的标记
    public static Shape[] itemList = new Shape[5000];// 图形存储单元
    public static int stroke = 1;// 画笔粗细
    public static Color color = Color.black;// 用于存放当前颜色
    public static int currentChoice = 3; // 初始状态是画笔
    public static JLabel statusBar;// 鼠标状态
    public static MyPanel drawingArea; // 画图区域
    // 菜单类
    MyMenu menu;

    // 工具条
    MyToolbar myToolbar;

    // 调色板
    ColorPanel colorPanel;

    public MyFrame() {

    }

    public MyFrame(String s) {
        this.setTitle(s);
        this.setSize(1100, 800);// 设置窗口大小
        this.setLocationRelativeTo(null);// 居中显示

        menu = new MyMenu();//菜单

        myToolbar = new MyToolbar();
        colorPanel = new ColorPanel();
        drawingArea = new MyPanel();
        add(colorPanel, BorderLayout.SOUTH);
        add(drawingArea, BorderLayout.CENTER);
        statusBar = new JLabel();//标签组件
        statusBar.setBackground(new Color(195, 195, 195));
        statusBar.setOpaque(true);// 设置该组件为透明
        statusBar.setHorizontalAlignment(SwingConstants.CENTER);

        drawingArea.createNewGraphics();

        this.setVisible(true);
    }

    class MyMenu {
        /**
         * 菜单初始化部分
         */

        private JMenuBar jMenuBar;// 菜单条
        private JMenuItem file_item_new, file_item_open, file_item_save, file_item_exit;// 定文件菜单的菜单项
        private JMenuItem help_item_about;
        private JMenuItem help_item_manual;
        private JMenu file_menu,help_menu;// 定义文件、帮助菜单
        public MyMenu() {

            jMenuBar = new JMenuBar();
            // 实例化菜单对象
            file_menu = new JMenu("File");
            help_menu = new JMenu("Help");
            file_item_new = new JMenuItem("new");
            file_item_open = new JMenuItem("open");
            file_item_save = new JMenuItem("save");
            file_item_exit = new JMenuItem("exit");
            help_item_manual = new JMenuItem("manual");
            help_item_about = new JMenuItem("about");


            // 给文件菜单设置监听
            file_item_new.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.newFile();
                }
            });

            file_item_open.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    // 打开文件，并将标志符saved设置为0
                    menu.openFile();
                    saved = 0;
                }
            });

            file_item_save.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.saveFile();
                    saved = 1;
                }
            });

            file_item_exit.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (saved == 1) {//直接退出
                        System.exit(0);
                    }
                    else {          //用户选择是否未保存就退出
                        int n = JOptionPane.showConfirmDialog(null, "您还没保存，确定要退出？", "提示", JOptionPane.OK_CANCEL_OPTION);
                        if (n == JOptionPane.OK_OPTION) {
                            System.exit(0);
                        }

                    }
                }
            });

            help_item_manual.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "略" , "使用说明", JOptionPane.PLAIN_MESSAGE);

                }
            });

            help_item_about.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "" + "DATE: 2019-5-28\n"+"NAME: 韩传兵\n"+"NJUID: 161220044" , "About", JOptionPane.PLAIN_MESSAGE);

                }
            });

            // 添加菜单项到菜单
            file_menu.add(file_item_new);
            file_menu.add(file_item_open);
            file_menu.add(file_item_save);
            file_menu.add(file_item_exit);
            help_menu.add(help_item_manual);
            help_menu.add(help_item_about);
            // 添加菜单到菜单条
            jMenuBar.add(file_menu);
            jMenuBar.add(help_menu);
            // 添加菜单条
            setJMenuBar(jMenuBar);
        }


        //新建文件
        private void newFile() {
            //变量重置
            color = Color.black;
            stroke = 1;
            index = 0;
            currentChoice = 3;
            drawingArea.createNewGraphics();
            repaint();
        }
        // 打开文件
        private void openFile() {
            // 文件选择器
            JFileChooser fileChooser = new JFileChooser();
            // 只选文件
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);

            // 返回当前的文本过滤器，并设置成当前的选择
            fileChooser.setFileFilter(fileChooser.getFileFilter());
            // 弹出一个 "Open File" 文件选择器对话框
            int result = fileChooser.showOpenDialog(MyFrame.this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            // 得到选择文件的名字
            File fileName = fileChooser.getSelectedFile();
            BufferedImage image;
            try {
                index = 0;
                image = ImageIO.read(fileName);
                drawingArea.createNewGraphics();
                itemList[index].image = image;
                itemList[index].board = drawingArea;
                drawingArea.repaint();
                index++;
                currentChoice = 3;
                drawingArea.createNewGraphics();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        // 保存图形文件
        private void saveFile() {
            // 文件选择器
            JFileChooser fileChooser = new JFileChooser();
            // 设置文件显示类型为仅显示文件
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setMultiSelectionEnabled(false);
            // 返回当前的文本过滤器，并设置成当前的选择
            fileChooser.setFileFilter(fileChooser.getFileFilter());
            // 弹出一个 文件选择器对话框
            int result = fileChooser.showSaveDialog(MyFrame.this);
            if (result == JFileChooser.CANCEL_OPTION) {
                return;
            }
            File fileName = fileChooser.getSelectedFile();
            String t = fileName.getPath() + ".png";
            fileName = new File(t);
            fileName.canWrite();
            BufferedImage image = createImage(drawingArea);
            try {
                ImageIO.write(image, "png", fileName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        // 将画板内容画到panelImage上
        private BufferedImage createImage(MyPanel panel) {

            int width = MyFrame.this.getWidth();
            int height = MyFrame.this.getHeight();
            BufferedImage panelImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2D = (Graphics2D) panelImage.createGraphics();

            g2D.setColor(Color.WHITE);
            g2D.fillRect(0, 0, width, height);
            g2D.translate(0, 0);
            panel.paint(g2D);
            g2D.dispose();
            return panelImage;
        }
    }

    class MyToolbar {
        /**
         * 工具栏初始化部分
         */
        private String[] tmp={"保存","清空","撤销","铅笔","直线","矩形","填充矩形","椭圆","填充椭圆","圆","填充圆","三角形","填充三角形","橡皮擦","涂鸦","文本"};
        private JButton[] btn_paint;// 定义各种绘图的按钮
        private JComboBox<String> jline_size;
        private JComboBox<String> jfont;
        private JComboBox<String> jfont_size;
        private JToolBar toolbar; // 定义按钮面板
        private String lineSize[] = {"1","5","15","20"};
        private String font[] = {"宋体", "隶书", "华文彩云", "仿宋_GB2312", "华文行楷"};
        private String fontSize[] = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};

        public MyToolbar() {

            btn_paint = new JButton[16];// 定义指定个数的按钮
            toolbar = new JToolBar("工具栏");// 实例化一个水平的工具标签
            //toolbar.setLayout(new GridLayout(5, 2, 4, 4));

            toolbar.setLayout(new FlowLayout());
            toolbar.setBackground(new Color(195, 195, 195));

            jline_size = new JComboBox(lineSize);
            jline_size.setPreferredSize(new Dimension(45, 30));
            jfont_size = new JComboBox(fontSize);
            jfont_size.setPreferredSize(new Dimension(45, 30));
            jfont = new JComboBox(font);
            jfont.setPreferredSize(new Dimension(80, 30));

            for (int i = 0; i < 16; i++) {
                btn_paint[i] = new JButton(tmp[i]);
                switch (tmp[i].length()) {// 设置图标大小
                    case 1:
                        btn_paint[i].setPreferredSize(new Dimension(25, 30));
                        break;
                    case 2:
                        btn_paint[i].setPreferredSize(new Dimension(40, 30));
                        break;
                    case 3:
                        btn_paint[i].setPreferredSize(new Dimension(55, 30));
                        break;
                    case 4:
                        btn_paint[i].setPreferredSize(new Dimension(65, 30));
                        break;
                    case 5:
                        btn_paint[i].setPreferredSize(new Dimension(80, 30));
                        break;
                }
                btn_paint[i].setBackground(Color.WHITE);
                toolbar.add(btn_paint[i]);
            }
            //toolbar.setLayout(new FlowLayout());

            btnAddActionListener(); // 将动作侦听器加入到按钮里面

            // 设置粗细
            toolbar.add(jline_size);
            jline_size.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    stroke = Integer.parseInt(lineSize[jline_size.getSelectedIndex()]);
                    itemList[index].width = stroke;
                }
            });

            // 设置字体大小
            toolbar.add(jfont_size);
            jfont_size.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    fSize = Integer.parseInt(fontSize[jfont_size.getSelectedIndex()]);
                    // System.out.println(fSize);
                }
            });

            // 设置字体
            toolbar.add(jfont);
            jfont.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    fontName = font[jfont.getSelectedIndex()];
                    // System.out.println(fontName);
                }
            });
            MyFrame.this.add(toolbar, BorderLayout.NORTH);// 添加按钮面板到容器中
        }
        private void btnAddActionListener() {
            btn_paint[0].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.saveFile();
                    saved = 1;

                }
            });
            btn_paint[1].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    menu.newFile();

                }
            });
            btn_paint[2].addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    drawingArea.undo();

                }
            });

            for (int i = 3; i < 16; i++) {
                btn_paint[i].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        for (int j = 0; j < 16; j++) {
                            // 如果按钮被点击。则设置相应的画笔
                            if (e.getSource() == btn_paint[j]) {
                                currentChoice = j;
                                drawingArea.createNewGraphics();
                                repaint();
                            }
                        }
                    }
                });
            }
        }
    }

}

