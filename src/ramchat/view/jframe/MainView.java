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
 * ����ȭ�� - ä��ȭ��, ��ŷȭ��, ����������, ��������ȭ���� ����ȭ���� ��ӹ޴´�. - ���ٿ� ������ 4���� ȭ���� MainView��
 * pnlMiddle �κп� �����Ѵ�. - reSize : false(����� ũ������ ����)
 */
public class MainView extends JFrame implements ActionListener {
	
	
	// �������
	JPanel pnlTop = new JPanel();
	JPanel pnlWest = new JPanel();

	JPanel pnlTopEast = new JPanel();
	public CardLayout card = new CardLayout();
	public JPanel pnlMiddle = new JPanel(card);
	JPanel pnlBottom = new JPanel();

	int ReceiveHeartCnt; // ���� ���� ��Ʈ ��
	int SendHeartCnt; // ���� ���濡�� ���� �� �ִ� ��Ʈ �ܿ���(�Ϸ翡 �ִ� 5��)

	/** �� ���� */
	/** ��Ż��Ʈ �ܿ� ��Ʈ ���� ���⼭ �⺻ ���� ���� �α����� ���� ������ ��Ʈ ���ڸ� ������ ������ �ʿ䰡 ���� */
	public JLabel lblReceiveHeart = new JLabel("���� ��Ʈ : ");
	public JLabel lblReceiveHeartCnt = new JLabel(UserSession.getTotalHeart() + " �� " + " / ");

	public JLabel lblSendHeart = new JLabel("�ܿ� ��Ʈ : ");
	public JLabel lblSendHeartCnt = new JLabel(UserSession.getDailyHeart() + " �� ");

	JLabel lblLoginState = new JLabel(UserSession.getId() + " �� ������");
	JButton btnLogout = new JButton("�α׾ƿ�");

	public JButton btnMain = new JButton("����");

	public JButton btnChatting = new JButton("ä��");
	public JButton btnRanking = new JButton("��ŷ");
	public JButton btnMypage = new JButton("����������");
	public JButton btnVersionInfo = new JButton("��������");

	// ChatWaitDialog DialogChatWait = new ChatWaitDialog(this);
	ChatWaitDialog DialogChatWait;
	JPanel PanelChat;
	JPanel PanelMyPage;
	JPanel PanelRank;

	/**
	 * ������
	 */
	public MainView() {
		super("����ȭ��");

		/*
		 * pnlTop(BoarderLayout) , pnlEast(FlowLayout) -lblLoginState(id��
		 * ������..), btnLogout�� �ö� �гε�
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
		 * pnlMiddle - ä��ȭ��, ��ŷȭ��, ����������, ��������ȭ���� �÷��� �г�
		 */
		pnlMiddle.setBackground(new Color(255, 255, 255));

		JPanel PanelMain = new MainPanelView();
		JPanel PanelVersion = new VersionView();

		pnlMiddle.add(PanelMain, "PanelMain");
		pnlMiddle.add(PanelVersion, "PanelVersion");

		// card.show(pnlMiddle, "pnl");

		/*
		 * pnlBottom(GridLayout) -btnChatting, btnRanking, btnMypage,
		 * btnVersionInfo �� �ö� �г�
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
		 * mainPage�� (��ü)���̾ƿ�: GroupLayout
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

		/** ������ ��� */
		/* ž ��ư */
		btnLogout.addActionListener(this);

		/* �ٴ� ��ư */
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
		// ���� ��ư ��
		else if (obj == btnMain) {
			System.out.println("���ι�ư ����");
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
			System.out.println("��ũ��ư����");
			PanelRank = new RankView();
			pnlMiddle.add(PanelRank, "PanelRank");
			card.show(pnlMiddle, "PanelRank");
			
		} else if (obj == btnMypage) {
			System.out.println("������������ư����");
			PanelMyPage = new MyPageView(this);
			pnlMiddle.add(PanelMyPage, "PanelMyPage");
			card.show(pnlMiddle, "PanelMyPage");

		} else if (obj == btnVersionInfo) {
			System.out.println("������ư����");
			card.show(pnlMiddle, "PanelVersion");
	
		} else {
			System.out.println("���ܰ��...��ư�׼�");
		}
	}
}
