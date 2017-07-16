package ramchat.view.jdialog;

import javax.swing.JDialog;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;

/**
 *	시간연장수락화면
 *	- ChatView에서 btnAddTime 클릭시 상대방에게만 실행된다.
 *  - 남-녀 매칭이 성공하면 창이 자동으로 닫힌다.
 *	- reSize : false(사용자 크기조정 불가)
 */
public class AddTimeDialog extends JDialog {
	public AddTimeDialog() {

		JLabel lblmessage1 = new JLabel("상대방이 시간연장을 신청했습니다!");
		JLabel lblmessage2 = new JLabel("수락하시겠습니까?");
		JLabel lblmessage3 = new JLabel("[☆Tip] 수락하시면 프로필사진 일부가 오픈됩니다.");
		JButton btnAccept = new JButton("수락하기");
		JButton btnRefusal = new JButton("거절하기");
		
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(89, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblmessage3)
						.addComponent(lblmessage2)
						.addComponent(lblmessage1))
					.addGap(79))
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addGap(131)
					.addComponent(btnAccept)
					.addGap(18)
					.addComponent(btnRefusal)
					.addContainerGap(123, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(60)
					.addComponent(lblmessage1)
					.addGap(27)
					.addComponent(lblmessage2)
					.addGap(26)
					.addComponent(lblmessage3)
					.addPreferredGap(ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnRefusal)
						.addComponent(btnAccept))
					.addGap(32))
		);
		getContentPane().setLayout(groupLayout);
		
		this.setSize(400, 300);
		this.setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}
}
