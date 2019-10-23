/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 8:39
 desc: User service 오버라이드 메소드 재정의

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/
package com.board.service;
import com.board.dao.UserDAO;
import com.board.dto.LoginDTO;
import com.board.utils.TempKey;
import com.board.domain.UserVO;
import com.board.utils.MailUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDAO uDAO;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public UserVO readByUid(String uid) throws Exception {
        return uDAO.readByUid(uid);
    }

    @Override
    public UserVO readyByEmail(String email) throws Exception {
        return uDAO.readyByEmail(email);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor={Exception.class})
    public void createByUser(UserVO userVO) throws Exception {
        uDAO.createByUser(userVO);

        String authKey = new TempKey().getKey(8, false);
        userVO.setAuthkey(authKey);
        uDAO.updateAuthKey(userVO);

        // mail 작성 관련기능
        MailUtils sendMail = new MailUtils(mailSender);

        sendMail.setSubject("[Jihye's CRUD] 회원가입 이메일 인증");
        sendMail.setText(new StringBuffer().append("<h1>[이메일 인증]</h1>")
                .append("<p>아래 링크를 클릭하시면 이메일 인증이 완료됩니다.</p>")
                .append("<a href='http://localhost:8090/user/joinConfirm?uid=")
                .append(userVO.getUid())
                .append("&email=")
                .append(userVO.getEmail())
                .append("&authkey=")
                .append(authKey)
                .append("' target='_blenk'>이메일 인증 확인</a>")
                .toString());
        sendMail.setFrom("altjd815@gmail.com ", "Lee Ji-Hye");
        sendMail.setTo(userVO.getEmail());
        sendMail.send();

    }

    @Override
    public void updateAuthStatus(UserVO uVO) throws Exception {
        uDAO.updateAuthStatus(uVO);
    }

    @Override
    public UserVO login(LoginDTO lDTO) throws Exception {
        return uDAO.login(lDTO);
    }
    @Override
    public UserVO readForCheckSession(String cookieValue) throws Exception {
        return uDAO.readForCheckSession(cookieValue);
    }
    @Override
    public void updateForCookie(String uid, String sessionKey, Date sessionlimit) throws Exception {
        uDAO.updateForCookie(uid, sessionKey, sessionlimit);

    }


}
