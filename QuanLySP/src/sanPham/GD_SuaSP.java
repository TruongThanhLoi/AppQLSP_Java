package sanPham;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CTLoaiSP;
import controller.CTSanPham;
import loaiSanPham.LoaiSP;

public class GD_SuaSP extends JDialog implements ActionListener{
	public enum KqSua {
		OK,LOI
	}
	JPanel jpCenter;
	JLabel jlbMaSP,jlbMaLoai,jlbTrangThaiLoi,jlbHanBaoHanh,jlbHanSuDung;
	JTextField jtfMaSP,jtfMaLoai,jtfTrangThaiLoi,jtfHanBaoHanh,jtfHanSuDung;
	JButton btnOK,btnReset;
	private SanPham sp;
	private ArrayList<SanPham> listsp= new CTSanPham().getListSanPham();
	private KqSua kqSua;
	private String maSPCu="";
	
	public KqSua getKqSua() {
		return kqSua;
	}
	public void setKqSua(KqSua kqSua) {
		this.kqSua = kqSua;
	}
	public GD_SuaSP(String maSP) {
		maSPCu=maSP;
		
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		
		jlbMaSP= new JLabel("Mã sản phẩm:");
		jlbMaSP.setBounds(10, 10, 130, 30);
		jpCenter.add(jlbMaSP);
		jlbMaLoai= new JLabel("Mã loại sản phẩm:");
		jlbMaLoai.setBounds(10, 50, 130, 30);
		jpCenter.add(jlbMaLoai);
		jlbTrangThaiLoi= new JLabel("Trạng thái lỗi:");
		jlbTrangThaiLoi.setBounds(10, 90, 130, 30);
		jpCenter.add(jlbTrangThaiLoi);
		jlbHanSuDung= new JLabel("Ngày hết hạn:");
		jlbHanSuDung.setBounds(10, 130, 130, 30);
		jpCenter.add(jlbHanSuDung);
		jlbHanBaoHanh= new JLabel("Hạn bảo hành:");
		jlbHanBaoHanh.setBounds(10, 170, 130, 30);
		jpCenter.add(jlbHanBaoHanh);
		

		sp = new CTSanPham().getSanPhamSua(maSP);
		jtfMaSP=new JTextField(sp.getMaSP());
		jtfMaSP.setBounds(131, 10,290 , 30);
		jpCenter.add(jtfMaSP);
		jtfMaSP.disable();
		jtfMaLoai=new JTextField(sp.getLoai().getMaLoai());
		jtfMaLoai.setBounds(131, 50,290 , 30);
		jpCenter.add(jtfMaLoai);
		jtfMaLoai.disable();
		jpCenter.add(jtfTrangThaiLoi = new JTextField("TRUE"));
		jtfTrangThaiLoi.setBounds(131, 90,290 , 30);
		if(sp.getHanSuDung()!=null) jtfHanSuDung = new JTextField(sp.getHanSuDung()+"");
		else jtfHanSuDung = new JTextField("");
		jpCenter.add(jtfHanSuDung);
		jtfHanSuDung.setBounds(131, 130,290 , 30);
		if(sp.getHanBaoHanh()!=null) jtfHanBaoHanh = new JTextField(sp.getHanBaoHanh()+"");
		else jtfHanBaoHanh = new JTextField("");
		jpCenter.add(jtfHanBaoHanh);
		jtfHanBaoHanh.setBounds(131, 170,290 , 30);
		
		btnOK=new JButton("Xong");
		btnOK.setBounds(131, 220, 100, 30);
		jpCenter.add(btnOK);
		btnReset=new JButton("Reset");
		btnReset.setBounds(280, 220, 100, 30);
		jpCenter.add(btnReset);
		//
		setTitle("Chỉnh sửa Sản phẫm");
		setBounds(485, 220,450, 300);
		setResizable(false);
		setLocationRelativeTo(null);
		/// xử lý button
		btnOK.addActionListener(this);
		btnReset.addActionListener(this);
	}
	public boolean timMaSP(String maSP) {
		for(SanPham sp: listsp) {
			if(sp.getMaSP().equals(maSP)) return true;
		}
		return false;
	}
	public int layNgay(String ngayThang) {
		return Integer.parseInt(ngayThang.charAt(8)+"")*10+Integer.parseInt(ngayThang.charAt(9)+"");
	}
	public int layThang(String ngayThang) {
		return Integer.parseInt(ngayThang.charAt(5)+"")*10+Integer.parseInt(ngayThang.charAt(6)+"");
	}
	public int layNam(String ngayThang) {
		return Integer.parseInt(ngayThang.charAt(0)+"")*1000+Integer.parseInt(ngayThang.charAt(1)+"")*100
				+ Integer.parseInt(ngayThang.charAt(2)+"")*10+Integer.parseInt(ngayThang.charAt(3)+"");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK) {
			try {
				if(!jtfMaSP.getText().equals("")) {
					if(jtfMaSP.getText().equals(maSPCu) || !timMaSP(jtfMaSP.getText())) {
						SanPham sp = new SanPham();
						LoaiSP lsp = new LoaiSP();
						sp.setMaSP(jtfMaSP.getText());
						lsp.setMaLoai(jtfMaLoai.getText());
						sp.setLoai(lsp);
						sp.setTrangThaiLoi(jtfTrangThaiLoi.getText());
						if(!jtfHanBaoHanh.getText().equals("")) sp.setHanBaoHanh(Date.valueOf(LocalDate.of(layNam(jtfHanBaoHanh.getText()), layThang(jtfHanBaoHanh.getText()), 
								layNgay(jtfHanBaoHanh.getText()))));
						if(!jtfHanSuDung.getText().equals("")) sp.setHanSuDung(Date.valueOf(LocalDate.of(layNam(jtfHanSuDung.getText()), layThang(jtfHanSuDung.getText()), 
								layNgay(jtfHanSuDung.getText()))));
						if(new CTSanPham().suaSanPham(maSPCu,sp)){
							JOptionPane.showMessageDialog(null, "! Cập nhật thành công.");
							kqSua=KqSua.OK;
							this.setVisible(false);
						}else {
							JOptionPane.showMessageDialog(null, "X Cập nhật không thành công.");
							kqSua=KqSua.LOI;
						}
					}else {
						JOptionPane.showMessageDialog(null, "X Đã có mã sản phẩm này.");
						kqSua=KqSua.LOI;
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "X Cập nhật không thành công: Chưa nhập mã sản phẩm.");
					kqSua=KqSua.LOI;
				}
			} catch (Exception e2) {
				JOptionPane.showMessageDialog(null, "Xin hãy nhập đàng hoàng!");
			}
		}
		if(e.getSource()==btnReset) {
			jtfMaLoai.setText("");
			jtfTrangThaiLoi.setText("");
		}
	}
	
}
