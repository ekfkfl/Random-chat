package ramchat.view.jframe;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.PrintWriter;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;

import ramchat.controller.impl.ServerConnetController;
import ramchat.view.jdialog.ChatWaitDialog;
import ramchat.view.jpanel.ChatView;
import ramchat.view.jpanel.MainPanelView;
import ramchat.view.jpanel.MyPageView;
import ramchat.view.jpanel.RankView;
import ramchat.view.jpanel.VersionView;
import usersession.UserSession;

/**
 * 메인화면 - 채팅화면, 랭킹화면, 마이페이지, 버전정보화면은 메인화면을 상속받는다. - 윗줄에 나열한 4개의 화면은 MainView의
 * pnlMiddle 부분에 구현한다. - reSize : false(사용자 크기조정 가능)
 */
public class MainView extends JFrame implements ActionListener {
	
	
	// 멤버변수
	JPanel pnlTop = new JPanel();
	JPanel pnlWest = new JPanel();

	JPanel pnlTopEast = new JPanel();
	public CardLayout card = new CardLayout();
	public JPanel pnlMiddle = new JPanel(card);
	JPanel pnlBottom = new JPanel();

	int ReceiveHeartCnt; // 내가 받은 하트 수
	int SendHeartCnt; // 내가 상대방에게 보낼 수 있는 하트 잔여수(하루에 최대 5개)

	/** 라벨 설정 */
	/** 토탈하트 잔여 하트 설정 여기서 기본 으로 설정 로그인을 하지 않을시 하트 인자를 어차피 가져올 필요가 없음 */
	public JLabel lblReceiveHeart = new JLabel("받은 하트 : ");
	public JLabel lblReceiveHeartCnt = new JLabel(UserSession.getTotalHeart() + " 개 " + " / ");

	public JLabel lblSendHeart = new JLabel("잔여 하트 : ");
	public JLabel lblSendHeartCnt = new JLabel(UserSession.getDailyHeart() + " 개 ");

	JLabel lblLoginState = new JLabel(UserSession.getId() + " 님 접속중");
	JButton btnLogout = new JButton("로그아웃");

	public JButton btnMain = new JButton("메인");

	public JButton btnChatting = new JButton("채팅");
	public JButton btnRanking = new JButton("랭킹");
	public JButton btnMypage = new JButton("마이페이지");
	public JButton btnVersionInfo = new JButton("버전정보");

	// ChatWaitDialog DialogChatWait = new ChatWaitDialog(this);
	ChatWaitDialog DialogChatWait;
	JPanel PanelChat;
	JPanel PanelMyPage;
	JPanel PanelRank;

	/**
	 * 생성자
	 */
	public MainView() {
		super("메인화면");

		/*
		 * pnlTop(BoarderLayout) , pnlEast(FlowLayout) -lblLoginState(id님
		 * 접속중..), btnLogout이 올라갈 패널들
		 */
		pnlTop.setLayout(new BorderLayout(0, 0));
		pnlTop.add(pnlWest, BorderLayout.WEST);
		pnlTop.add(pnlTopEast, BorderLayout.EAST);

		pnlWest.add(lblReceiveHeart);
		pnlWest.add(lblReceiveHeartCnt);
		pnlWest.add(lblSendHeart);
		pnlWest.add(lblSendHeartCnt);

		pnlTopEast.add(lblLoginState);
		pnlTopEast.add(btnLogout);

		lblLoginState.setForeground(new Color(0, 0, 0));
		btnLogout.setBackground(new Color(153, 204, 204));

		/*
		 * pnlMiddle - 채팅화면, 랭킹화면, 마이페이지, 버전정보화면이 올려질 패널
		 */
		pnlMiddle.setBackground(new Color(255, 255, 255));

		JPanel PanelMain = new MainPanelView();
		JPanel PanelVersion = new VersionView();

		pnlMiddle.add(PanelMain, "PanelMain");
		pnlMiddle.add(PanelVersion, "PanelVersion");

		// card.show(pnlMiddle, "pnl");

		/*
		 * pnlBottom(GridLayout) -btnChatting, btnRanking, btnMypage,
		 * btnVersionInfo 가 올라갈 패널
		 */
		pnlBottom.setLayout(new GridLayout(0, 5, 0, 0));
		pnlBottom.setBackground(new Color(204, 255, 153));

		btnMain.setBackground(Color.white);
		btnChatting.setBackground(Color.white);
		btnRanking.setBackground(Color.white);
		btnMypage.setBackground(Color.white);
		btnVersionInfo.setBackground(Color.white);

		pnlBottom.add(btnMain);
		pnlBottom.add(btnChatting);
		pnlBottom.add(btnRanking);
		pnlBottom.add(btnMypage);
		pnlBottom.add(btnVersionInfo);

		/*
		 * mainPage의 (전체)레이아웃: GroupLayout
		 */
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(
						groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup()
										.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
												.addComponent(pnlTop, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
												.addComponent(pnlBottom, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)
												.addGroup(groupLayout.createSequentialGroup().addGap(0).addComponent(
														pnlMiddle, GroupLayout.DEFAULT_SIZE, 785, Short.MAX_VALUE)))
										.addGap(0)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pnlTop, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlMiddle, GroupLayout.DEFAULT_SIZE, 535, Short.MAX_VALUE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pnlBottom, GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE)));
		getContentPane().setLayout(groupLayout);

		this.setSize(800, 700);
		this.setVisible(true);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		/** 리스너 등록 */
		/* 탑 버튼 */
		btnLogout.addActionListener(this);

		/* 바닥 버튼 */
		btnMain.addActionListener(this);
		btnChatting.addActionListener(this);
		btnRanking.addActionListener(this);
		btnMypage.addActionListener(this);
		btnVersionInfo.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj == btnLogout) {
			System.exit(0);
		}
		// 바텀 버튼 들
		else if (obj == btnMain) {
			System.out.println("메인버튼 누름");
			card.show(pnlMiddle, "PanelMain");
		} else if (obj == btnChatting) {
			PanelChat = new ChatView(this);
			pnlMiddle.add(PanelChat, "PanelChat");
			card.show(pnlMiddle, "PanelChat");
			btnMain.setEnabled(false);
			btnRanking.setEnabled(false);
			btnMypage.setEnabled(false);
			btnVersionInfo.setEnabled(false);
			btnChatting.setEnabled(false);
		} else if (obj == btnRanking) {
			System.out.println("랭크버튼누름");
			PanelRank = new RankView();
			pnlMiddle.add(PanelRank, "PanelRank");
			card.show(pnlMiddle, "PanelRank");
			
		} else if (obj == btnMypage) {
			System.out.println("마이페이지버튼누름");
			PanelMyPage = new MyPageView(this);
			pnlMiddle.add(PanelMyPage, "PanelMyPage");
			card.show(pnlMiddle, "PanelMyPage");

		} else if (obj == btnVersionInfo) {
			System.out.println("버전버튼누름");
			card.show(pnlMiddle, "PanelVersion");
	
		} else {
			System.out.println("예외결과...버튼액션");
		}
	}
}
