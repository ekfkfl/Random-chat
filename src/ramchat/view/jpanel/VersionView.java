package ramchat.view.jpanel;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Color;
import java.awt.GridLayout;

/**
 *	버전정보
 */
public class VersionView extends JPanel {

	String imagePath = "src/resource/Symbol.png";
	ImageIcon icon;
	JPanel pnlSymbol;
	JLabel lbVersion = new JLabel("현재버전 1.0.0");
	JLabel lbComment = new JLabel("\uD604\uC7AC \uCD5C\uC2E0 \uBC84\uC804\uC785\uB2C8\uB2E4.");
	
	/**
	 * 생성자
	 */
	public VersionView() {

		try {
			// Symbol 이미지 삽입
			icon = new ImageIcon(ImageIO.read(new File(imagePath)));
		}catch(IOException e){
			System.out.println("버전패널 에러: "+e.getMessage());
		}
		pnlSymbol = new JPanel() {
			public void paint(Graphics g) {
				g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		pnlSymbol.setBackground(Color.GREEN);
		
		lbVersion.setHorizontalAlignment(SwingConstants.CENTER);
		lbVersion.setFont(new Font("굴림", Font.BOLD, 18));
		lbComment.setHorizontalAlignment(SwingConstants.CENTER);
		lbComment.setFont(new Font("굴림", Font.BOLD, 18));
		
		//GroupLayout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(188)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(pnlSymbol, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(lbVersion, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE)
						.addComponent(lbComment, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 229, Short.MAX_VALUE))
					.addGap(201))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(48)
					.addComponent(pnlSymbol, GroupLayout.DEFAULT_SIZE, 215, Short.MAX_VALUE)
					.addGap(54)
					.addComponent(lbVersion, GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(lbComment, GroupLayout.DEFAULT_SIZE, 42, Short.MAX_VALUE)
					.addGap(98))
		);
		setLayout(groupLayout);

	}
}
