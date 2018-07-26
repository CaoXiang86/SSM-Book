package dto;

import enums.AppointStateEnum;

public class AppointExecution {

	// 鍥句功ID
	private long bookId;

	// 绉掓潃棰勭害缁撴灉鐘舵��
	private int state;

	// 鐘舵�佹爣璇�
	private String stateInfo;  

	public AppointExecution() {
	}

	// 棰勭害澶辫触鐨勬瀯閫犲櫒
	public AppointExecution(long bookId, AppointStateEnum stateEnum) {
		this.bookId = bookId;
		this.state = stateEnum.getState();
		this.stateInfo = stateEnum.getStateInfo();
	}

	//set get 鏂规硶锛�
	public long getBookId() {
		return bookId;
	}

	public void setBookId(long bookId) {
		this.bookId = bookId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}
	
	@Override
	public String toString() {
		return "AppointExecution [bookId=" + bookId + ", state=" + state + ", stateInfo=" + stateInfo+"]";
	}
	
}
