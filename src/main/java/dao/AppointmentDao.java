package dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import entiy.Appointment;



public interface AppointmentDao {
	//闁俺绻冮崶鍙ュ姛ID閸滃苯顒熼悽鐑瓺妫板嫮瀹虫稊锔剧潉閿涘苯鑻熼幓鎺戝弳
	int insertAppointment(@Param("bookId") long bookId, @Param("studentId") long studentId);
	 
	//闁俺绻冩稉锟芥稉顏勵劅閻㈢儺D閺屻儴顕楀鑼病妫板嫮瀹虫禍鍡楁憿娴滄稐鍔熼妴锟�
	List<Appointment> quaryAndReturn(long studentId);
//	//閺屻儴顕楅幍锟介張澶婂嚒缂佸繘顣╃痪锔垮姛缁稄绱濋弳鍌涙娑撳秴绱戦崣鎴狀吀閻炲棗鎲抽悾宀勬桨
//	List<Appointment> queryAll(@Param("offset") int offset,@Param("limit") int limit);
}
