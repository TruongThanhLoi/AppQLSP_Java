package loaiSanPham;

import java.sql.Date;

import nhaCungCap.NhaCungCap;
import nhomHang.NhomHang;

public class LoaiSP {
	private String maLoai;
	private String tenLoai;
	private String donViTinh;
	private NhomHang nh;
	private NhaCungCap ncc;
	private int soLuong;
	private String xuatXu;
	private float thue;
	private double donGiaBan;
	public String getMaLoai() {
		return maLoai;
	}
	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}
	public String getTenLoai() {
		return tenLoai;
	}
	public void setTenLoai(String tenLoai) {
		this.tenLoai = tenLoai;
	}
	public String getDonViTinh() {
		return donViTinh;
	}
	public void setDonViTinh(String donViTinh) {
		this.donViTinh = donViTinh;
	}
	public NhomHang getNh() {
		return nh;
	}
	public void setNh(NhomHang nh) {
		this.nh = nh;
	}
	public NhaCungCap getNcc() {
		return ncc;
	}
	public void setNcc(NhaCungCap ncc) {
		this.ncc = ncc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public String getXuatXu() {
		return xuatXu;
	}
	public void setXuatXu(String xuatXu) {
		this.xuatXu = xuatXu;
	}
	public float getThue() {
		return thue;
	}
	public void setThue(float thue) {
		this.thue = thue;
	}
	public double getDonGiaBan() {
		return donGiaBan;
	}
	public void setDonGiaBan(double donGiaBan) {
		this.donGiaBan = donGiaBan;
	}
	
	
	public LoaiSP(String maLoai, String tenLoai, String donViTinh, NhomHang nh, NhaCungCap ncc, int soLuong,
			String xuatXu, float thue, double donGiaBan) {
		super();
		this.maLoai = maLoai;
		this.tenLoai = tenLoai;
		this.donViTinh = donViTinh;
		this.nh = nh;
		this.ncc = ncc;
		this.soLuong = soLuong;
		this.xuatXu = xuatXu;
		this.thue = thue;
		this.donGiaBan = donGiaBan;
	}
	public LoaiSP() {
		super();
	}
	@Override
	public String toString() {
		return "LoaiSP [maLoai=" + maLoai + ", tenLoai=" + tenLoai + ", donViTinh=" + donViTinh + ", nh=" + nh
				+ ", ncc=" + ncc + ", soLuong=" + soLuong + ", xuatXu=" + xuatXu + ", thue=" + thue + ", donGiaBan="
				+ donGiaBan + "]";
	}
	
}
