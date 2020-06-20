package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import loaiSanPham.LoaiSP;
import nhanVien.TaiKhoan;

public class CTDangNhap {
	private Connection conn;

	public CTDangNhap() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<TaiKhoan> getListTaiKhoan() {
		ArrayList<TaiKhoan> list = new ArrayList<TaiKhoan>();
		String sql = "select * from TaiKhoan";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				TaiKhoan tk = new TaiKhoan();
				tk.setMaNV(rs.getString("MaNV"));
				tk.setUserName(rs.getString("UserName"));
				tk.setPassWord(rs.getString("PassWord"));
				
				list.add(tk);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
}
