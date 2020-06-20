package hoaDon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.CTHoaDon;
import controller.CTLoaiSP;
import loaiSanPham.LoaiSP;
import nhanVien.NhanVien;
import sanPham.SanPham;

public class GD_HoaDonXuat extends JDialog implements ActionListener{
	
	JPanel jpCenter,jpNorth,jpSouth;
	JTable table;
	DefaultTableModel model;
	JScrollPane scpTable;
	JLabel jlbMaNV,jlbMaHD,jlbNgayLapHD,jlbMaLoaiSP,jlbSoLuong,jlbTongTien;
	JTextField jtfMaLoaiSP,jtfSoLuong;
	JComboBox<String> jcbMaNV;

	JButton btnThem,btnHuy,btnXong;
	private ArrayList<CTHD> listCTHD=new ArrayList<CTHD>();
	private HoaDon hd=new HoaDon();
	String maHD="HDX";
	double tongTien=0;
	Locale local=new Locale("vi", "vn");
	NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
	public GD_HoaDonXuat(String maNV) {
		hd.setNhanVien(new NhanVien(maNV));
		hd.setNgayLapHD(Date.valueOf(LocalDate.now()));
		jpNorth =new JPanel();
		jpNorth.setLayout(null);
		jpNorth.setPreferredSize(new Dimension(0,30));;
		add(jpNorth,BorderLayout.NORTH);
		jpSouth =new JPanel();
		jpSouth.setLayout(null);
		jpSouth.setPreferredSize(new Dimension(0,70));;
		add(jpSouth,BorderLayout.SOUTH);
		jpCenter = new JPanel();
		jpCenter.setLayout(null);
		add(jpCenter, BorderLayout.CENTER);
		
		jpNorth.add(jlbMaNV = new JLabel("Mã Nhân Viên: "+maNV));
		jlbMaNV.setBounds(0, 0, 130, 30);
		int slHD=new CTHoaDon().getLaySLHDX()+1;
		
		if(slHD<10) maHD+="000"+slHD;
		else if(slHD<100) maHD+="00"+slHD;
		else if(slHD<1000) maHD+="0"+slHD;
		else maHD+=slHD+"";
		jpNorth.add(jlbMaHD=new JLabel("Mã hóa đơn: "+maHD));
		jlbMaHD.setBounds(250, 0, 150, 30);
		jpNorth.add(jlbNgayLapHD=new JLabel("Ngày lập: "+LocalDate.now()));
		jlbNgayLapHD.setBounds(400, 0, 150, 30);
		hd.setMaHD(maHD);
//		
		model=new DefaultTableModel();
		model.setColumnIdentifiers(new Object[] {
				"Mã SP","SoLuong", "GiaBan","Thành tiền"
		});
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		jpCenter.add(scpTable=new JScrollPane(table));
		scpTable.setBounds(0, 0, 550, 250);
		

		for (CTHD cthd : listCTHD) {
			tongTien+=cthd.getTongTien();
		}
		jpCenter.add(jlbTongTien=new JLabel());
		jlbTongTien.setText("Tổng tiền thu:     "+ formatter.format(tongTien));
		jlbTongTien.setBounds(290, 251, 250, 30);
		jpCenter.add(jlbMaLoaiSP=new JLabel("Mã sản phẩm:"));
		jlbMaLoaiSP.setBounds(10, 310, 90, 30);
		jpCenter.add(jlbSoLuong=new JLabel("Số lượng:"));
		jlbSoLuong.setBounds(300, 310, 90, 30);		
		jpCenter.add(jtfMaLoaiSP=new JTextField());
		jtfMaLoaiSP.setBounds(100, 310,150 , 30);
		jpCenter.add(jtfSoLuong=new JTextField());
		jtfSoLuong.setBounds(390, 310,150 , 30);
		
		jpSouth.add(btnThem=new JButton("Thêm"));
		btnThem.setBounds(20,20, 100, 40);
		jpSouth.add(btnHuy=new JButton("Hủy"));
		btnHuy.setBounds(320, 20, 100, 40);
		jpSouth.add(btnXong=new JButton("Xong"));
		btnXong.setBounds(430, 20, 100, 40);
		
//		//
		setTitle("Lập hóa đơn nhập");
		setBounds(425, 150,550, 500);
		setResizable(false);
		setLocationRelativeTo(null);

		//Xử lý button
		btnThem.addActionListener(this);
		btnHuy.addActionListener(this);
		btnXong.addActionListener(this);
	}
	public boolean kiemTraLoaiSP(String maLoaiSP) {
		for(LoaiSP lsp : new CTLoaiSP().getListLoaiSP()) {
			if(lsp.getMaLoai().equals(maLoaiSP)) return true;
		}
		return false;
	}
	//kiểm tra trùng mã trong list
	public boolean trungMaTrongList(String maLoaiSP) {
		for (CTHD cthd : listCTHD) {
			if(cthd.getLsp().getMaLoai().equals(maLoaiSP)) return true;
		}return false;
	}
	///Thêm chi tiết hóa đơn
	public boolean themCTHD(ArrayList<CTHD> listCTHD) {
		for (CTHD cthd : listCTHD) {
			if(!new CTHoaDon().themCTHDXuat(cthd))
				return false;
		}return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnThem) {
			if(!jtfMaLoaiSP.getText().equals("") && !jtfSoLuong.getText().equals("")) {
				if(kiemTraLoaiSP(jtfMaLoaiSP.getText())) {
					if(!trungMaTrongList(jtfMaLoaiSP.getText())) {
						int sl=0;
						try {
							sl=Integer.parseInt(jtfSoLuong.getText());
							
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "X Nhập số lượng đàng hoàng.");
						}
						if(sl>0) {
							CTHD cthd = new CTHD();
							LoaiSP lsp =new LoaiSP();
							cthd.setMaHD(maHD);
							lsp=new CTLoaiSP().getLoaiSPTheoMa(jtfMaLoaiSP.getText());
							cthd.setLsp(lsp);
							cthd.setSoLuong(sl);
							listCTHD.add(cthd);
							
							model.addRow(new Object[] { 
								cthd.getLsp().getTenLoai(),cthd.getSoLuong(),cthd.getLsp().getDonGiaBan(),formatter.format(cthd.getLsp().getDonGiaBan()*cthd.getSoLuong())
							});
							jtfMaLoaiSP.setText("");
							jtfSoLuong.setText("");
							tongTien+=cthd.getLsp().getDonGiaBan()*cthd.getSoLuong();
							jlbTongTien.setText("Tổng tiền thu:     "+ formatter.format(tongTien));
						}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập số lượng > 0.");
					}else JOptionPane.showMessageDialog(null, "X Đã có mã loại sản phẩm này trong hóa đơn.");
				}else JOptionPane.showMessageDialog(null, "X Chưa có mã loại sản phẩm này.");
			}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập mã loại sp và số lượng.");
		}
		if(e.getSource()==btnHuy) {
			this.setVisible(false);
		}
		if(e.getSource()==btnXong) {
			if(listCTHD.size()>0) {
				if(new CTHoaDon().themHoaDonXuat(hd) && themCTHD(listCTHD)) {
					JOptionPane.showMessageDialog(null, "! Đã  thêm một hóa đơn.");
					GDHoaDon gd = new GDHoaDon(hd.getNhanVien().getMaNV());
					this.setVisible(false);
					gd.setVisible(true);
				}else JOptionPane.showMessageDialog(null, "X Không thể tạo hóa đơn này.");
			}else JOptionPane.showMessageDialog(null, "X Chưa có đơn hàng nào.");
		}
	}
}


