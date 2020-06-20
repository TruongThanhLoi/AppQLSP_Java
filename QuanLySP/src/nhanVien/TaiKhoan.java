package nhanVien;

public class TaiKhoan {
	private String maNV;
	private String userName;
	private String passWord;
	public String getMaNV() {
		return maNV;
	}
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public TaiKhoan(String maNV, String userName, String passWord) {
		super();
		this.maNV = maNV;
		this.userName = userName;
		this.passWord = passWord;
	}
	public TaiKhoan() {
		super();
	}
	@Override
	public String toString() {
		return "TaiKhoan [maNV=" + maNV + ", userName=" + userName + ", passWord=" + passWord + "]";
	}
	
}
