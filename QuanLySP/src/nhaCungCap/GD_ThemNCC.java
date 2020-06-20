package nhaCungCap;

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

public class GD_ThemNCC extends JDialog implements ActionListener{
	
	JPanel jpCenter;
	JLabel jlbMaNCC,jlbTenNCC,jlbDiaChi,jlbEmail,jlbSDT;
	JTextField jtfMaNCC,jtfTenNCC,jtfDiaChi,jtfEmail,jtfSDT;

	JButton btnOK,btnReset;
	private boolean kq=false;
	
	
	public boolean isKq() {
		return kq;
	}
	public void setKq(boolean kq) {
		this.kq = kq;
	}
	public GD_ThemNCC() {
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		
		jlbMaNCC= new JLabel("Mã nhà cung cấp");
		jlbMaNCC.setBounds(10, 10, 130, 30);
		jpCenter.add(jlbMaNCC);
		jlbTenNCC= new JLabel("Tên nhà cung cấp");
		jlbTenNCC.setBounds(10, 50, 130, 30);
		jpCenter.add(jlbTenNCC);
		jlbDiaChi= new JLabel("Địa chỉ");
		jlbDiaChi.setBounds(10, 90, 130, 30);
		jpCenter.add(jlbDiaChi);
		jlbSDT= new JLabel("Số điện thoại");
		jlbSDT.setBounds(10, 130, 130, 30);
		jpCenter.add(jlbSDT);
		jlbEmail= new JLabel("Email");
		jlbEmail.setBounds(10, 170, 130, 30);
		jpCenter.add(jlbEmail);
		
		jtfMaNCC=new JTextField();
		jtfMaNCC.setBounds(131, 10,290 , 30);
		jpCenter.add(jtfMaNCC);
		jtfTenNCC=new JTextField();
		jtfTenNCC.setBounds(131, 50,290 , 30);
		jpCenter.add(jtfTenNCC);
		jtfDiaChi=new JTextField();
		jtfDiaChi.setBounds(131, 90,290 , 30);
		jpCenter.add(jtfDiaChi);
		jtfSDT= new JTextField();
		jtfSDT.setBounds(131, 130, 290, 30);
		jpCenter.add(jtfSDT);
		jtfEmail= new JTextField();
		jtfEmail.setBounds(131, 170, 290, 30);
		jpCenter.add(jtfEmail);
		
		btnOK=new JButton("Thêm");
		btnOK.setBounds(131, 230, 100, 30);
		jpCenter.add(btnOK);
		btnReset=new JButton("Reset");
		btnReset.setBounds(280, 230, 100, 30);
		jpCenter.add(btnReset);
		//
		setTitle("Thêm nhà cung cấp");
		setBounds(485, 200,450, 330);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//Xử lý button
		btnOK.addActionListener(this);
		btnReset.addActionListener(this);
	}
	public boolean timMaNCC(String maNCC) {
		for(NhaCungCap ncc : new CTNhaCC().getListNhaCC()) 
			if(ncc.getMaNCC().equals(maNCC)) return true;
		return false;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOK) {
			if(!jtfMaNCC.getText().equals("") && !jtfTenNCC.getText().equals("")) {
				if(!timMaNCC(jtfMaNCC.getText())) {
					NhaCungCap ncc = new NhaCungCap();
					ncc.setMaNCC(jtfMaNCC.getText());
					ncc.setTenNCC(jtfTenNCC.getText());
					ncc.setDicChiNCC(jtfDiaChi.getText());
					ncc.setSdtNCC(jtfSDT.getText());
					ncc.setEmailNCC(jtfEmail.getText());
					 
					if(new CTNhaCC().themNhaCC(ncc)) {
						JOptionPane.showMessageDialog(null, "! Đã thêm một nhà cung cấp mới.");
						kq=true;
					}
					else JOptionPane.showMessageDialog(null, "X Không thành công.");
				}else JOptionPane.showMessageDialog(null, "X Đã có mã nhà cung cấp này trong dữ liệu.");
			}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập mã nhà cung cấp và tên nhà cung cấp.");
		}
		if(e.getSource()==btnReset) {
			jtfMaNCC.setText("");
			jtfTenNCC.setText("");
			jtfDiaChi.setText("");
			jtfSDT.setText("");
			jtfEmail.setText("");
		}
	}
}


