package ramchat.view.jpanel;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;

import ramchat.controller.ChatController;
import ramchat.controller.impl.ChatControllerImpl;
import ramchat.controller.impl.FileClientConnetControllerImpl;
import ramchat.controller.impl.ServerConnetController;
import ramchat.view.jdialog.ChatWaitDialog;
import ramchat.view.jframe.MainView;
import usersession.UserSession;

/**
 * ä��ȭ��
 */
public class ChatView extends JPanel implements ActionListener {
	public ChatWaitDialog chatWaitDialog = null;
	MainView mainView = null;
	ChatController chatController = new ChatControllerImpl();

	// ä�� VIEW, ä���Է�
	JTextArea tAreaBoard = new JTextArea();
	JScrollPane jsp = new JScrollPane(tAreaBoard);
	JTextField tFieldMsg = new JTextField();
	JButton btnSendMsg = new JButton("������");

	// User ���� ȣ��
	JLabel lblUserA = new JLabel("���� ����");
	ImagePanel2 pnlUserA = new ImagePanel2();
	JLabel lblUserB = new JLabel("���� ����");
	ImagePanel2 pnlUserB = new ImagePanel2();

	// ��� Button
	JButton btnExit = new JButton("������");
	JButton btnAddTime = new JButton("�ð�����");
	JButton btnHeart = new JButton("���ƿ䢾");

	int limitTime2;
	JLabel lblNewLabel = new JLabel(); // ä�ó����ð� ǥ���ϴ� ��

	int count = 0; // �г��� ��ġ�� �ĺ��� count����

	FileClientConnetControllerImpl fileConnect = new FileClientConnetControllerImpl();

	ServerConnetController serverConnect = new ServerConnetController();
	PrintWriter pw = serverConnect.getPrintWriter();
	BufferedReader br = serverConnect.getBufferedReader();
	Socket sk = serverConnect.getSocket();
	String firstId;
	String secondId;

	class ImagePanel2 extends Canvas {
		private ImageIcon img;
		private BufferedImage bImg;
		private int temp;

		public ImageIcon getCover() {
			ImageIcon cover = new ImageIcon("src/resource/question.jpg");
			return cover;
		}

		public void setImg(ImageIcon img) {
			this.img = img;
		}

		public void setbImg(BufferedImage bImg) {
			this.bImg = bImg;
		}

		@Override
		public void paint(Graphics g) {
			g.drawImage(getCover().getImage(), 0, 0, getWidth(), getHeight(), this);
		}

		@Override
		public void update(Graphics g) {
			if (bImg != null) {
				temp += 20;
				if (temp >= 100) {
					temp = getWidth();
				}
				g.setClip(0, 0, getWidth(), temp);
				g.drawImage(bImg, 0, 0, getWidth(), getHeight(), this);
			}
		}
	}

	/**
	 * ����
	 */
	public ChatView(MainView mainView) {
		this.mainView = mainView;
		chatWaitDialog = new ChatWaitDialog(mainView);

		try {
			pw.println("request");
			firstId = br.readLine();
			secondId = br.readLine();
			chatWaitDialog.dispose();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String myId = UserSession.getId();

		if (myId.equals(firstId)) {
			pnlUserA.setbImg(fileConnect.getImg(secondId));
			pnlUserB.setbImg(fileConnect.getImg(myId));
			UserSession.setSecondId(secondId);
		} else {
			pnlUserA.setbImg(fileConnect.getImg(firstId));
			pnlUserB.setbImg(fileConnect.getImg(myId));
			UserSession.setSecondId(firstId);
		}

		tAreaBoard.setEditable(false);
		tAreaBoard.setBackground(new Color(242, 206, 213));

		// �̹������ �ӽ÷� �̹���ȭ�鱸��...line86~87
		pnlUserA.setBackground(Color.GRAY);
		pnlUserB.setBackground(Color.GRAY);

		// GroupLayout
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
				.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
						.createSequentialGroup().addComponent(jsp, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout
								.createSequentialGroup().addGap(5)
								.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING).addGroup(groupLayout
										.createParallelGroup(Alignment.LEADING)
										.addComponent(btnExit, GroupLayout.PREFERRED_SIZE, 107,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup().addGap(5).addComponent(lblUserA,
												GroupLayout.PREFERRED_SIZE, 70, GroupLayout.PREFERRED_SIZE))
										.addComponent(pnlUserA, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE)
										.addGroup(groupLayout.createSequentialGroup().addGap(5).addComponent(lblUserB,
												GroupLayout.PREFERRED_SIZE, 60, GroupLayout.PREFERRED_SIZE))
										.addComponent(pnlUserB, GroupLayout.PREFERRED_SIZE, 105,
												GroupLayout.PREFERRED_SIZE))
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(btnAddTime, GroupLayout.PREFERRED_SIZE, 107,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(btnHeart, GroupLayout.PREFERRED_SIZE, 107,
														GroupLayout.PREFERRED_SIZE))))
								.addGroup(groupLayout.createSequentialGroup()
										.addPreferredGap(ComponentPlacement.UNRELATED).addComponent(lblNewLabel))))
						.addGroup(groupLayout.createSequentialGroup()
								.addComponent(tFieldMsg, GroupLayout.DEFAULT_SIZE, 321, Short.MAX_VALUE).addGap(5)
								.addComponent(btnSendMsg, GroupLayout.PREFERRED_SIZE, 107, GroupLayout.PREFERRED_SIZE)))
				.addGap(5)));
		groupLayout
				.setVerticalGroup(
						groupLayout
								.createParallelGroup(
										Alignment.TRAILING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addGroup(groupLayout
														.createSequentialGroup()
														.addComponent(jsp, GroupLayout.DEFAULT_SIZE, 306,
																Short.MAX_VALUE)
														.addGap(1))
												.addGroup(groupLayout.createSequentialGroup().addGap(12)
														.addComponent(btnExit).addGap(7).addComponent(lblUserA)
														.addGap(7)
														.addComponent(pnlUserA, GroupLayout.PREFERRED_SIZE, 107,
																GroupLayout.PREFERRED_SIZE)
														.addGap(7).addComponent(lblUserB).addGap(7)
														.addComponent(pnlUserB, GroupLayout.PREFERRED_SIZE, 107,
																GroupLayout.PREFERRED_SIZE)
														.addPreferredGap(ComponentPlacement.RELATED, 63,
																Short.MAX_VALUE)
														.addComponent(lblNewLabel).addGap(52)))
										.addGap(18)
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(tFieldMsg, GroupLayout.PREFERRED_SIZE,
														GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
												.addComponent(btnSendMsg, GroupLayout.PREFERRED_SIZE, 25,
														GroupLayout.PREFERRED_SIZE))
										.addGap(8))
								.addGroup(groupLayout.createSequentialGroup().addContainerGap(265, Short.MAX_VALUE)
										.addComponent(btnAddTime).addGap(7).addComponent(btnHeart).addGap(40)));
		setLayout(groupLayout);
		/** ������ ��� */
		/*
		 * JButton btnExit = new JButton("������"); JButton btnAddTime = new
		 * JButton("\uC2DC\uAC04\uC5F0\uC7A5"); JButton btnHeart = new
		 * JButton("\uC88B\uC544\uC694\u2665");
		 */
		btnExit.addActionListener(this);
		btnAddTime.addActionListener(this);
		btnHeart.addActionListener(this);
		tFieldMsg.addActionListener(this);
		btnSendMsg.addActionListener(this);

		// �޴� ������
		new Thread() {
			public void run() {
				try {
					String data = "";
					while ((data = br.readLine()) != null) {
						if (data.equals("�ð��� �߰��Ǿ����ϴ�.")) {

							if (count < 5) {
								count++;
								limitTime2 += 180; // 3�� �߰�
								pnlUserA.repaint();
								pnlUserB.repaint();
							} else if (count >= 5)
								btnAddTime.setEnabled(false);
						}
						if (!data.equals("heartup"))
							tAreaBoard.append(data + "\n");
						else {
							JOptionPane.showMessageDialog(null, UserSession.getSecondId() + "�� ���� ��Ʈ�� �޾ҽ��ϴ�.");
							panelRevalidate2();
						}

						// ��ũ�ѹ��� ��ġ�� �̵�
						int length = tAreaBoard.getText().length();
						tAreaBoard.setCaretPosition(length);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();

		// ���������
		timeThread.start();

	}// ������ ��

	// ä�� �����ð� ������ ����
	Thread timeThread = new Thread() {
		public void run() {

			try {
				for (limitTime2 = 300; limitTime2 >= 0; limitTime2--) { // limitTime2:
					// �ʱⰪ(5��)����
					// ����
					int min2 = limitTime2 / 60;
					int sec2 = limitTime2 % 60;

					String min = String.format("%02d", min2);
					String sec = String.format("%02d", sec2);

					// ä��â�� ������ �����ð�
					String limitTime = min + ":" + sec;

					lblNewLabel.setText("�����ð� - " + limitTime);

					Thread.sleep(1000);

					// time�� 0�̸� ä�ù��� ��������
					if (limitTime2 == 0) {
						pw.println("exit");

						mainView.btnMain.setEnabled(true);
						mainView.btnRanking.setEnabled(true);
						mainView.btnMypage.setEnabled(true);
						mainView.btnVersionInfo.setEnabled(true);
						mainView.btnChatting.setEnabled(true);
						mainView.card.show(mainView.pnlMiddle, "PanelMain");
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			// time�� 0�̸� ä�ù��� ��������

		};
	};

	Thread addBbnThread = null;

	// ������
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 1. ä�� ������
		if (obj == btnExit) {
			pw.println("exit");
			mainView.btnMain.setEnabled(true);
			mainView.btnRanking.setEnabled(true);
			mainView.btnMypage.setEnabled(true);
			mainView.btnVersionInfo.setEnabled(true);
			mainView.btnChatting.setEnabled(true);
			mainView.card.show(mainView.pnlMiddle, "PanelMain");

			if (timeThread.isAlive()) {
				timeThread.interrupt();
			}

		}
		// 2. �ð� ���� �ϱ�
		else if (obj == btnAddTime) {
			try {
				pw.println("���� �ð����� ��û�� �߽��ϴ�.");

			} catch (Exception ee) {
				ee.printStackTrace();
			}
			if (count == 0) {
				new JOptionPane().showMessageDialog(null, "�ð����� Ƚ���� �� 5�� �Դϴ�.");
			}
			// �ð����� ��ư Ŭ���� �߻��� ������
			addBbnThread = new Thread() {
				public void run() {

					try {
						if (count > 5) {
							stop();
						}
						btnAddTime.setEnabled(false); // 10�ʰ� �ð������ư Ŭ�� ����
						sleep(1000 * 5); // �׽�Ʈ �� 1000*10 ���� ������
											// ��!!!!!!!!!!!!!!!!!!!!!!
						if (count < 5)
							btnAddTime.setEnabled(true);

					} catch (Exception e) {
						e.printStackTrace();
					}

				};
			};
			addBbnThread.start();

		}
		// 3.��Ʈ ������
		else if (obj == btnHeart) {
			System.out.println(UserSession.getSecondId() + "||" + UserSession.getId());
			chatController.sendHeart(UserSession.getSecondId(), UserSession.getId());
			btnHeart.setEnabled(false);
			panelRevalidate1();
			pw.println("heart");
		}
		// 4.�޽��� ������ ��ư
		else if (obj == tFieldMsg) {
			pw.println(tFieldMsg.getText());
			tFieldMsg.setText("");
		}
		else if (obj == btnSendMsg) {
			pw.println(tFieldMsg.getText());
			tFieldMsg.setText("");
		}
	}

	/** ���� �ٲ�� */
	public void panelRevalidate1() {
		int dailyHeart = chatController.dailyHeart(UserSession.getId());
		mainView.lblSendHeartCnt.setText(dailyHeart + " �� ");
	}

	/** ������ �ٲܲ� */
	public void panelRevalidate2() {
		int totalHeart = chatController.totalHeart(UserSession.getId());
		mainView.lblReceiveHeartCnt.setText(totalHeart + " �� /");
	}
}