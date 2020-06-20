package nhomHang;

public class NhomHang {
	private String maNhomHang;
	private String tenNhomHang;
	public String getMaNhomHang() {
		return maNhomHang;
	}
	public void setMaNhomHang(String maNhomHang) {
		this.maNhomHang = maNhomHang;
	}
	public String getTenNhomHang() {
		return tenNhomHang;
	}
	public void setTenNhomHang(String tenNhomHang) {
		this.tenNhomHang = tenNhomHang;
	}
	public NhomHang(String maNhomHang, String tenNhomHang) {
		super();
		this.maNhomHang = maNhomHang;
		this.tenNhomHang = tenNhomHang;
	}
	public NhomHang() {
		super();
	}
	@Override
	public String toString() {
		return "NhomHang [maNhomHang=" + maNhomHang + ", tenNhomHang=" + tenNhomHang + "]";
	}
	
}
