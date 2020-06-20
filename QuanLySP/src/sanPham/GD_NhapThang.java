package sanPham;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class GD_NhapThang extends JDialog implements ActionListener{
	
	JPanel jpCenter;
	JLabel jlbNhapThang,jlbNhapNam;
	JTextField jtfNhapThang,jtfNhapNam;

	JButton btnOK,btnHienTai,btnTruoc,btnSau;
	private NhapThang kqNhap;
	public enum NhapThang {
		TRUOC, HIENTAI,SAU,NHAP,LOI
	}
	
	public NhapThang getKqNhap() {
		return kqNhap;
	}
	public void setKqNhap(NhapThang kqNhap) {
		this.kqNhap = kqNhap;
	}
	public GD_NhapThang() {
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		
		jlbNhapThang= new JLabel("Nhập tháng:");
		jlbNhapThang.setBounds(10, 10, 130, 30);
		jpCenter.add(jlbNhapThang);
		jlbNhapNam= new JLabel("Nhập năm:");
		jlbNhapNam.setBounds(10, 50, 130, 30);
		jpCenter.add(jlbNhapNam);
		
		jtfNhapThang=new JTextField();
		jtfNhapThang.setBounds(131, 10,290 , 30);
		jpCenter.add(jtfNhapThang);
		jtfNhapNam=new JTextField();
		jtfNhapNam.setBounds(131, 50,290 , 30);
		jpCenter.add(jtfNhapNam);
		
		btnTruoc=new JButton("<< Trước");
		btnTruoc.setBounds(10, 100, 90, 30);
		jpCenter.add(btnTruoc);
		btnHienTai=new JButton("Hiện tại");
		btnHienTai.setBounds(90, 100, 90, 30);
		jpCenter.add(btnHienTai);
		btnSau=new JButton("Sau >>");
		btnSau.setBounds(170, 100, 90, 30);
		jpCenter.add(btnSau);
		btnOK=new JButton("Tìm");
		btnOK.setBounds(330, 100, 100, 30);
		jpCenter.add(btnOK);
		//
		setTitle("Tìm hạn sử dụng");
		setBounds(485, 310,450, 180);
		setResizable(false);
		setLocationRelativeTo(null);
		
		//Xử lý button
		btnTruoc.addActionListener(this);
		btnHienTai.addActionListener(this);
		btnSau.addActionListener(this);
		btnOK.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnTruoc) {
			kqNhap= NhapThang.TRUOC;
			this.setVisible(false);
		}
		if(e.getSource()==btnHienTai) {
			kqNhap= NhapThang.HIENTAI;
			this.setVisible(false);
		}
		if(e.getSource()==btnSau) {
			kqNhap= NhapThang.SAU;
			this.setVisible(false);
		}
		if(e.getSource()==btnOK) {
			if(jtfNhapThang.getText().equals("") || jtfNhapNam.getText().equals("")) 
				kqNhap = NhapThang.LOI;
			else {
				kqNhap= NhapThang.NHAP;
				this.setVisible(false);
			}
		}
		
	}
}
