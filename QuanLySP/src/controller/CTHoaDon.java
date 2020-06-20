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
import nhanVien.NhanVien;
import sanPham.SanPham;

public class CTHoaDon{
	private Connection conn;

	public CTHoaDon() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	Thêm Hóa đơn nhập
	public boolean themHoaDonNhap(HoaDon hd) {
		String sql = "insert into HoaDonNhap(MaHDN,MaNV,NgayLapHD) values " + "(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hd.getMaHD());
			ps.setString(2, hd.getNhanVien().getMaNV());
			ps.setDate(3, new Date(hd.getNgayLapHD().getTime()));

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	//Thêm CTHD Nhập
	public boolean themCTHDNhap(CTHD cthd) {
		String sql = "insert into ChiTietHDN values\r\n" + 
				"(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cthd.getMaHD());
			ps.setString(2, cthd.getLsp().getMaLoai());
			ps.setInt(3, cthd.getSoLuong());
			
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//Thêm sản phẩm
	public boolean themSanPham(SanPham sp) {
		String sql = "insert SanPham values\r\n" + 
				"(?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, sp.getMaSP());
			ps.setString(2, sp.getLoai().getMaLoai());
			ps.setDouble(3, sp.getDonGiaNhap());
			if(sp.getTrangThaiLoi().equals("TRUE"))
				ps.setString(4,sp.getTrangThaiLoi());
			else
				ps.setString(4,"FALSE");
			ps.setDate(5, sp.getNgayNhap());
			ps.setDate(6, sp.getHanBaoHanh());
			ps.setDate(7, sp.getHanSuDung());
			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	Thêm Hóa đơn Xuất
	public boolean themHoaDonXuat(HoaDon hd) {
		String sql = "insert into HoaDonXuat(MaHDX,MaNV,NgayLapHD) values " + "(?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, hd.getMaHD());
			ps.setString(2, hd.getNhanVien().getMaNV());
			ps.setDate(3, hd.getNgayLapHD());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Thêm CTHD Xuất
	public boolean themCTHDXuat(CTHD cthd) {
		String sql = "insert ChiTietHDX values\r\n" + 
				"(?,?,?)\r\n" + 
				"delete top (?)from SanPham\r\n" + 
				"where MaLoaiSP=?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, cthd.getMaHD());
			ps.setString(2, cthd.getLsp().getMaLoai());
			ps.setInt(3, cthd.getSoLuong());
			ps.setInt(4, cthd.getSoLuong());
			ps.setString(5, cthd.getLsp().getMaLoai());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//form get list hoa don
	public ArrayList<HoaDon> getListHDForm(String sql,String sql1){
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HoaDon hdn = new HoaDon();
				hdn.setMaHD(rs.getString("MaHDN"));
				hdn.setNhanVien(new NhanVien(rs.getString("MaNV")));
				hdn.setNgayLapHD(rs.getDate("NgayLapHD"));
				hdn.setTongTien(rs.getDouble("TongTien"));
			
				list.add(hdn);
			}

			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				HoaDon hdx = new HoaDon();
				hdx.setMaHD(rs1.getString("MaHDX"));
				hdx.setNhanVien(new NhanVien(rs1.getString("MaNV")));
				hdx.setNgayLapHD(rs1.getDate("NgayLapHD"));
				hdx.setTongTien(rs1.getDouble("TongTien"));
			
				list.add(hdx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
//		Hóa đơn
	public ArrayList<HoaDon> getListHoaDon() {
		String sql = "select h.MaHDN,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from HoaDonNhap h left join ChiTietHDN c on h.MaHDN=c.MaHDN\r\n" + 
				"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by h.MaHDN,MaNV,NgayLapHD";
		String sql1 = "select h.MaHDX,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from HoaDonXuat h left join ChiTietHDX c on h.MaHDX=c.MaHDX\r\n" + 
				"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by h.MaHDX,MaNV,NgayLapHD";
		return getListHDForm(sql, sql1);
	}
	//	Hóa đơn nhập
	public ArrayList<HoaDon> getListHoaDonNhap() {
		String sql = "select h.MaHDN,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from HoaDonNhap h left join ChiTietHDN c on h.MaHDN=c.MaHDN\r\n" + 
				"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by h.MaHDN,MaNV,NgayLapHD";
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				HoaDon hdn = new HoaDon();
				hdn.setMaHD(rs.getString("MaHDN"));
				hdn.setNhanVien(new NhanVien(rs.getString("MaNV")));
				hdn.setNgayLapHD(rs.getDate("NgayLapHD"));
				hdn.setTongTien(rs.getDouble("TongTien"));
			
				list.add(hdn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//	Hóa đơn Xuất
	public ArrayList<HoaDon> getListHoaDonXuat() {
		String sql1 = "select h.MaHDX,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from HoaDonXuat h left join ChiTietHDX c on h.MaHDX=c.MaHDX\r\n" + 
				"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by h.MaHDX,MaNV,NgayLapHD";
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		try {
			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				HoaDon hdx = new HoaDon();
				hdx.setMaHD(rs1.getString("MaHDX"));
				hdx.setNhanVien(new NhanVien(rs1.getString("MaNV")));
				hdx.setNgayLapHD(rs1.getDate("NgayLapHD"));
				hdx.setTongTien(rs1.getDouble("TongTien"));
			
				list.add(hdx);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//	Hóa đơn tìm kiếm
	public ArrayList<HoaDon> getListHoaDonTK(String maHD,String ngay) {
		String sql,sql1;
		if(!maHD.equals("")&&ngay.equals("")) {
			sql = "select h.MaHDN,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
					"from HoaDonNhap h left join ChiTietHDN c on h.MaHDN=c.MaHDN\r\n" + 
					"					left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where h.MaHDN='"+maHD+"'\r\n" + 
					"group by h.MaHDN,MaNV,NgayLapHD";
			sql1 = "select h.MaHDX,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
					"from HoaDonXuat h left join ChiTietHDX c on h.MaHDX=c.MaHDX\r\n" + 
					"					left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where h.MaHDX='"+maHD+"'\r\n" + 
					"group by h.MaHDX,MaNV,NgayLapHD";
			return getListHDForm(sql, sql1);
		}
		if(maHD.equals("")&&!ngay.equals("")) {
			sql = "select h.MaHDN,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
					"from HoaDonNhap h left join ChiTietHDN c on h.MaHDN=c.MaHDN\r\n" + 
					"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where NgayLapHD='"+ngay+"'\r\n"+
					"group by h.MaHDN,MaNV,NgayLapHD";
			sql1 = "select h.MaHDX,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from HoaDonXuat h left join ChiTietHDX c on h.MaHDX=c.MaHDX\r\n" + 
				"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where NgayLapHD='"+ngay+"'\r\n"+
				"group by h.MaHDX,MaNV,NgayLapHD";
			return getListHDForm(sql, sql1);
		}
		else {
			sql = "select h.MaHDN,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
					"from HoaDonNhap h left join ChiTietHDN c on h.MaHDN=c.MaHDN\r\n" + 
					"left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where h.MaHDN='"+maHD+"' and NgayLapHD='"+ngay+"'\r\n"+
					"group by h.MaHDN,MaNV,NgayLapHD";
			sql1 = "select h.MaHDX,MaNV,NgayLapHD,TongTien=Sum(SoLuong*DonGiaBan)\r\n" + 
					"from HoaDonXuat h left join ChiTietHDX c on h.MaHDX=c.MaHDX\r\n" + 
					"					left join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
					"where h.MaHDX='"+maHD+"' and NgayLapHD='"+ngay+"'\r\n" + 
					"group by h.MaHDX,MaNV,NgayLapHD";
			return getListHDForm(sql, sql1);
		}
	}
	//form lấy cthd
	public ArrayList<CTHD> getListCTHDForm(String sql,String sql1){
		ArrayList<CTHD> list = new ArrayList<CTHD>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				CTHD ct = new CTHD();
				LoaiSP lsp = new LoaiSP();
				ct.setMaHD(rs.getString("MaHDN"));
				lsp.setMaLoai(rs.getString("MaLoaiSP"));
				lsp.setDonGiaBan(rs.getDouble("DonGiaBan"));
				ct.setLsp(lsp);
				ct.setSoLuong(rs.getInt("SoLuong"));
				ct.setTongTien(rs.getDouble("ThanhTien"));
				
				list.add(ct);
			}

			PreparedStatement ps1 = conn.prepareStatement(sql1);
			ResultSet rs1 = ps1.executeQuery();
			while (rs1.next()) {
				CTHD ct = new CTHD();
				LoaiSP lsp = new LoaiSP();
				ct.setMaHD(rs1.getString("MaHDX"));
				lsp.setMaLoai(rs1.getString("MaLoaiSP"));
				lsp.setDonGiaBan(rs1.getDouble("DonGiaBan"));
				ct.setLsp(lsp);
				ct.setSoLuong(rs1.getInt("SoLuong"));
				ct.setTongTien(rs1.getDouble("ThanhTien"));
				
				list.add(ct);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Lấy cthd
	public ArrayList<CTHD> getListCTHD() {
		String sql = "select MaHDN,l.MaLoaiSP,SoLuong,DonGiaBan,ThanhTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from ChiTietHDN c join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by MaHDN,l.MaLoaiSP,SoLuong,DonGiaBan";
		String sql1 = "select MaHDX,l.MaLoaiSP,SoLuong,DonGiaBan,ThanhTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from ChiTietHDX c join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"group by MaHDX,l.MaLoaiSP,SoLuong,DonGiaBan";
		
		return getListCTHDForm(sql, sql1);
	}

	//Lấy cthd theo mã
	public ArrayList<CTHD> getListCTHDTheoMa(String maHD) {
		String sql = "select MaHDN,l.MaLoaiSP,SoLuong,DonGiaBan,ThanhTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from ChiTietHDN c join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" + 
				"where MaHDN='"+maHD+"'\r\n"+
				"group by MaHDN,l.MaLoaiSP,SoLuong,DonGiaBan";
		String sql1 = "select MaHDX,l.MaLoaiSP,SoLuong,DonGiaBan,ThanhTien=Sum(SoLuong*DonGiaBan)\r\n" + 
				"from ChiTietHDX c join LoaiSanPham l on c.MaLoaiSP=l.MaLoaiSP\r\n" +  
				"where MaHDX='"+maHD+"'\r\n"+
				"group by MaHDX,l.MaLoaiSP,SoLuong,DonGiaBan";
		
		return getListCTHDForm(sql, sql1);
	}
	//Đếm số lượng hd nhập
	public int getLaySLHDN() {
		ArrayList<HoaDon> list = getListHoaDonNhap();
		return list.size();
	}
	//Đếm số lượng hd Xuất
	public int getLaySLHDX() {
		ArrayList<HoaDon> list = getListHoaDonXuat();
		return list.size();
	}
}