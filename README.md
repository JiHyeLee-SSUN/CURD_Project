# CURD_Project
<br>

<b>< Used Language, Library ></b>

<ul>
  <li>Used Language</li>
    <ul>
      <li>Java</li>
      <li>JSP</li>
      <li>HTML, CSS</li>
      <li>Java Script</li>
    </ul>
</ul>
<ul>
  <li>Used Open Source Library</li>
    <ul>
      <li>Bootstrap(HTML, CSS)</li>
      <li>jQuery(JavaScript)</li>
      <li>emailJS(JavaScript)</li>
      <li>Jackson(JSON)</li>
    </ul>
</ul>

<hr>

<b>< Tool Kit ></b>

<ul>
    <li>Intellij IDEA 2019.02, JDK 1.8.0</li>
    <li>Spring 4.3.18.RELEASE</li>
    <li>Apache-Tomcat 8.5(WAS)</li>
    <li>Mysql(DB)</li>
    <li>Github</li>
</ul>

<hr>

<b>< Mysql DB 구축 ></b>

<p><b> - database</b><p>

<p><b> - tbl_board</b><p>

CREATE TABLE tbl_board (
  bno int(11) NOT NULL AUTO_INCREMENT,
  title varchar(50) DEFAULT NULL,
  content varchar(50) DEFAULT NULL,
  writer varchar(50) DEFAULT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
  viewcnt int(11) DEFAULT 0,
  replycnt int(11) DEFAULT 0,
  likecnt int(11) DEFAULT 0,
    PRIMARY KEY (bno)
);


<p><b> - tbl_reply</b><p>
  
CREATE TABLE tbl_reply (
  rno int(11) NOT NULL AUTO_INCREMENT,
  bno int(11) NOT NULL,
  replytext varchar(1000) DEFAULT NULL,
  replywriter varchar(50) DEFAULT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
  updatedate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (rno)
);


<p><b> - tbl_attach</b><p>
  
CREATE TABLE tbl_attach (
  fullName varchar(150) NOT NULL,
  bno int(11) NOT NULL,
  regdate timestamp NOT NULL DEFAULT current_timestamp(),
    PRIMARY KEY (fullName)
);

<p><b> - tbl_user</b><p>
  
 CREATE TABLE tbl_user ( 
  uid VARCHAR(50) NOT NULL
  , pw VARCHAR(50) DEFAULT NULL
  , NAME VARCHAR(100) DEFAULT NULL
  , gender VARCHAR(10) DEFAULT NULL
  , sessionkey VARCHAR(50) NOT NULL DEFAULT 'none'
  , sessionlimit TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP() ON UPDATE CURRENT_TIMESTAMP()
  ,  authkey VARCHAR(50) DEFAULT NULL
  , authstatus INT(1) NOT NULL DEFAULT 0
  , thumbnail VARCHAR(150) DEFAULT NULL
  , email VARCHAR(150) NOT NULL
  , joindate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP()
  , PRIMARY KEY (uid) 
);


<p><b> - tbl_like</b><p>
  
CREATE TABLE tbl_like (
  lno int(11) NOT NULL AUTO_INCREMENT,
  bno int(11) DEFAULT NULL,
  uid varchar(50) DEFAULT NULL,
  PRIMARY KEY (lno)
)

<p><b> - foreign key</b></p>

alter table tbl_reply add foreign key(rno) references tbl_board(bno);

alter table tbl_reply add foreign key(bno) references tbl_board(bno);

alter table tbl_like add foreign key(bno) references tbl_board(bno);

alter table tbl_like add foreign key(uid) references tbl_user(uid);

<hr>

<b>< ERD ></b>

![Board Erd](https://user-images.githubusercontent.com/53209589/66992616-16227c00-f105-11e9-90a1-446331cac93c.PNG)

<hr>

<b>< URL ></b>
 <br> http://52.78.131.105:8080/
  <br>
  - 현재 AWS의 google smtp차단으로 인해 회원가입 인증메일이 보내지지 않고 있습니다.
  



<hr>

<b>< Function ></b>

<ol>
  <b><li>회원가입</li></b>
  <ul>
    <ol>
      <b><li>기본 기능</li></b>
        <ul>
          <li>
            Ajax를 이용해 입력된 uid, email 값을 파라미터로 하여 컨트롤러에 전달 -> 
            tbl_user 테이블을 조회하여 아이디, 이메일 중복 결과를 text 데이터 타입으로 리턴
          </li>
          <li>비밀번호 6자리 이상 + 비밀번호 재입력 일치 시 -&raquo; 비밀번호 체크 표시 활성화</li>
          <li>---> jQeury .keyup() 사용, 매 입력시 최종 조건 모두 충족해야만 submit 버튼 활성화</li>
        </ul>
      <b><li>이메일 인증</li></b>
        <ul>
          <li>임시 회원가입 이후, 인증용 메일 수신(트랜잭션처리로 메일발신 성공시 회원가입 commit ,임의의 authkey정보 포함) <br>
            -> 이메일 인증(사용자 권한 활성화)</li>
        </ul>
    </ol>   
  </ul>
 
  <b><li>로그인</li></b>
  <ul>
    <ol>
      <b><li>기본 기능</li></b>
        <ul>
          <li>Interceptor를 이용한 세션, 쿠키 관리</li>
          <li>입력된 uid, pw 값을 파라미터로 하여 컨트롤러에 전달, 해당 사용자가 존재하는지 확인</li>
          <li>쿠키를 이용한 자동 로그인</li>
        </ul>
  </ul>
  
  <b><li>CRUD 게시판(구현 예정....)</li></b>
  <ul>
    <ol>
      <b><li>기본 기능</li></b>
        <ul>
          <li>글 쓰기, 글 수정, 글 삭제, 글 보기</li>
          <li>
            글 보기의 경우 Ajax를 이용해 페이지 이동없이 답글 달기 가능, 
            그 후 페이지 이동없이 게시글 정보(댓글 목록, 댓글수 등) 최신화
          </li>
          <li>Bootstrap Modal을 이용, 페이지 이동없이 댓글 관리 가능</li>
        </ul>
      <b><li>좋아요</li></b>
        <ul>
          <li>사용자별 좋아요 관리(시각적으로 표현)</li>
        </ul>
      <b><li>검색기능</li></b>
        <ul>
          <li>검색 조건 + 검색어 -> 조건 유지한 상태로 페이징 처리</li>
        </ul>
      <b><li>파일 첨부</li></b>
      <ul>
        <li>이미지, 일반 파일을 구분하여 처리</li>
        <li>각 파일마다 표시된 'x' 클릭시, 위에서 실제 경로에 저장된 파일 삭제</li>
        <li>사용자 썸네일 이미지 구현</li>
      </ul>
    </ol>
  </ul>
  
  <b><li>About (구현 예정....)</li></b>
  <ul>
    <ol>
      <b><li>관리자에게 메일 보내기(emailJS)</li></b>
      <ul>
          <li>사용자의 편의를 위한 간단한 메세지 보내기</li>
      </ul>
    </ol>
  </ul>
  
  <b><li>예외처리 (구현 예정....)</li></b>
  <ul>
    <ol>
      <b><li>Java 클래스, 에러 코드별 예외처리</li></b>
    </ol>
  </ul>
</ol>
