/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-07
 Time: 오후 9:58
 desc: MYSQL Connection Test Unit

 Created by altjd815@gmail.com 
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/

import java.sql.Connection;
import javax.inject.Inject;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:web/WEB-INF/spring/applicationContext.xml" })
public class MySQLConnectionTest {

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private DataSource ds;

    @Test
    public void testConnection() throws Exception {

        try (Connection con = ds.getConnection()) {

            log.info("\n >>>>>>>>>> Connection 출력 : " + con + "\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
