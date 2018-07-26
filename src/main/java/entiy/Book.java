package entiy;

public class Book {
	
	private long bookId;//鍥句功ID
	private String name;//鍥句功鍚嶇О
	private int number;//棣嗚棌鏁伴噺 
	private String introd;
	public Book() {  //涓轰粈涔堣鏈変釜鏃犲弬鏋勯�犲櫒
	}
	public Book(long bookId, String name, int number) { 
		this.bookId = bookId;
		this.name = name;
		this.number = number;
	}
	public long getBookId() {
		return bookId;
	}
	public void setBookId(long bookId) {
		this.bookId = bookId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getIntrod() {
		return introd;
	}
	public void setIntrod(String introd) {
		this.introd = introd;
	}

	@Override
	public String toString() {
		return "Book [bookId=" + bookId + ", name=" + name + ", number=" + number + ", introd=" + introd + "]";
	}
}
