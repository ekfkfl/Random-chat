package ramchat.view.joptionpane;

import javax.swing.JOptionPane;

public class FailView extends JOptionPane {
	public static void failMessage(String message) {
		showMessageDialog(null, message);
	}
}
