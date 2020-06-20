package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import nhaCungCap.NhaCungCap;
import nhomHang.NhomHang;

public class CTNhomHang {
	private Connection conn;

	public CTNhomHang() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Thêm
	public boolean themNhomHang(NhomHang nh) {
		String sql = "insert into NhomHang(MaNhomHang,TenNhomHang) values (?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, nh.getMaNhomHang());
			ps.setString(2, nh.getTenNhomHang());
			
///
			return ps.executeUpdate() > 0 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Sửa
	public boolean suaNhomHang(String maNHCu,NhomHang nh) {
		String sql = "update NhomHang\r\n"
				+ "set MaNhomHang='"+nh.getMaNhomHang()+"', TenNhomHang=N'"+nh.getTenNhomHang()+"'\r\n"
				+ "where MaNhomHang='"+maNHCu+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() > 0 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Xóa ncc
	public boolean xoaNH(String maNH) {
		String sql="delete from NhomHang\r\n" + 
				"where MaNhomHang='"+maNH+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() !=-1 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
//	//List Nhóm Hàng
	public ArrayList<NhomHang> getListNhomHang() {
		ArrayList<NhomHang> list = new ArrayList<NhomHang>();
		String sql = "select MaNhomHang,TenNhomHang from NhomHang";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhomHang nh = new NhomHang();
				nh.setMaNhomHang(rs.getString("MaNhomHang"));
				nh.setTenNhomHang(rs.getString("TenNhomHang"));

				list.add(nh);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	public ArrayList<NhomHang> getListNhomHangTK(String tenNhomHang) {
		ArrayList<NhomHang> list = new ArrayList<NhomHang>();
		String sql = "select MaNhomHang,TenNhomHang from NhomHang where TenNhomHang like N'%"+tenNhomHang+"%'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhomHang nh = new NhomHang();
				nh.setMaNhomHang(rs.getString("MaNhomHang"));
				nh.setTenNhomHang(rs.getString("TenNhomHang"));

				list.add(nh);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Get mã nhóm hàng theo mã
	public NhomHang getNhomHangTheoMa(String maNhomHang) {
		NhomHang nh = new NhomHang();
		String sql = "select MaNhomHang,TenNhomHang from NhomHang where MaNhomHang = '"+maNhomHang+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				nh.setMaNhomHang(rs.getString("MaNhomHang"));
				nh.setTenNhomHang(rs.getString("TenNhomHang"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return nh;
	}
}