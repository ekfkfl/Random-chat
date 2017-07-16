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
 * 채팅화면
 */
public class ChatView extends JPanel implements ActionListener {
	public ChatWaitDialog chatWaitDialog = null;
	MainView mainView = null;
	ChatController chatController = new ChatControllerImpl();

	// 채팅 VIEW, 채팅입력
	JTextArea tAreaBoard = new JTextArea();
	JScrollPane jsp = new JScrollPane(tAreaBoard);
	JTextField tFieldMsg = new JTextField();
	JButton btnSendMsg = new JButton("보내기");

	// User 정보 호출
	JLabel lblUserA = new JLabel("상대방 사진");
	ImagePanel2 pnlUserA = new ImagePanel2();
	JLabel lblUserB = new JLabel("나의 사진");
	ImagePanel2 pnlUserB = new ImagePanel2();

	// 기능 Button
	JButton btnExit = new JButton("나가기");
	JButton btnAddTime = new JButton("시간연장");
	JButton btnHeart = new JButton("좋아요♥");

	int limitTime2;
	JLabel lblNewLabel = new JLabel(); // 채팅남은시간 표시하는 라벨

	int count = 0; // 패널의 위치를 식별할 count변수

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
	 * 생성
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

		// 이미지대신 임시로 이미지화면구분...line86~87
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
		/** 리스너 등록 */
		/*
		 * JButton btnExit = new JButton("나가기"); JButton btnAddTime = new
		 * JButton("\uC2DC\uAC04\uC5F0\uC7A5"); JButton btnHeart = new
		 * JButton("\uC88B\uC544\uC694\u2665");
		 */
		btnExit.addActionListener(this);
		btnAddTime.addActionListener(this);
		btnHeart.addActionListener(this);
		tFieldMsg.addActionListener(this);
		btnSendMsg.addActionListener(this);

		// 받는 스레드
		new Thread() {
			public void run() {
				try {
					String data = "";
					while ((data = br.readLine()) != null) {
						if (data.equals("시간이 추가되었습니다.")) {

							if (count < 5) {
								count++;
								limitTime2 += 180; // 3분 추가
								pnlUserA.repaint();
								pnlUserB.repaint();
							} else if (count >= 5)
								btnAddTime.setEnabled(false);
						}
						if (!data.equals("heartup"))
							tAreaBoard.append(data + "\n");
						else {
							JOptionPane.showMessageDialog(null, UserSession.getSecondId() + "님 에게 하트를 받았습니다.");
							panelRevalidate2();
						}

						// 스크롤바의 위치를 이동
						int length = tAreaBoard.getText().length();
						tAreaBoard.setCaretPosition(length);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();

		// 스레드시작
		timeThread.start();

	}// 생성자 끝

	// 채팅 남은시간 쓰레드 생성
	Thread timeThread = new Thread() {
		public void run() {

			try {
				for (limitTime2 = 300; limitTime2 >= 0; limitTime2--) { // limitTime2:
					// 초기값(5분)으로
					// 시작
					int min2 = limitTime2 / 60;
					int sec2 = limitTime2 % 60;

					String min = String.format("%02d", min2);
					String sec = String.format("%02d", sec2);

					// 채팅창에 보여질 남은시간
					String limitTime = min + ":" + sec;

					lblNewLabel.setText("남은시간 - " + limitTime);

					Thread.sleep(1000);

					// time이 0이면 채팅방을 나가도록
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
			// time이 0이면 채팅방을 나가도록

		};
	};

	Thread addBbnThread = null;

	// 리스너
	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		// 1. 채팅 나가기
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
		// 2. 시간 연장 하기
		else if (obj == btnAddTime) {
			try {
				pw.println("님이 시간연장 요청을 했습니다.");

			} catch (Exception ee) {
				ee.printStackTrace();
			}
			if (count == 0) {
				new JOptionPane().showMessageDialog(null, "시간연장 횟수는 총 5번 입니다.");
			}
			// 시간연장 버튼 클릭시 발생할 스레드
			addBbnThread = new Thread() {
				public void run() {

					try {
						if (count > 5) {
							stop();
						}
						btnAddTime.setEnabled(false); // 10초간 시간연장버튼 클릭 금지
						sleep(1000 * 5); // 테스트 후 1000*10 으로 변경할
											// 것!!!!!!!!!!!!!!!!!!!!!!
						if (count < 5)
							btnAddTime.setEnabled(true);

					} catch (Exception e) {
						e.printStackTrace();
					}

				};
			};
			addBbnThread.start();

		}
		// 3.하트 보내기
		else if (obj == btnHeart) {
			System.out.println(UserSession.getSecondId() + "||" + UserSession.getId());
			chatController.sendHeart(UserSession.getSecondId(), UserSession.getId());
			btnHeart.setEnabled(false);
			panelRevalidate1();
			pw.println("heart");
		}
		// 4.메시지 보내기 버튼
		else if (obj == tFieldMsg) {
			pw.println(tFieldMsg.getText());
			tFieldMsg.setText("");
		}
		else if (obj == btnSendMsg) {
			pw.println(tFieldMsg.getText());
			tFieldMsg.setText("");
		}
	}

	/** 내가 바뀔거 */
	public void panelRevalidate1() {
		int dailyHeart = chatController.dailyHeart(UserSession.getId());
		mainView.lblSendHeartCnt.setText(dailyHeart + " 개 ");
	}

	/** 상대방이 바꿀꺼 */
	public void panelRevalidate2() {
		int totalHeart = chatController.totalHeart(UserSession.getId());
		mainView.lblReceiveHeartCnt.setText(totalHeart + " 개 /");
	}
}