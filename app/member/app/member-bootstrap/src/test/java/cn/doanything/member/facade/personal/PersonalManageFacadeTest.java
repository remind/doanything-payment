package cn.doanything.member.facade.personal;

import cn.doanything.member.facade.personal.dto.PersonalAddRequest;
import cn.doanything.member.types.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author wxj
 * 2023/12/31
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class
PersonalManageFacadeTest {

    @Autowired
    private PersonalManageFacade personalManageFacade;

    @Test
    public void testCreate() {
        PersonalAddRequest request = new PersonalAddRequest();
        request.setMemberName("张三");
        request.setPhone("12345678901");
        request.setGender(Gender.MALE);
        personalManageFacade.create(request);
    }
}
