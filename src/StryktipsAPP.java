import javax.swing.JFrame;
import javax.swing.JPanel;

public class StryktipsAPP extends JPanel{

    public StryktipsAPP() {
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("Stryktipshjälpen");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
//		frame.setBounds(50, 50, 500, 200);		//placering och storlek på skärmen
        frame.setLocation(50, 50);
//		frame.setSize(500, 200);
//		frame.setResizable(false);
        frame.add(new StryktipsPanel());
        frame.pack();
    }
}
