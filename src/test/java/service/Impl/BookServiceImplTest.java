package service.Impl;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import entiy.Book;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:/spring/spring-dao.xml","classpath:/spring/spring-service.xml","classpath:/spring/springweb.xml"})
@TransactionConfiguration(transactionManager="transactionManager")
@Transactional
public class BookServiceImplTest {
	@Resource
	BookServiceImpl bookService;
	
	@Test
	public void test() {
		List<Book> list = bookService.getList();
		for (Book book : list) {
			System.out.println(book);
		}
	}

}
