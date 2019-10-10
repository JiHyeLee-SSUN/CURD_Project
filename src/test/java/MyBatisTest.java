/***
 Created by IntelliJ IDEA.
 Project : curd
 User: leejihye
 Date: 2019-10-10
 Time: 오후 8:05
 desc:MyBatis  Test Unit

 Created by altjd815@gmail.com
 Blog : https://2-jissun.tistory.com/
 Github : https://github.com/JiHyeLee-SSUN/Project_Togather.git
 ***/

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.inject.Inject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"file:web/WEB-INF/spring/applicationContext.xml"})
public class MyBatisTest
{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Inject
    private SqlSessionFactory sqlFactory;

    @Test
    public void testFactory(){
        log.info("\n >>>>>>>>>> sqlFactory 출력 : "+sqlFactory);
    }

    @Test
    public void testSession() throws Exception{

        try(SqlSession session = sqlFactory.openSession()){

            log.info(" >>>>>>>>>> session 출력 : "+session+"\n");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}