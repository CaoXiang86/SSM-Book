package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entiy.Book;



public interface BookDao {
	/*
	 * 鏍规嵁id鏌ヨ涔�
	 * 
	 */
	Book queryById(long id);
	List<Book> querySome(String name);
	List<Book> queryAll(@Param("offset") int offset,@Param("limit") int limit);
	
	/*鍑忓皯绠″瓨鏁伴噺
	 * 鐢ㄨ繑鍥炲�煎彲鍒ゆ柇褰撳墠搴撳瓨鏄惁杩樻湁涔�
	 */
	 int reduceNumber(long bookId);
}
