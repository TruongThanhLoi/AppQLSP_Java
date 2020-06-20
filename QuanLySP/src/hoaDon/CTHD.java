package hoaDon;

import java.sql.Date;

import loaiSanPham.LoaiSP;
import sanPham.SanPham;

public class CTHD {
	private String maHD;
	private LoaiSP lsp;
	private int soLuong;
	private double tongTien;
	public String getMaHD() {
		return maHD;
	}
	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}
	
	public double getTongTien() {
		return tongTien;
	}
	
	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}
	public LoaiSP getLsp() {
		return lsp;
	}
	public void setLsp(LoaiSP lsp) {
		this.lsp = lsp;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	
	public CTHD(String maHD, LoaiSP lsp, int soLuong, double tongTien) {
		super();
		this.maHD = maHD;
		this.lsp = lsp;
		this.soLuong = soLuong;
		this.tongTien = tongTien;
	}
	public CTHD() {
		super();
	}
	@Override
	public String toString() {
		return "CTHD [maHD=" + maHD + ", lsp=" + lsp + ", soLuong=" + soLuong + ", tongTien=" + tongTien + "]";
	}
	
}
