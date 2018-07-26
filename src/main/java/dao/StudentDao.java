package dao;

import org.apache.ibatis.annotations.Param;

import entiy.Student;

public interface StudentDao {
	/**
	 * 鍚戞暟鎹簱楠岃瘉杈撳叆鐨勫瘑鐮佹槸鍚︽纭�
	 */
	Student quaryStudent(@Param("studentId") long studentId, @Param("password") long password);
}
