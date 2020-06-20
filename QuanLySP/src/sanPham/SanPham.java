package sanPham;

import java.sql.Date;
import java.time.LocalDate;

import hoaDon.HoaDon;
import loaiSanPham.LoaiSP;
import nhaCungCap.NhaCungCap;

public class SanPham {
	private String maSP;
	private LoaiSP loai;
	private double donGiaNhap;
	private boolean trangThaiLoi;
	private Date ngayNhap;
	private Date hanSuDung;
	private Date hanBaoHanh;
	public String getMaSP() {
		return maSP;
	}
	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}
	public LoaiSP getLoai() {
		return loai;
	}
	public void setLoai(LoaiSP loai) {
		this.loai = loai;
	}
	public String getTrangThaiLoi() {
		return trangThaiLoi? "TRUE" : "";
	}
	public void setTrangThaiLoi(String trangThaiLoi) {
		if(trangThaiLoi.equals("TRUE"))
			this.trangThaiLoi = true;
		else this.trangThaiLoi = false;
	}
	
	public double getDonGiaNhap() {
		return donGiaNhap;
	}
	public void setDonGiaNhap(double donGiaNhap) {
		this.donGiaNhap = donGiaNhap;
	}
	public Date getNgayNhap() {
		return ngayNhap;
	}
	public void setNgayNhap(Date ngayNhap) {
		this.ngayNhap = ngayNhap;
	}
	public Date getHanSuDung() {
		return hanSuDung;
	}
	public void setHanSuDung(Date hanSuDung) {
		this.hanSuDung = hanSuDung;
	}
	public Date getHanBaoHanh() {
		return hanBaoHanh;
	}
	public void setHanBaoHanh(Date hanBaoHanh) {
		this.hanBaoHanh = hanBaoHanh;
	}
	public SanPham(String maSP, LoaiSP loai, double donGiaNhap, boolean trangThaiLoi, Date ngayNhap, Date hanSuDung,
			Date hanBaoHanh) {
		super();
		this.maSP = maSP;
		this.loai = loai;
		this.donGiaNhap = donGiaNhap;
		this.trangThaiLoi = trangThaiLoi;
		this.ngayNhap = ngayNhap;
		this.hanSuDung = hanSuDung;
		this.hanBaoHanh = hanBaoHanh;
	}
	public SanPham() {
		super();
	}
	@Override
	public String toString() {
		return "SanPham [maSP=" + maSP + ", loai=" + loai + ", donGiaNhap=" + donGiaNhap + ", trangThaiLoi="
				+ trangThaiLoi + ", ngayNhap=" + ngayNhap + ", hanSuDung=" + hanSuDung + ", hanBaoHanh=" + hanBaoHanh
				+ "]";
	}
}
