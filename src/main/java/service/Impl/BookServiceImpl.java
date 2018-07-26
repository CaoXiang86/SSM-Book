package service.Impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dao.AppointmentDao;
import dao.BookDao;
import dao.StudentDao;
import dto.AppointExecution;
import entiy.Appointment;
import entiy.Book;
import entiy.Student;
import enums.AppointStateEnum;
import exception.AppointException;
import exception.NoNumberException;
import exception.RepeatAppointException;
import service.BookService;
 
@Service
public class BookServiceImpl implements BookService{
	private Logger logger=LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BookDao bookDao;
	@Autowired
	private AppointmentDao appointmentDao;
	@Autowired
	private StudentDao studentDao; 
	public Book getById(long bookId) { 
		return bookDao.queryById(bookId);
	}  
	public List<Book> getList() { 
		return bookDao.queryAll(0, 1000);
	} 
	public Student validateStu(Long studentId,Long password){
		return studentDao.quaryStudent(studentId, password);
	}
	public List<Book> getSomeList(String name) {
		 
		return bookDao.querySome(name);
	} 
	public List<Appointment> getAppointByStu(long studentId) {//DOTO
		return appointmentDao.quaryAndReturn(studentId);
		 
	}
	@Transactional
	public AppointExecution appoint(long bookId, long studentId) {//鍦―ao鐨勫熀纭�涓婄粍缁囬�昏緫锛屽舰鎴愪笌web鎴愪氦浜掔敤鐨勬柟娉�
		try{													  //杩斿洖鎴愬姛棰勭害鐨勭被鍨嬨��		
			int update=bookDao.reduceNumber(bookId);//鍑忓簱瀛�
			if(update<=0){//宸茬粡鏃犲簱瀛橈紒
				throw new NoNumberException("no number");
			}else{
				//鎵ц棰勭害鎿嶄綔
				int insert=appointmentDao.insertAppointment(bookId, studentId);
				if(insert<=0){//閲嶅棰勭害
					throw new RepeatAppointException("repeat appoint");
				}else{//棰勭害鎴愬姛
					return new AppointExecution(bookId,AppointStateEnum.SUCCESS);
				}
				
			}
		} catch (NoNumberException e1) {
			throw e1;
		} catch (RepeatAppointException e2) {
			throw e2;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			// 鎵�鏈夌紪璇戞湡寮傚父杞崲涓鸿繍琛屾湡寮傚父
			throw new AppointException("appoint inner error:" + e.getMessage());
		}
	}

 
}
