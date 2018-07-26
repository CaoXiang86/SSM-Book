package web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.AppointExecution;
import dto.Result;
import entiy.Appointment;
import entiy.Book;
import entiy.Student;
import enums.AppointStateEnum;
import exception.NoNumberException;
import exception.RepeatAppointException;
import service.BookService;

 

@Controller
@RequestMapping("/books")
public class BookController {
	@Autowired
	private BookService bookService;
	//鑾峰彇鍥句功鍒楄〃
	@RequestMapping(value="/list",method = RequestMethod.GET)
	private String List(Model model){
		List<Book> list = bookService.getList();
		model.addAttribute("list", list);
		
		return "list";
	}
	//鎼滅储鏄惁鏈夋煇鍥句功
	@RequestMapping(value="/search",method = RequestMethod.POST)
	private void  search(HttpServletRequest req,HttpServletResponse resp) 
								throws ServletException, IOException{
		//鎺ユ敹椤甸潰鐨勫��
		String name=req.getParameter("name");
		name=name.trim();
		//鍚戦〉闈紶鍊�
		req.setAttribute("name", name);
		req.setAttribute("list", bookService.getSomeList(name)); 
		req.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(req, resp); 
	}
	//鏌ョ湅鏌愬浘涔︾殑璇︾粏鎯呭喌
	@RequestMapping(value = "/{bookId}/detail", method = RequestMethod.GET)
	private String detail(@PathVariable("bookId") Long bookId, Model model){
		if(bookId==null){
			return "redict:/book/list";
		}
		Book book=bookService.getById(bookId);
		if(book==null){
			return "forward:/book/list"; 
		}
		model.addAttribute("book",book);
		System.out.println(book);
		return "detail";
	}
	//楠岃瘉杈撳叆鐨勭敤鎴峰悕銆佸瘑鐮佹槸鍚︽纭�
	@RequestMapping(value="/verify", method = RequestMethod.POST, produces = {
													"application/json; charset=utf-8" })
	@ResponseBody
	private HashMap<String, String> validate(Long studentId,Long password){   //(HttpServletRequest req,HttpServletResponse resp){
		HashMap<String, String> resultMap=new HashMap<>();
		Student student =null;  
		System.out.println("楠岃瘉鍑芥暟"); 
		student =bookService.validateStu(studentId,password);
		
		System.out.println("杈撳叆鐨勫鍙枫�佸瘑鐮侊細"+studentId+":"+password);
		System.out.println("鏌ヨ鍒扮殑锛�"+student.getStudentId()+":"+student.getPassword());
		
		if(student!=null){
			System.out.println("SUCCESS");
			resultMap.put("result", "SUCCESS");
		}else{ 
			resultMap.put("result", "FAILED");
		}
		
		return resultMap;
	}
	//鎵ц棰勭害鐨勯�昏緫
	@RequestMapping(value = "/{bookId}/appoint", method = RequestMethod.POST, produces = {
	"application/json; charset=utf-8" })
	@ResponseBody
	private Result<AppointExecution> execute(@PathVariable("bookId") Long bookId,@RequestParam("studentId") Long studentId){
		Result<AppointExecution> result;
		AppointExecution execution=null;
		
		try{//鎵嬪姩try catch,鍦ㄨ皟鐢╝ppoint鏂规硶鏃跺彲鑳芥姤閿�
			execution=bookService.appoint(bookId, studentId);
			result=new Result<AppointExecution>(true,execution); 
				return result; 
				
		} catch(NoNumberException e1) {
			execution=new AppointExecution(bookId,AppointStateEnum.NO_NUMBER);
			result=new Result<AppointExecution>(true,execution);
				return result;
		}catch(RepeatAppointException e2){
			execution=new AppointExecution(bookId,AppointStateEnum.REPEAT_APPOINT);
			result=new Result<AppointExecution>(true,execution);
				return result;
		}catch (Exception e){
			execution=new AppointExecution(bookId,AppointStateEnum.INNER_ERROR); 
			result=new Result<AppointExecution>(true,execution);
				return result;
		} 
	}
	@RequestMapping(value ="/appoint")
	private String appointBooks(@RequestParam("studentId") long studentId,Model model){
		
		List<Appointment> appointList=new ArrayList<Appointment>();
		appointList=bookService.getAppointByStu(studentId);
		model.addAttribute("appointList", appointList);
		 
		return "appointBookList";
	}
	
}
