package nhaCungCap;

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
import hoaDon.GDHoaDon;
import loaiSanPham.GDLoaiSP;
import loaiSanPham.LoaiSP;
import nhaCungCap.GD_SuaNCC.KqSuaNCC;
import nhaCungCap.NhaCungCap;
import nhomHang.GDNhomHang;
import sanPham.GDSanPham;

public class GDNhaCungCap extends JFrame implements ActionListener{

	JPanel pNorth, pSouth, pCenter, pSouthUp, pSouthDown;
	JButton btnSanPham, btnNhaCC, btnLoai, btnHoaDon,btnNhomHang,btnDangXuat;
	JLabel lbTenNCC;
	JTextField jtfTimKiem;
	JButton btnTimKiem, btnThem, btnXoa,btnSua, btnDanhSach;
	JTable tableDS,tableTK;
	DefaultTableModel modelDS,modelTK;
	JScrollPane scpTableDS,scpTableTK;
	private ArrayList<NhaCungCap> listDS,listTK;
	private String maNCCDuocChon="";
	private String maNV;
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public GDNhaCungCap(String maNV) {
		setMaNV(maNV);
		initComponents();
		listDS = new CTNhaCC().getListNhaCC();
		modelDS = (DefaultTableModel) tableDS.getModel();

		modelDS.setColumnIdentifiers(new Object[] { "STT","Mã NCC", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Email" });
		tableDS.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDS.getColumnModel().getColumn(0).setMaxWidth(50);
		tableDS.getColumnModel().getColumn(1).setMaxWidth(200);
		tableDS.getColumnModel().getColumn(4).setMaxWidth(200);
		showTable();
		//
		modelTK = (DefaultTableModel) tableTK.getModel();

		modelTK.setColumnIdentifiers(new Object[] { "STT","Mã NCC", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại", "Email" });
		tableTK.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableTK.getColumnModel().getColumn(0).setMaxWidth(50);
		tableTK.getColumnModel().getColumn(1).setMaxWidth(200);
		tableTK.getColumnModel().getColumn(4).setMaxWidth(200);
	}

	public void showTable() {
		int stt=0;
		for (NhaCungCap ncc : listDS) {
			modelDS.addRow(new Object[] { 
					++stt,ncc.getMaNCC(),ncc.getTenNCC(),ncc.getDicChiNCC(),ncc.getSdtNCC(),ncc.getEmailNCC()
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
		btnNhaCC.setBackground(Color.YELLOW);
		pNorth.add(btnLoai = new JButton("LOẠI SẢN PHẨM"));
		btnLoai.setBounds(460, 0, 230, 50);
		pNorth.add(btnNhomHang = new JButton("NHÓM HÀNG"));
		btnNhomHang.setBounds(690, 0, 230, 50);
		pNorth.add(btnHoaDon = new JButton("HÓA ĐƠN"));
		btnHoaDon.setBounds(920, 0, 230, 50);
		pNorth.add(btnDangXuat = new JButton("ĐĂNG XUẤT"));
		btnDangXuat.setBounds(1150, 0, 214, 50);
		
		pCenter.add(lbTenNCC = new JLabel("Tên nhà cung cấp:"));
		lbTenNCC.setBounds(10, 10, 130, 30);
		pCenter.add(jtfTimKiem = new JTextField());
		jtfTimKiem.setBounds(140, 10, 200, 30);
		pCenter.add(btnTimKiem = new JButton("Tìm"));
		btnTimKiem.setBounds(340, 10, 150, 30);
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

		pSouth.add(btnDanhSach = new JButton("Danh sách nhà cung cấp:"));
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
				maNCCDuocChon=(String) tableDS.getValueAt(tableDS.getSelectedRow(), 1);
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
				maNCCDuocChon=(String) tableTK.getValueAt(tableTK.getSelectedRow(), 1);
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
		btnHoaDon.addActionListener(this);
		btnNhomHang.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
		btnDanhSach.addActionListener(this);
		
	}
	public void deleteModel(DefaultTableModel model) {
		for(int i=0; i< model.getRowCount();)  model.removeRow(i);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnSanPham) {
			this.setVisible(false);
			GDSanPham gd = new GDSanPham(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnLoai) {
			this.setVisible(false);
			GDLoaiSP gd = new GDLoaiSP(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnNhomHang) {
			this.setVisible(false);
			GDNhomHang gd = new GDNhomHang(this.maNV);
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
		if(e.getSource()==btnTimKiem) {
			if(!jtfTimKiem.getText().equals("")) {
				deleteModel(modelTK);
				listTK=new CTNhaCC().getListNhaCCTK(jtfTimKiem.getText());
				int stt=0;
				for(NhaCungCap ncc : listTK) {
					modelTK.addRow(new Object[]{
						++stt,ncc.getMaNCC(),ncc.getTenNCC(),ncc.getDicChiNCC(),ncc.getSdtNCC(),ncc.getEmailNCC()
					});
				}
				btnDanhSach.setBackground(null);
				btnTimKiem.setBackground(Color.YELLOW);
				scpTableDS.setVisible(false);
				scpTableTK.setVisible(true);
			}
		}
		if(e.getSource()==btnThem) {
			btnThem.setBackground(Color.YELLOW);
			GD_ThemNCC gd = new GD_ThemNCC();
			gd.setModal(true);
			gd.setVisible(true);
			if(gd.isKq()) {
				this.setVisible(false);
				GDNhaCungCap gd1 =new GDNhaCungCap(this.maNV);
				gd1.setVisible(true);
			}
			btnThem.setBackground(null);
		}
		if(e.getSource()==btnSua) {
			if(!maNCCDuocChon.equals("")) {
				btnSua.setBackground(Color.YELLOW);
				GD_SuaNCC gd = new GD_SuaNCC(new CTNhaCC().getListNhaCCTheoMa(maNCCDuocChon));
				gd.setModal(true);
				gd.setVisible(true);
				if(gd.getKq()==KqSuaNCC.OK) {
					this.setVisible(false);
					GDNhaCungCap gd1 =new GDNhaCungCap(this.maNV);
					gd1.setVisible(true);
				}
				btnSua.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp.");
		}
		if(e.getSource()==btnXoa) {
			if(!maNCCDuocChon.equals("")) {
				btnXoa.setBackground(Color.YELLOW);
				if(new CTNhaCC().xoaNCC(new CTNhaCC().getListNhaCCTheoMa(maNCCDuocChon))) {
					JOptionPane.showMessageDialog(null, "! Đã xóa.");
					this.setVisible(false);
					GDNhaCungCap gd1 =new GDNhaCungCap(this.maNV);
					gd1.setVisible(true);
				}
				else JOptionPane.showMessageDialog(null, "X xóa không thành công.");
				btnXoa.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp.");
		}
		if(e.getSource()==btnDanhSach) {
			btnDanhSach.setBackground(Color.YELLOW);
			btnTimKiem.setBackground(null);
			scpTableDS.setVisible(true);
			scpTableTK.setVisible(false);
		}
	}
}

