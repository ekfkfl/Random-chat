package ramchat.model.service.impl;

import java.sql.SQLException;

import mail.MailService;
import ramchat.model.dao.SearchDao;
import ramchat.model.dao.impl.SearchDaoImpl;
import ramchat.model.dto.UserInfoDTO;
import ramchat.model.service.SearchService;

public class SearchServiceImpl implements SearchService {
	private SearchDao searchDao = new SearchDaoImpl();
	private MailService mailService;
	@Override
	public UserInfoDTO searchId(String name, String email) throws SQLException {
		UserInfoDTO userInfoDTO = searchDao.searchId(name, email);
		
		if(userInfoDTO==null && !name.equals("") && !email.equals("") ){
			throw new SQLException(" �˻��� ����� �����ϴ�. \n ID Ȥ��, E-Mail�� Ȯ�����ּ���.");
		} else if (name.equals("")) {
			throw new SQLException("�̸��� �Է����ּ���.");
		} else if (email.equals("")) {
			throw new SQLException("E-Mail�� �Է����ּ���.");
		}  else {
			//��ư�� ������ Controller -> Service -> DAO -> ���ϰ��� ���ؼ�, �Ʒ��� mailService�޼��带 ȣ��
			mailService = new MailService(email, "���̵�ã�� ����Դϴ�", userInfoDTO.getId());
			return userInfoDTO;
		}
	}

	@Override
	public UserInfoDTO searchPw(String id, String name, String email) throws SQLException {
		UserInfoDTO userInfoDTO = searchDao.searchPw(id, name, email);
		
		if (userInfoDTO == null && !id.equals("")&& !name.equals("") && !email.equals("") ) {
			throw new SQLException(" �˻��� ����� �����ϴ�. \n ID Ȥ��, E-Mail�� Ȯ�����ּ���.");
		} else if (id.equals("")) {
			throw new SQLException("ID�� �Է����ּ���.");
		} else if (name.equals("")) {
			throw new SQLException("�̸��� �Է����ּ���.");
		} else if (email.equals("")) {
			throw new SQLException("E-Mail�� �Է����ּ���.");
		} else {
			mailService = new MailService(email, "�н�����ã�� ����Դϴ�", userInfoDTO.getPw());
			return userInfoDTO;
		}
	}
}
