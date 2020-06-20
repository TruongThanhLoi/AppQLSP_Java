package hoaDon;

import java.sql.Date;
import java.util.Arrays;

import nhanVien.NhanVien;

public class HoaDon {
	private Date ngayLapHD;
	private NhanVien nhanVien;
	private String maHD;
	private CTHD cthd;
	private double tongTien;

	public Date getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Date ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	
	public CTHD getCthd() {
		return cthd;
	}

	public void setCthdn(CTHD cthd) {
		this.cthd = cthd;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	
	public HoaDon(Date ngayLapHD, NhanVien nhanVien, String maHD) {
		super();
		this.ngayLapHD = ngayLapHD;
		this.nhanVien = nhanVien;
		this.maHD = maHD;
	}

	public HoaDon() {
		super();
	}

	@Override
	public String toString() {
		return "HoaDon [ngayLapHD=" + ngayLapHD + ", nhanVien=" + nhanVien + ", maHD=" + maHD + ", cthdn=" + cthd
				+ ", tongTien=" + tongTien + "]";
	}
	
}
