package chu;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.chu.dao.domain.User;
import com.chu.dao.domain.UserExample;
import com.chu.dao.domain.UserExample.Criteria;
import com.chu.dao.mapper.UserMapper;
import com.chu.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
@TransactionConfiguration(transactionManager = "chuTransactionManager", defaultRollback = false)
@Transactional
public class userTest {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserMapper userMapper;
	
	
	@Test
	public final void testSave() {
		int r = userService.save(7, "chu");
		Assert.assertTrue(r == 1);
	}
	
	@Test
	public final void testMapper() {
		UserExample c = new UserExample();
		UserExample.Criteria cr = c.createCriteria();
		cr.andIdEqualTo(1);
		Criteria ca = c.or();
		ca.andIdEqualTo(2);
		cr.andNameEqualTo("123");
		List<User> user = userMapper.selectByExample(c);
		
		
	}
}
