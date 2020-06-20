package nhomHang;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import boundary.GDChinh;
import controller.CTNhaCC;
import controller.CTNhomHang;
import controller.CTSanPham;
import hoaDon.GDHoaDon;
import loaiSanPham.GDLoaiSP;
import loaiSanPham.LoaiSP;
import nhaCungCap.GDNhaCungCap;
import nhaCungCap.GD_SuaNCC;
import nhaCungCap.GD_ThemNCC;
import nhaCungCap.NhaCungCap;
import nhaCungCap.GD_SuaNCC.KqSuaNCC;
import sanPham.GDSanPham;
import sanPham.SanPham;

public class GDNhomHang extends JFrame implements ActionListener{

	JPanel pNorth, pSouth, pCenter, pSouthUp, pSouthDown;
	JButton btnSanPham, btnNhaCC, btnLoai, btnHoaDon,btnNhomHang,btnDangXuat, btnDanhSach;
	JLabel lbNcc, lbTenNCC;
	JTextField jtfTimKiem;
	JButton btnTimKiem, btnThem, btnXoa,btnSua;
	JTable tableDS,tableTK;
	DefaultTableModel modelDS,modelTK;
	JScrollPane scpTableDS,scpTableTK;
	private ArrayList<NhomHang> listDS,listTK;
	private String maNHDuocChon="";
	private String maNV;
	

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public GDNhomHang(String maNV) {
		setMaNV(maNV);
		initComponents();
		listDS = new CTNhomHang().getListNhomHang();
		modelDS = (DefaultTableModel) tableDS.getModel();
		modelDS.setColumnIdentifiers(new Object[] {"STT", "Mã nhóm hàng", "Tên nhóm hàng"});
		///
		modelTK = (DefaultTableModel) tableTK.getModel();
		modelTK.setColumnIdentifiers(new Object[] {"STT", "Mã nhóm hàng", "Tên nhóm hàng"});
		showTable();
	}

	public void showTable() {
		int stt=0;
		for (NhomHang nh : listDS) {
			modelDS.addRow(new Object[] { 
					++stt,nh.getMaNhomHang(),nh.getTenNhomHang()
			});
		}
	}
	private void initComponents() {
		pNorth = new JPanel();
		pNorth.setLayout(null);
		pNorth.setPreferredSize(new Dimension(0,50));
		add(pNorth, BorderLayout.NORTH);

		pCenter = new JPanel();
		pCenter.setLayout(null);
		pCenter.setBackground(Color.WHITE);
		add(pCenter, BorderLayout.CENTER);

		pSouth = new JPanel();
		pSouth.setLayout(null);
		pSouth.setPreferredSize(new Dimension(0, 530));
		pSouth.setBackground(Color.WHITE);
		add(pSouth, BorderLayout.SOUTH);

		pNorth.add(btnSanPham = new JButton("SẢN PHẨM"));
		btnSanPham.setBounds(0, 0, 230, 50);
		pNorth.add(btnNhaCC = new JButton("NHÀ CUNG CẤP"));
		btnNhaCC.setBounds(230, 0, 230, 50);
		pNorth.add(btnLoai = new JButton("LOẠI SẢN PHẨM"));
		btnLoai.setBounds(460, 0, 230, 50);
		pNorth.add(btnNhomHang = new JButton("NHÓM HÀNG"));
		btnNhomHang.setBounds(690, 0, 230, 50);
		btnNhomHang.setBackground(Color.YELLOW);
		pNorth.add(btnHoaDon = new JButton("HÓA ĐƠN"));
		btnHoaDon.setBounds(920, 0, 230, 50);
		pNorth.add(btnDangXuat = new JButton("ĐĂNG XUẤT"));
		btnDangXuat.setBounds(1150, 0, 214, 50);
		
		pCenter.add(lbNcc = new JLabel("Nhóm hàng:"));
		lbNcc.setBounds(10, 10, 100, 30);
		pCenter.add(jtfTimKiem = new JTextField());
		jtfTimKiem.setBounds(110, 10, 200, 30);
		pCenter.add(btnTimKiem = new JButton("Tìm"));
		btnTimKiem.setBounds(310, 10, 150, 30);
		btnTimKiem.setBackground(null);

		pCenter.add(btnThem = new JButton("Thêm"));
		btnThem.setBounds(450, 80, 140, 40);
		btnThem.setBackground(null);
		pCenter.add(btnSua = new JButton("Chỉnh sửa"));
		btnSua.setBounds(615, 80, 140, 40);
		btnSua.setBackground(null);
		pCenter.add(btnXoa = new JButton("Xóa"));
		btnXoa.setBounds(780, 80, 140, 40);
		btnXoa.setBackground(null);
		//

		pSouth.add(btnDanhSach = new JButton("Danh sách nhóm hàng:"));
		btnDanhSach.setBounds(10, 0, 200, 30);
		btnDanhSach.setBackground(Color.YELLOW);

		modelDS = new DefaultTableModel();
		tableDS = new JTable(modelDS) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableDS = new JScrollPane(tableDS));
		scpTableDS.setBounds(0, 31, 1370, 500);
		tableDS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maNHDuocChon=(String) tableDS.getValueAt(tableDS.getSelectedRow(), 1);
			}
		});
		//
		modelTK = new DefaultTableModel();
		tableTK = new JTable(modelTK) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableTK = new JScrollPane(tableTK));
		scpTableTK.setBounds(0, 31, 1370, 500);
		scpTableTK.setVisible(false);
		tableTK.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maNHDuocChon=(String) tableTK.getValueAt(tableTK.getSelectedRow(), 1);
			}
		});
		//
		setTitle("App Quản lí sản phẩm");
		setBounds(-2, 0, 1370, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Sử lý button
		btnSanPham.addActionListener(this);
		btnLoai.addActionListener(this);
		btnNhaCC.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnDanhSach.addActionListener(this);
	}
	public void deleteModel(DefaultTableModel model) {
		for(int i=0;i<model.getRowCount();) {
			model.removeRow(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSanPham) {
			this.setVisible(false);
			GDSanPham gd = new GDSanPham(this.getMaNV());
			gd.setVisible(true);
		}
		if(e.getSource()==btnLoai) {
			this.setVisible(false);
			GDLoaiSP gd = new GDLoaiSP(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnNhaCC) { 
			this.setVisible(false);
			GDNhaCungCap gd = new GDNhaCungCap(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnHoaDon) {
			this.setVisible(false);
			GDHoaDon gd = new GDHoaDon(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnDangXuat) {
			GDChinh gd = new GDChinh();
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnThem) {
			btnThem.setBackground(Color.YELLOW);
			GD_ThemNhomHang gd = new GD_ThemNhomHang();
			gd.setModal(true);
			gd.setVisible(true);
			if(gd.isKqThem()) {
				this.setVisible(false);
				GDNhomHang gd1 =new GDNhomHang(maNV);
				gd1.setVisible(true);
			}
			btnThem.setBackground(null);
		}
		if(e.getSource()==btnTimKiem) {
			if(!jtfTimKiem.getText().equals("")) {
				deleteModel(modelTK);
				int stt1=0;
				listTK= new CTNhomHang().getListNhomHangTK(jtfTimKiem.getText());
				for (NhomHang nh : listTK) {
					modelTK.addRow(new Object[] { 
							++stt1,nh.getMaNhomHang(),nh.getTenNhomHang()
					}); 
				}
				btnTimKiem.setBackground(Color.YELLOW);
				btnDanhSach.setBackground(null);
				btnSua.setBackground(null);
				btnThem.setBackground(null);
				btnXoa.setBackground(null);
				scpTableDS.setVisible(false);
				scpTableTK.setVisible(true);
			}
			
		}
		if(e.getSource()==btnSua) {
			if(!maNHDuocChon.equals("")) {
				btnSua.setBackground(Color.YELLOW);
				GD_SuaNhomHang gd = new GD_SuaNhomHang(new CTNhomHang().getNhomHangTheoMa(maNHDuocChon));
				gd.setModal(true);
				gd.setVisible(true);
				if(gd.isKqSua()) {
					this.setVisible(false);
					GDNhomHang gd1 =new GDNhomHang(maNV);
					gd1.setVisible(true);
				}
				btnSua.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một nhóm hàng.");
		}
		if(e.getSource()==btnXoa) {
			if(!maNHDuocChon.equals("")) {
				btnXoa.setBackground(Color.YELLOW);
				if(new CTNhomHang().xoaNH(maNHDuocChon)) {
					JOptionPane.showMessageDialog(null, "! Đã xóa.");
					this.setVisible(false);
					GDNhomHang gd1 =new GDNhomHang(maNV);
					gd1.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "X xóa không thành công.");
				btnXoa.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một nhóm hàng.");
		}
		if(e.getSource()==btnDanhSach) {
			btnTimKiem.setBackground(null);
			btnDanhSach.setBackground(Color.YELLOW);
			btnSua.setBackground(null);
			btnThem.setBackground(null);
			btnXoa.setBackground(null);
			scpTableDS.setVisible(true);
			scpTableTK.setVisible(false);
		}
	}
}

