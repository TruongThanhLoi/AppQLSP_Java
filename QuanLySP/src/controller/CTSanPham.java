package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JComboBox;
import javax.swing.text.MaskFormatter;

import hoaDon.CTHD;
import hoaDon.HoaDon;
import loaiSanPham.LoaiSP;
import nhaCungCap.NhaCungCap;
import nhomHang.NhomHang;
import sanPham.SanPham;

public class CTSanPham {
	private Connection conn;

	public CTSanPham() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Sủa
	public boolean suaSanPham(String maSPCu,SanPham sp) {
		String loi=sp.getTrangThaiLoi().equals("TRUE")?"TRUE":"FALSE";
		String hsd = sp.getHanSuDung()==null?"null":"'"+sp.getHanSuDung()+"'";
		String hbh = sp.getHanBaoHanh()==null?"null":"'"+sp.getHanBaoHanh()+"'";
		String sql="update SanPham\r\n" + 
							"set MaSP='"+sp.getMaSP()+"',MaLoaiSP='"+sp.getLoai().getMaLoai()+"',TrangThaiLoi='"+loi+"', " + 
							"HanBaoHanh="+hbh+", HanSuDung="+hsd+"\r\n"+
					"where MaSP='"+maSPCu+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() >0 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//From mẫu lấy danh sách
	public ArrayList<SanPham> formListSanPham(String sql) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				LoaiSP lsp = new LoaiSP();
				sp.setMaSP(rs.getString("MaSP"));
				lsp.setMaLoai(rs.getString("MaLoaiSP"));
				lsp.setTenLoai(rs.getString("TenLoaiSP"));
				lsp.setDonGiaBan(rs.getDouble("DonGiaBan"));
				sp.setLoai(lsp);
				sp.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
				sp.setTrangThaiLoi(rs.getString("TrangThaiLoi"));
				sp.setHanSuDung(rs.getDate("HanSuDung"));
				sp.setHanBaoHanh(rs.getDate("HanBaoHanh"));
				sp.setNgayNhap(rs.getDate("NgayNhap"));
				list.add(sp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//danh sách sản phẩm
	public ArrayList<SanPham> getListSanPham() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql = "select MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP";
		list=formListSanPham(sql);
		return list;
	}
	//danh sách sản phẩm theo ngày
	public ArrayList<SanPham> getListSanPhamTheoHD(String maLoaiSP) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql = "select MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where day(NgayNhap)=day(getdate()) and month(NgayNhap)=month(getdate()) and year(NgayNhap)=year(getdate()) and l.MaLoaiSP='"+maLoaiSP+"'";
		list=formListSanPham(sql);
		return list;
	}
	//danh sách sản phẩm sửa
	public SanPham getSanPhamSua(String maSP) {
		SanPham spchinh = new SanPham();
		String sql = "select MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n"
				+ "where MaSP='"+maSP+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SanPham sp = new SanPham();
				LoaiSP lsp = new LoaiSP();
				sp.setMaSP(rs.getString("MaSP"));
				lsp.setMaLoai(rs.getString("MaLoaiSP"));
				lsp.setTenLoai(rs.getString("TenLoaiSP"));
				lsp.setDonGiaBan(rs.getDouble("DonGiaBan"));
				sp.setLoai(lsp);
				sp.setDonGiaNhap(rs.getDouble("DonGiaNhap"));
				sp.setTrangThaiLoi(rs.getString("TrangThaiLoi"));
				sp.setHanSuDung(rs.getDate("HanSuDung"));
				sp.setHanBaoHanh(rs.getDate("HanBaoHanh"));
				sp.setNgayNhap(rs.getDate("NgayNhap"));
				spchinh=sp;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return spchinh;
	}
	//Danh sách tìm kiếm sp comboBox
	public ArrayList<SanPham> getListSanPhamTK_ComboBox(String ncc,String nhomHang,String tenLoaiSP) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql;
		if(!ncc.equals("") && !nhomHang.equals("") && ! tenLoaiSP.equals("")) {
			sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
					"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"				join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
					"				join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"where ncc.TenNCC=N'"+ncc+"' and n.TenNhomHang=N'"+nhomHang+"' and l.TenLoaiSP=N'"+tenLoaiSP+"'";
		}
		else if(!ncc.equals("") && !nhomHang.equals("")){
			sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
					"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"				join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
					"				join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"where ncc.TenNCC=N'"+ncc+"' and n.TenNhomHang=N'"+nhomHang+"'";
		}
		else if(!ncc.equals("") && nhomHang.equals(""))
			sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
					"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"				join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
					"				join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"where TenNCC=N'"+ncc+"'";
		else if (ncc.equals("") && !nhomHang.equals("") && ! tenLoaiSP.equals(""))
			sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
					"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"				join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
					"				join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"where n.TenNhomHang=N'"+nhomHang+"' and l.TenLoaiSP=N'"+tenLoaiSP+"'";
		else 
			sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
					"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"				join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
					"				join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"where n.TenNhomHang=N'"+nhomHang+"'";

		list=formListSanPham(sql);
		return list;
	}
	//Tìm kiếm nhập
	public ArrayList<SanPham> getListSanPhamTK(String tenLoaiSP) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql= "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where l.TenLoaiSP like N'%"+tenLoaiSP+"%'";;
		

		list=formListSanPham(sql);
		return list;
	}
	///sản phẩm lỗi
	public ArrayList<SanPham> getListSanPhamLoi() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where TrangThaiLoi='TRUE'";


		list=formListSanPham(sql);
		return list;
	}
	//Sản phẩm hết hạn
	public ArrayList<SanPham> getListSanPhamHH() {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql = "select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where (Year(HanSuDung)<Year(getdate())) or (Year(HanSuDung)=Year(getdate()) and Month(HanSuDung)<Month(getdate())) \r\n" + 
				"		or (Year(HanSuDung)=Year(getdate()) and Month(HanSuDung)=Month(getdate()) and Day(HanSuDung)=Day(getdate()))";


		list=formListSanPham(sql);
		return list;
	}

	//Sản phẩm hết hạn theo thang
	public ArrayList<SanPham> getListSanPhamHHThang(int thang,int nam) {
		ArrayList<SanPham> list = new ArrayList<SanPham>();
		String sql ="select s.MaSP,TenLoaiSP,l.MaLoaiSP,DonGiaNhap,DonGiaBan,NgayNhap,TrangThaiLoi,HanBaoHanh,HanSuDung\r\n" + 
				"from SanPham s join LoaiSanPham l on s.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where Year(HanSuDung)="+nam+" and Month(HanSuDung)="+thang;


		list=formListSanPham(sql);
		return list;
	}
	//ComboBox Nhà cung cấp
	public ArrayList<NhaCungCap> getListTenNCC() {
		ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
		String sql = "select TenNCC from NhaCungCap";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc= new NhaCungCap();
				ncc.setTenNCC(rs.getString("TenNCC"));
				
				list.add(ncc);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//ComboBox Nhóm hàng
	public ArrayList<NhomHang> getListTenNH() {
		ArrayList<NhomHang> list = new ArrayList<NhomHang>();
		String sql = "select TenNhomHang from NhomHang";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhomHang nh = new NhomHang();
				nh.setTenNhomHang(rs.getString("TenNhomHang"));
				
				list.add(nh);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//ComboBox Loại sp
	public ArrayList<LoaiSP> getListTenLoaiSP(String nhomHang) {
		ArrayList<LoaiSP> list = new ArrayList<LoaiSP>();
		String sql = "select TenLoaiSP\r\n" + 
				"from LoaiSanPham l join NhomHang n on l.MaNhomHang=n.MaNhomHang\r\n" + 
				"where TenNhomHang=N'"+nhomHang+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LoaiSP lsp= new LoaiSP();
				lsp.setTenLoai(rs.getString("TenLoaiSP"));
				
				list.add(lsp);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
}
