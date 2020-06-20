package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import loaiSanPham.LoaiSP;
import nhaCungCap.NhaCungCap;
import nhomHang.NhomHang;
import sanPham.SanPham;

public class CTLoaiSP {
	private Connection conn;

	public CTLoaiSP() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Thêm
	public boolean themLoaiSP(LoaiSP lsp) {
		String sql = "insert into LoaiSanPham(MaLoaiSP,TenLoaiSP,DonViTinh,MaNCC,MaNhomHang,DonGiaBan,XuatXu,Thue) values "
						+ "(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, lsp.getMaLoai());
			ps.setString(2, lsp.getTenLoai());
			ps.setString(3, lsp.getDonViTinh());
			ps.setString(4, lsp.getNcc().getMaNCC());
			ps.setString(5, lsp.getNh().getMaNhomHang());
			ps.setDouble(6, lsp.getDonGiaBan());
			ps.setString(7, lsp.getXuatXu());
			ps.setFloat(8, lsp.getThue());
			
			return ps.executeUpdate() > 0 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	//Xóa
	public boolean xoaLoaiSP(String maLoaiSP) {
		String sql = "delete from LoaiSanPham where MaLoaiSP='"+maLoaiSP+"'";
;
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() > 0 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Sửa
	public boolean suaLoaiSP(String maLSPCu,LoaiSP lsp) {
		String sql="update LoaiSanPham\r\n" + 
				"set MaLoaiSP='"+lsp.getMaLoai()+"', TenLoaiSP=N'"+lsp.getTenLoai()+"',DonViTinh=N'"+lsp.getDonViTinh()+"',MaNCC='"+lsp.getNcc().getMaNCC()+"',MaNhomHang='"+lsp.getNh().getMaNhomHang()+"',"
						+ "DonGiaBan="+lsp.getDonGiaBan()+",XuatXu=N'"+lsp.getXuatXu()+"',Thue="+lsp.getThue()+"\r\n" + 
				"where MaLoaiSP='"+maLSPCu+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() !=-1 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Danh sách tìm kiếm sp comboBox
		public ArrayList<LoaiSP> getListLoaiSPTK_ComboBox(String ncc,String nhomHang,String tenLoaiSP) {
			ArrayList<LoaiSP> list = new ArrayList<LoaiSP>();
			String sql;
			if(!ncc.equals("") && !nhomHang.equals("") && ! tenLoaiSP.equals("")) {
				sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
						"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
						"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
						"where TenLoaiSP=N'"+tenLoaiSP+"' and TenNCC=N'"+ncc+"' and TenNhomHang=N'"+nhomHang+"'";
			}
			else if(!ncc.equals("") && !nhomHang.equals("")){
				sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
						"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
						"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
						"where TenNCC=N'"+ncc+"' and TenNhomHang=N'"+nhomHang+"'";
			}
			else if(!ncc.equals("") && nhomHang.equals(""))
				sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
						"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
						"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
						"where TenNCC=N'"+ncc+"'";
			else if (ncc.equals("") && !nhomHang.equals("") && ! tenLoaiSP.equals(""))
				sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
						"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
						"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
						"where TenLoaiSP=N'"+tenLoaiSP+"' and TenNhomHang=N'"+nhomHang+"'";
			else 
				sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
						"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
						"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
						"where TenNhomHang=N'"+nhomHang+"'";

			list=formListLoaiSP(sql);
			return list;
		}
		//Tìm kiếm nhập
		public ArrayList<LoaiSP> getListLoaiSPTK(String tenLoaiSP) {
			ArrayList<LoaiSP> list = new ArrayList<LoaiSP>();
			String sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
					"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n" + 
					"where TenLoaiSP like N'%"+tenLoaiSP+"%'";

			list=formListLoaiSP(sql);
			return list;
		}
	//From mẫu lấy danh sách
	public ArrayList<LoaiSP> formListLoaiSP(String sql) {
		ArrayList<LoaiSP> list = new ArrayList<LoaiSP>();
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				LoaiSP lsp = new LoaiSP();
				NhaCungCap ncc=new NhaCungCap();
				NhomHang nh = new NhomHang();
				lsp.setMaLoai(rs.getString("MaLoaiSP"));
				lsp.setTenLoai(rs.getString("TenLoaiSP"));
				lsp.setDonViTinh(rs.getString("DonViTinh"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				lsp.setNcc(ncc);
				nh.setTenNhomHang(rs.getString("TenNhomHang"));
				lsp.setNh(nh);
				lsp.setDonGiaBan(rs.getDouble("DonGiaBan"));
				lsp.setSoLuong(rs.getInt("SoLuong"));
				lsp.setXuatXu(rs.getString("XuatXu"));
				lsp.setThue(rs.getFloat("Thue"));

				list.add(lsp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Lấy danh sách 
	public ArrayList<LoaiSP> getListLoaiSP() {
		String sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,TenNCC,TenNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
				"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
				"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n";

		return formListLoaiSP(sql);
	}
	//Lấy danh sách theo mã
		public LoaiSP getLoaiSPTheoMa(String maLoaiSP) {
			String sql = "select MaLoaiSP,TenLoaiSP,DonViTinh,ncc.MaNCC,nh.MaNhomHang,DonGiaBan,XuatXu,SoLuong=dbo.tinhSoLuong(MaLoaiSP),Thue\r\n" + 
					"from LoaiSanPham l join NhaCungCap ncc on l.MaNCC=ncc.MaNCC\r\n" + 
					"					join NhomHang nh on l.MaNhomHang=nh.MaNhomHang\r\n"
					+ "where MaLoaiSP='"+maLoaiSP+"'";
			LoaiSP lsp = new LoaiSP();
			try {
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					NhaCungCap ncc=new NhaCungCap();
					NhomHang nh = new NhomHang();
					lsp.setMaLoai(rs.getString("MaLoaiSP"));
					lsp.setTenLoai(rs.getString("TenLoaiSP"));
					lsp.setDonViTinh(rs.getString("DonViTinh"));
					ncc.setMaNCC(rs.getString("MaNCC"));
					lsp.setNcc(ncc);
					nh.setMaNhomHang(rs.getString("MaNhomHang"));
					lsp.setNh(nh);
					lsp.setDonGiaBan(rs.getDouble("DonGiaBan"));
					lsp.setSoLuong(rs.getInt("SoLuong"));
					lsp.setXuatXu(rs.getString("XuatXu"));
					lsp.setThue(rs.getFloat("Thue"));

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return lsp;
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