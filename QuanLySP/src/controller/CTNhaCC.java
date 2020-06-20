package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.text.MaskFormatter;

import nhaCungCap.NhaCungCap;

public class CTNhaCC {
	private Connection conn;

	public CTNhaCC() {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;instance=SQLEXPRESS;databasename=QLSP;",
					"sa", "sapassword");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//Thêm
	public boolean themNhaCC(NhaCungCap ncc) {
		String sql = "insert into NhaCungCap(MaNCC,TenNCC,DiaChiNCC,SdtNCC,EmailNCC) values " + "(?,?,?,?,?)";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, ncc.getMaNCC());
			ps.setString(2, ncc.getTenNCC());
			ps.setString(3, ncc.getDicChiNCC());
			ps.setString(4, ncc.getSdtNCC());
			ps.setString(5, ncc.getEmailNCC());

			return ps.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Lấy danh sach toàn bộ
	public ArrayList<NhaCungCap> getListNhaCC() {
		ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
		String sql = "select * from NhaCungCap";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("MaNCC"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				ncc.setDicChiNCC(rs.getString("DiaChiNCC"));
				ncc.setSdtNCC(rs.getString("SdtNCC"));
				ncc.setEmailNCC(rs.getString("EmailNCC"));
				
				list.add(ncc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Lấy ds tìm kiếm
	public ArrayList<NhaCungCap> getListNhaCCTK(String tenNcc) {
		ArrayList<NhaCungCap> list = new ArrayList<NhaCungCap>();
		String sql = "select * from NhaCungCap where TenNCC like '%"+tenNcc+"%'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				NhaCungCap ncc = new NhaCungCap();
				ncc.setMaNCC(rs.getString("MaNCC"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				ncc.setDicChiNCC(rs.getString("DiaChiNCC"));
				ncc.setSdtNCC(rs.getString("SdtNCC"));
				ncc.setEmailNCC(rs.getString("EmailNCC"));
				
				list.add(ncc);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	//Lấy ds theo mã
	public NhaCungCap getListNhaCCTheoMa(String maNcc) {
		NhaCungCap ncc = new NhaCungCap();
		String sql = "select * from NhaCungCap where MaNCC like '"+maNcc+"'";

		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				ncc.setMaNCC(rs.getString("MaNCC"));
				ncc.setTenNCC(rs.getString("TenNCC"));
				ncc.setDicChiNCC(rs.getString("DiaChiNCC"));
				ncc.setSdtNCC(rs.getString("SdtNCC"));
				ncc.setEmailNCC(rs.getString("EmailNCC"));
				
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return ncc;
	}
	//Sửa
	public boolean suaNCC(String maNCC,NhaCungCap ncc) {
		String sql="update NhaCungCap\r\n" + 
				"set MaNCC='"+ncc.getMaNCC()+"',TenNCC =N'"+ncc.getTenNCC()+"',DiaChiNCC=N'"+ncc.getDicChiNCC()+"',SdtNCC='"+ncc.getSdtNCC()+"',EmailNCC='"+ncc.getEmailNCC()+"'\r\n"+ 
				"where MaNCC='"+maNCC+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() !=-1 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	//Xóa ncc
	public boolean xoaNCC(NhaCungCap ncc) {
		String sql="delete from NhaCungCap\r\n" + 
				"where MaNCC='"+ncc.getMaNCC()+"'";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			return ps.executeUpdate() !=-1 ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
}