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

import utils.Page;

import com.chu.dao.domain.AuthUser;
import com.chu.service.AuthUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring.xml")
@TransactionConfiguration(transactionManager = "chuTransactionManager", defaultRollback = false)
@Transactional
public class AuthUserServiceTest {

	@Autowired
	private AuthUserService authUserService;
	
	@Test
	public final void testFindByMtime() {
		Page<AuthUser> page = new Page<AuthUser>(5);
		page.pageNo(2);
		page = authUserService.findByMtime(null, page);
		List<AuthUser> list = page.getResult();
		Assert.assertTrue(list != null);
	}
	
}
