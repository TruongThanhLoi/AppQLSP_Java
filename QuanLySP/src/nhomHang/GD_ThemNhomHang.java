package nhomHang;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CTNhaCC;
import controller.CTNhomHang;
import nhaCungCap.NhaCungCap;

public class GD_ThemNhomHang extends JDialog implements ActionListener{
	
	JPanel jpCenter;
	JLabel jlbMaNH,jlbTenNH;
	JTextField jtfMaNH,jtfTenNH;

	JButton btnOK,btnReset;
	private boolean kqThem=false;
	
	public boolean isKqThem() {
		return kqThem;
	}
	public void setKqThem(boolean kqThem) {
		this.kqThem = kqThem;
	}
	public GD_ThemNhomHang() {
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		
		jlbMaNH= new JLabel("Mã nhóm hàng");
		jlbMaNH.setBounds(10, 10, 130, 30);
		jpCenter.add(jlbMaNH);
		jlbTenNH= new JLabel("Tên nhóm hàng");
		jlbTenNH.setBounds(10, 50, 130, 30);
		jpCenter.add(jlbTenNH);
		
		jtfMaNH=new JTextField();
		jtfMaNH.setBounds(131, 10,290 , 30);
		jpCenter.add(jtfMaNH);
		jtfTenNH=new JTextField();
		jtfTenNH.setBounds(131, 50,290 , 30);
		jpCenter.add(jtfTenNH);
		
		btnOK=new JButton("Thêm");
		btnOK.setBounds(131, 100, 100, 30);
		jpCenter.add(btnOK);
		btnReset=new JButton("Reset");
		btnReset.setBounds(280, 100, 100, 30);
		jpCenter.add(btnReset);
		//
		setTitle("Thêm nhóm hàng");
		setBounds(485, 315,430,160);
		setResizable(false);
		setLocationRelativeTo(null);
		//Xử lý button
		btnOK.addActionListener(this);
		btnReset.addActionListener(this);
	}
	public boolean timMaNH(String maNH) {
		for(NhomHang nh : new CTNhomHang().getListNhomHang()) 
			if(nh.getMaNhomHang().equals(maNH)) return true;
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK) {
			if(!jtfMaNH.getText().equals("") ) {
				if(!timMaNH(jtfMaNH.getText())) {
					NhomHang nh = new NhomHang();
					nh.setMaNhomHang(jtfMaNH.getText());
					nh.setTenNhomHang(jtfTenNH.getText());
					 
					if(new CTNhomHang().themNhomHang(nh)) {
						JOptionPane.showMessageDialog(null, "! Đã thêm một nhóm hàng mới.");
						kqThem=true;
					}
					else JOptionPane.showMessageDialog(null, "X Không thành công.");
				}else JOptionPane.showMessageDialog(null, "X Đã có mã nhóm hàng này trong dữ liệu.");
			}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập mã nhóm hàng.");
		}
		if(e.getSource()==btnReset) {
			jtfMaNH.setText("");
			jtfTenNH.setText("");
		}
	}
}


