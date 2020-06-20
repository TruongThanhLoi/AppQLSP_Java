package loaiSanPham;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import controller.CTLoaiSP;
import controller.CTNhaCC;
import controller.CTNhomHang;
import nhaCungCap.NhaCungCap;
import nhaCungCap.GD_SuaNCC.KqSuaNCC;
import nhomHang.NhomHang;

public class GD_SuaLoaiSP extends JDialog implements ActionListener{
	
	JPanel jpCenter;
	JLabel jlbMaLSP,jlbTenLSP,jlbMaNCC,jlbMaNhomHang,jlbGiaBan,jlbDonViTinh,jlbSoLuongSP,jlbXuatXu,jlbThue;
	JTextField jtfMaLSP,jtfTenLSP,jtfMaNCC,jtfMaNhomHang,jtfGiaBan,jtfDonViTinh,jtfXuatXu,jtfSoLuongSP,jtfThue;

	JButton btnOK,btnReset;
	private String maLoaiSPCu;
	private boolean kqSua=false;
	
	public boolean isKqSua() {
		return kqSua;
	}
	public void setKqSua(boolean kqSua) {
		this.kqSua = kqSua;
	}
	public GD_SuaLoaiSP(String maLoaiSP) {
		maLoaiSPCu=maLoaiSP;
		LoaiSP lsp = new LoaiSP();
		lsp= new CTLoaiSP().getLoaiSPTheoMa(maLoaiSP);
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		jlbMaLSP= new JLabel("Mã loại sản phẩm:");
		jlbMaLSP.setBounds(10, 10, 130, 30);
		jpCenter.add(jlbMaLSP);
		jlbTenLSP= new JLabel("Tên loại sản phẩm:");
		jlbTenLSP.setBounds(10, 50, 130, 30);
		jpCenter.add(jlbTenLSP);
		jlbMaNCC= new JLabel("Mã nhà cung cấp:");
		jlbMaNCC.setBounds(10, 90, 130, 30);
		jpCenter.add(jlbMaNCC);
		jlbMaNhomHang= new JLabel("Mã nhóm hàng:");
		jlbMaNhomHang.setBounds(10, 130, 130, 30);
		jpCenter.add(jlbMaNhomHang);
		jlbGiaBan= new JLabel("Đơn giá bán:");
		jlbGiaBan.setBounds(10, 170, 130, 30);
		jpCenter.add(jlbGiaBan);
		jlbDonViTinh= new JLabel("Đơn vị tính:");
		jlbDonViTinh.setBounds(10, 210, 130, 30);
		jpCenter.add(jlbDonViTinh);
		jlbSoLuongSP= new JLabel("Số lượng sản phẩm:");
		jlbSoLuongSP.setBounds(10, 250, 130, 30);
		jpCenter.add(jlbSoLuongSP);
		jlbXuatXu= new JLabel("Xuất xứ:");
		jlbXuatXu.setBounds(10, 290, 130, 30);
		jpCenter.add(jlbXuatXu);
		jlbThue= new JLabel("Thuế:");
		jlbThue.setBounds(10, 330, 130, 30);
		jpCenter.add(jlbThue);
		
		jtfMaLSP=new JTextField(lsp.getMaLoai());
		jtfMaLSP.setBounds(131, 10,290 , 30);
		jpCenter.add(jtfMaLSP);
		jtfTenLSP=new JTextField(lsp.getTenLoai());
		jtfTenLSP.setBounds(131, 50,290 , 30);
		jpCenter.add(jtfTenLSP);
		jtfMaNCC=new JTextField(lsp.getNcc().getMaNCC());
		jtfMaNCC.setBounds(131, 90,290 , 30);
		jpCenter.add(jtfMaNCC);
		jtfMaNhomHang=new JTextField(lsp.getNh().getMaNhomHang());
		jtfMaNhomHang.setBounds(131, 130,290 , 30);
		jpCenter.add(jtfMaNhomHang);
		jtfGiaBan=new JTextField(lsp.getDonGiaBan()+"");
		jtfGiaBan.setBounds(131, 170,290 , 30);
		jpCenter.add(jtfGiaBan);
		jtfDonViTinh=new JTextField(lsp.getDonViTinh());
		jtfDonViTinh.setBounds(131, 210,290 , 30);
		jpCenter.add(jtfDonViTinh);
		jtfSoLuongSP=new JTextField(lsp.getSoLuong());
		jtfSoLuongSP.setBounds(131, 250,290 , 30);
		jpCenter.add(jtfSoLuongSP);
		jtfSoLuongSP.disable();
		jtfXuatXu=new JTextField(lsp.getXuatXu());
		jtfXuatXu.setBounds(131, 290,290 , 30);
		jpCenter.add(jtfXuatXu);
		jtfThue=new JTextField(lsp.getThue()+"");
		jtfThue.setBounds(131, 330,290 , 30);
		jpCenter.add(jtfThue);

		btnOK=new JButton("Xong");
		btnOK.setBounds(131, 380, 100, 30);
		jpCenter.add(btnOK);
		btnReset=new JButton("Reset");
		btnReset.setBounds(280, 380, 100, 30);
		jpCenter.add(btnReset);
		//
		setTitle("Chỉnh sửa loại sản phẩm");
		setBounds(485, 100,450, 450);
		setResizable(false);
		setLocationRelativeTo(null);
		//Xử lý button
		btnOK.addActionListener(this);
	}
	public boolean kiemTraLoaiSP(String maLoaiSP) {
		for(LoaiSP lsp : new CTLoaiSP().getListLoaiSP()) {
			if(lsp.getMaLoai().equals(maLoaiSP)) return true;
		}
		return false;
	}
	public boolean kiemTraMaNCC(String maNCC) {
		for(NhaCungCap ncc : new CTNhaCC().getListNhaCC()) 
			if(ncc.getMaNCC().equals(maNCC)) return true;
		return false;
	}
	public boolean kiemTraMaNH(String maNH) {
		for(NhomHang nh : new CTNhomHang().getListNhomHang()) 
			if(nh.getMaNhomHang().equals(maNH)) return true;
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK) {
			if(!jtfMaLSP.getText().equals("") && !jtfMaNCC.getText().equals("") && !jtfMaNhomHang.getText().equals("")) {
				if(kiemTraMaNCC(jtfMaNCC.getText()) && kiemTraMaNH(jtfMaNhomHang.getText())){
					if(jtfMaLSP.getText().equals(maLoaiSPCu) || !kiemTraLoaiSP(jtfMaLSP.getText())) {
						int soLuongSP = jtfSoLuongSP.getText().equals("") ? 0:Integer.parseInt(jtfSoLuongSP.getText());
						float thue = jtfThue.getText().equals("")? 0:Float.parseFloat(jtfThue.getText());
						double donGia = jtfGiaBan.getText().equals("")? 0:Double.parseDouble(jtfGiaBan.getText());
						NhaCungCap ncc = new NhaCungCap();
						ncc.setMaNCC(jtfMaNCC.getText());
						NhomHang nh = new NhomHang();
						nh.setMaNhomHang(jtfMaNhomHang.getText());
						LoaiSP lsp = new LoaiSP(jtfMaLSP.getText(), jtfTenLSP.getText(), jtfDonViTinh.getText(), nh, 
								ncc, soLuongSP, jtfXuatXu.getText(),thue , donGia);
						if(new CTLoaiSP().suaLoaiSP(maLoaiSPCu,lsp)) {
							JOptionPane.showMessageDialog(null, "! Chỉnh sửa thành công.");
							kqSua=true;
						}
						else JOptionPane.showMessageDialog(null, "X Không thêm được.");
					}else JOptionPane.showMessageDialog(null, "X Đã có mã loại sản phẩm này.");
				}else JOptionPane.showMessageDialog(null, "X Không có mã nhà cung cấp hoặc mã nhóm hàng.");
			}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập mã loại sản phẩm, mã nhà cung cấp và mã nhóm hàng.");
		}
		if(e.getSource()==btnReset) {
			jtfTenLSP.setText("");
			jtfDonViTinh.setText("");
			jtfMaNCC.setText("");
			jtfMaNhomHang.setText("");
			jtfGiaBan.setText("");
			jtfXuatXu.setText("");
			jtfThue.setText("");
		}
	}
}
