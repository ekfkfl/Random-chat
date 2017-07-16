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
			throw new SQLException(" 검색된 결과가 없습니다. \n ID 혹은, E-Mail을 확인해주세요.");
		} else if (name.equals("")) {
			throw new SQLException("이름을 입력해주세요.");
		} else if (email.equals("")) {
			throw new SQLException("E-Mail을 입력해주세요.");
		}  else {
			//버튼이 눌리면 Controller -> Service -> DAO -> 리턴값을 통해서, 아래에 mailService메서드를 호출
			mailService = new MailService(email, "아이디찾기 결과입니다", userInfoDTO.getId());
			return userInfoDTO;
		}
	}

	@Override
	public UserInfoDTO searchPw(String id, String name, String email) throws SQLException {
		UserInfoDTO userInfoDTO = searchDao.searchPw(id, name, email);
		
		if (userInfoDTO == null && !id.equals("")&& !name.equals("") && !email.equals("") ) {
			throw new SQLException(" 검색된 결과가 없습니다. \n ID 혹은, E-Mail을 확인해주세요.");
		} else if (id.equals("")) {
			throw new SQLException("ID를 입력해주세요.");
		} else if (name.equals("")) {
			throw new SQLException("이름을 입력해주세요.");
		} else if (email.equals("")) {
			throw new SQLException("E-Mail을 입력해주세요.");
		} else {
			mailService = new MailService(email, "패스워드찾기 결과입니다", userInfoDTO.getPw());
			return userInfoDTO;
		}
	}
}
