package demo;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.AfterTransaction;
import org.springframework.test.context.transaction.BeforeTransaction;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:prjX/conf/bean_prjX.xml")
public class BaseTest {
	@Before
	public void setUp() throws Exception {
		System.out.println("测试开始");
	}

	@After
	public void tearDown() throws Exception {
		System.out.println("测试结束");
	}

	@BeforeTransaction
	public void beforeTransaction() {
		System.out.println("事务开始");
	}

	@AfterTransaction
	public void afterTransaction() {
		System.out.println("事务结束");
	}
}
