package boundary;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CTDangNhap;
import controller.CTSanPham;
import nhanVien.TaiKhoan;
import sanPham.GDSanPham;
import sanPham.SanPham;

public class GDChinh extends JFrame implements ActionListener {

	JPanel jpCenter;
	JLabel jlbDangNhap, jlbUser, jlbPass,jlbLoiUser,jlbLoiPass,jlbLoiUser1,jlbLoiPass1;
	JTextField jtfUser;
	JPasswordField jtfPass;
	JButton btnDangNhap, btnThoat;
	private ArrayList<TaiKhoan> list;
	private String MaNV;
	
	public String getMaNV() {
		return MaNV;
	}


	public GDChinh() {
		initComponents();
		list = new CTDangNhap().getListTaiKhoan();
	}
	
	

	private void initComponents() {
		
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		jpCenter.setBackground(Color.WHITE);
		add(jpCenter, BorderLayout.CENTER);

		
		jlbDangNhap = new JLabel("Đăng nhập");
		jlbDangNhap.setBounds(560, 110, 190, 45);
		jlbDangNhap.setFont(new Font("Time New Roman", Font.PLAIN, 36));
		jpCenter.add(jlbDangNhap);
		jlbUser = new JLabel("Tên tài khoản:");
		jlbUser.setBounds(450, 170, 100, 40);
		jpCenter.add(jlbUser);
		jlbPass = new JLabel("Password");
		jlbPass.setBounds(450, 230, 100, 40);
		jpCenter.add(jlbPass);
		jtfUser = new JTextField();
		jtfUser.setBounds(550, 170, 300, 40);
		jpCenter.add(jtfUser);
		jpCenter.add(jlbLoiUser = new JLabel("* Không tìm thấy UserName này."));
		jlbLoiUser.setBounds(550, 212, 300, 13);
		jlbLoiUser.setVisible(false);
		jpCenter.add(jlbLoiUser1 = new JLabel("* Bắt buộc nhập."));
		jlbLoiUser1.setBounds(550, 212, 300, 13);
		jlbLoiUser1.setVisible(false);
		jtfPass = new JPasswordField();
		jtfPass.setBounds(550, 230, 300, 40);
		jpCenter.add(jtfPass);
		jpCenter.add(jlbLoiPass = new JLabel("* Nhập sai password!"));
		jlbLoiPass.setBounds(550, 272, 300, 13);
		jlbLoiPass.setVisible(false);
		jpCenter.add(jlbLoiPass1 = new JLabel("* Bắt buộc nhập."));
		jlbLoiPass1.setBounds(550, 272, 300, 13);
		jlbLoiPass1.setVisible(false);

		btnDangNhap = new JButton("Đăng nhập");
		btnDangNhap.setBounds(450, 310, 400, 30);
		jpCenter.add(btnDangNhap);
		btnThoat = new JButton("Thoát");
		btnThoat.setBounds(450, 350, 400, 30);
		jpCenter.add(btnThoat);
		///
		setTitle("App Quản lí sản phẩm");
		setBounds(-2, 0, 1370, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		/// Xử lý button
		btnDangNhap.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	public static void main(String[] args) {
		GDChinh gd = new GDChinh();
		gd.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnDangNhap) {
			list = new CTDangNhap().getListTaiKhoan();
			TaiKhoan tk = new TaiKhoan();
			tk.setUserName(jtfUser.getText().toString());
			char[] c = jtfPass.getPassword();
			tk.setPassWord(new String(c));
			
			if(!tk.getUserName().equals("") && !tk.getPassWord().equals("")) {
				int demUser=0,flagOK=0;
				for(TaiKhoan _tk : list) {
					if(_tk.getUserName().equals(tk.getUserName()) && _tk.getPassWord().equals(tk.getPassWord())) {
						flagOK++;
						MaNV=_tk.getMaNV();
					}
					else if(_tk.getUserName().equals(tk.getUserName())) demUser++;
				}
				if(flagOK > 0) {
					GDSanPham gd = new GDSanPham(MaNV);
					gd.setVisible(true);
					this.setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(null, "Đăng nhập không thành công!");
					if(demUser==0) {
						jlbLoiUser.setVisible(true);
						jlbLoiPass.setVisible(false);
						jlbLoiUser1.setVisible(false);
						jlbLoiPass1.setVisible(false);
					}
					else {
						jlbLoiUser.setVisible(false);
						jlbLoiPass.setVisible(true);
						jlbLoiUser1.setVisible(false);
						jlbLoiPass1.setVisible(false);
					}
				}
			}
			else if(tk.getUserName().equals("") && !tk.getPassWord().equals("")) {
				jlbLoiUser1.setVisible(true);
				jlbLoiUser.setVisible(false);
				jlbLoiPass.setVisible(false);
				jlbLoiPass1.setVisible(false);
			}
			else if(!tk.getUserName().equals("") && tk.getPassWord().equals("")) {
				jlbLoiPass1.setVisible(true);
				jlbLoiUser.setVisible(false);
				jlbLoiPass.setVisible(false);
				jlbLoiUser1.setVisible(false);
			}
			else {
				jlbLoiUser1.setVisible(true);
				jlbLoiPass1.setVisible(true);
				jlbLoiUser.setVisible(false);
				jlbLoiPass.setVisible(false);
			}
		}
		if (e.getSource() == btnThoat) {
			System.exit(0);
		}
	}
}
