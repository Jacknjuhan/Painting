import setting.MyFrame;

public class StartProgram {

    public static MyFrame wds;
    public static void main(String[] args) {
        /*
        UIManager.setLookAndFeel("com.birosoft.liquid.LiquidLookAndFeel");
        LiquidLookAndFeel.setLiquidDecorations(true);
        */
        /*
    try {
		//调用Windows的文件系统
		//UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        javax.swing.UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    } catch (Exception e) {
        e.printStackTrace();
		}*/
        wds = new MyFrame("Painting");

    }

}
