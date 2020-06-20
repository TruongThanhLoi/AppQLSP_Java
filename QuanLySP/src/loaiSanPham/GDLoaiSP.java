package loaiSanPham;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Array;
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
import controller.CTLoaiSP;
import controller.CTNhaCC;
import controller.CTSanPham;
import hoaDon.GDHoaDon;
import nhaCungCap.GDNhaCungCap;
import nhaCungCap.GD_SuaNCC;
import nhaCungCap.NhaCungCap;
import nhaCungCap.GD_SuaNCC.KqSuaNCC;
import nhomHang.GDNhomHang;
import nhomHang.NhomHang;
import sanPham.GDSanPham;
import sanPham.SanPham;
import sanPham.GDSanPham.ItemHandler;


public class GDLoaiSP extends JFrame implements ActionListener{

	JPanel pNorth, pSouth, pCenter, pSouthUp, pSouthDown;
	JButton btnSanPham, btnNhaCC, btnLoai, btnHoaDon,btnNhomHang,btnDangXuat;
	JComboBox cbNcc, cbLoaiSP, cbNhomHang;
	JLabel lbNcc, lbNhomHang,lb_cbLoaiSP, lbLoaiSP;
	JTextField jtfTimKiem;
	JButton btnTimKiem, btnThem, btnXoa,btnSua,btnDanhSach;
	JTable tableDS,tableTK;
	DefaultTableModel modelDS,modelTK;
	JScrollPane scpTableDS,scpTableTK;
	private ArrayList<LoaiSP> listDS,listTK;
	private ArrayList<NhaCungCap> listNcc = new CTSanPham().getListTenNCC();
	private ArrayList<NhomHang> listNh = new CTSanPham().getListTenNH();
	private ArrayList<LoaiSP> listLsp;
	private String maLoaiSPChon="";
	private ItemHandler handler = new ItemHandler();
	private String maNV;
	
	
	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaLoaiSPChon() {
		return maLoaiSPChon;
	}

	public void setMaLoaiSPChon(String maLoaiSPChon) {
		this.maLoaiSPChon = maLoaiSPChon;
	}
	public void setComboBox() {
		for (NhaCungCap ncc : listNcc) {
			 cbNcc.addItem(ncc.getTenNCC());
		}
		for (NhomHang nh : listNh) {
			 cbNhomHang.addItem(nh.getTenNhomHang());
		}
	}
	public GDLoaiSP(String maNV) {
		setMaNV(maNV);
		initComponents();
		listDS = new CTLoaiSP().getListLoaiSP();
		modelDS= (DefaultTableModel) tableDS.getModel();

		modelDS.setColumnIdentifiers(new Object[] { 
				"STT","Mã Loại SP", "Tên loại sản phẩm", "Đơn vị tính","Tên nhóm hàng", "Tên nhà cung cấp", "Số lượng","Xuất xứ","Thuế"
		});
		tableDS.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDS.getColumnModel().getColumn(0).setMaxWidth(50);
		
		showTable();
		modelTK.setColumnIdentifiers(new Object[] { 
				"STT","Mã Loại SP", "Tên loại sản phẩm", "Đơn vị tính","Tên nhóm hàng", "Tên nhà cung cấp", "Số lượng","Xuất xứ","Thuế"
		});
		tableTK.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableTK.getColumnModel().getColumn(0).setMaxWidth(50);
	}

	public void showTable() {
		int stt=0;
		for (LoaiSP lsp : listDS) {
			modelDS.addRow(new Object[] { 
					++stt,lsp.getMaLoai(),lsp.getTenLoai(),lsp.getDonViTinh(),lsp.getNh().getTenNhomHang(),lsp.getNcc().getTenNCC(),
					lsp.getSoLuong(),lsp.getXuatXu(),lsp.getThue()
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
		btnLoai.setBackground(Color.YELLOW);
		pNorth.add(btnNhomHang = new JButton("NHÓM HÀNG"));
		btnNhomHang.setBounds(690, 0, 230, 50);
		pNorth.add(btnHoaDon = new JButton("HÓA ĐƠN"));
		btnHoaDon.setBounds(920, 0, 230, 50);
		pNorth.add(btnDangXuat = new JButton("ĐĂNG XUẤT"));
		btnDangXuat.setBounds(1150, 0, 214, 50);
		
		pCenter.add(lbNcc = new JLabel("Nhà cung cấp:"));
		lbNcc.setBounds(10, 10, 100, 30);
		pCenter.add(cbNcc = new JComboBox());
		cbNcc.addItem("");
		cbNcc.setBounds(110, 10, 200, 30);
		cbNcc.setBackground(Color.WHITE);
		pCenter.add(lbNhomHang = new JLabel("Nhóm hàng:"));
		lbNhomHang.setBounds(310, 10, 100, 30);
		pCenter.add(cbNhomHang = new JComboBox());
		cbNhomHang.addItem("");
		cbNhomHang.setBounds(410, 10, 200, 30);
		cbNhomHang.setBackground(Color.WHITE);
		cbNhomHang.addItemListener(handler);
		pCenter.add(lb_cbLoaiSP = new JLabel("Loại sản phẩm:"));
		lb_cbLoaiSP.setBounds(610, 10, 100, 30);
		pCenter.add(cbLoaiSP = new JComboBox());
		cbLoaiSP.setBounds(710, 10, 200, 30);
		cbLoaiSP.setBackground(Color.WHITE);
		pCenter.add(btnTimKiem = new JButton("Tìm"));
		btnTimKiem.setBounds(610, 45, 150, 30);
		btnTimKiem.setBackground(null);
		pCenter.add(lbLoaiSP = new JLabel("Tên loại sản phẩm:"));
		lbLoaiSP.setBounds(910, 10, 150, 30);
		pCenter.add(jtfTimKiem = new JTextField());
		jtfTimKiem.setBounds(1060, 10, 200, 30);
		setComboBox();

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

		pSouth.add(btnDanhSach = new JButton("Danh sách loại sản phẩm:"));
		btnDanhSach.setBounds(0, 0, 250, 30);
		btnDanhSach.setBackground(Color.YELLOW);

		modelDS = new DefaultTableModel();
		tableDS = new JTable(modelDS) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableDS = new JScrollPane(tableDS));
		scpTableDS.setBounds(0, 31, 1370, 500);
		scpTableDS.setVisible(true);
		tableDS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maLoaiSPChon=(String) tableDS.getValueAt(tableDS.getSelectedRow(), 1);
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
				maLoaiSPChon=(String) tableTK.getValueAt(tableTK.getSelectedRow(), 1);
				
			}
		});
		//
		setTitle("App Quản lí sản phẩm");
		setBounds(-2, 0, 1370, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Sử lý button
		btnSanPham.addActionListener(this);
		btnNhomHang.addActionListener(this);
		btnNhaCC.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnDanhSach.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	public class ItemHandler implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(e.getSource()==cbNhomHang) {
				cbLoaiSP.removeAllItems();
				cbLoaiSP.addItem("");
				listLsp = new CTSanPham().getListTenLoaiSP(cbNhomHang.getSelectedItem().toString());
				for (LoaiSP lsp : listLsp) {
					 cbLoaiSP.addItem(lsp.getTenLoai());
				}
			}
		}
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
		if(e.getSource()==btnNhomHang) {
			this.setVisible(false);
			GDNhomHang gd = new GDNhomHang(this.maNV);
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
		if(e.getSource()==btnTimKiem) {
			if(!jtfTimKiem.getText().equals("") || !cbNcc.getSelectedItem().toString().equals("") || !cbNhomHang.getSelectedItem().toString().equals("")) {
				deleteModel(modelTK);
				int stt1=0;
				if(!jtfTimKiem.getText().equals("")) {
					listTK= new CTLoaiSP().getListLoaiSPTK(jtfTimKiem.getText());
				}
				else if(!cbNcc.getSelectedItem().toString().equals("") || !cbNhomHang.getSelectedItem().toString().equals("")) {
					
					listTK= new CTLoaiSP().getListLoaiSPTK_ComboBox(cbNcc.getSelectedItem().toString(), cbNhomHang.getSelectedItem().toString(),
							cbLoaiSP.getSelectedItem().toString());
				}
				for (LoaiSP lsp : listTK) {
					modelTK.addRow(new Object[] { 
							++stt1,lsp.getMaLoai(),lsp.getTenLoai(),lsp.getDonViTinh(),lsp.getNh().getTenNhomHang(),lsp.getNcc().getTenNCC(),
							lsp.getSoLuong(),lsp.getXuatXu(),lsp.getThue()
					}); 
				}
				btnTimKiem.setBackground(Color.YELLOW);
				btnDanhSach.setBackground(null);
				btnThem.setBackground(null);
				btnSua.setBackground(null);
				btnXoa.setBackground(null);
				scpTableDS.setVisible(false);
				scpTableTK.setVisible(true);
			}
		}
		if(e.getSource()==btnThem) {
			btnThem.setBackground(Color.YELLOW);
			GD_ThemLoaiSP gd = new GD_ThemLoaiSP();
			gd.setModal(true);
			gd.setVisible(true);
			if(gd.isKqThem()) {
				this.setVisible(false);
				GDLoaiSP gd1 =new GDLoaiSP(this.maNV);
				gd1.setVisible(true);
			}
			btnThem.setBackground(null);
		}
		if(e.getSource()==btnSua) {
			if(!maLoaiSPChon.equals("")) {
				btnSua.setBackground(Color.YELLOW);
				GD_SuaLoaiSP gd = new GD_SuaLoaiSP(maLoaiSPChon);
				gd.setModal(true);
				gd.setVisible(true);
				if(gd.isKqSua()) {
					this.setVisible(false);
					GDLoaiSP gd1 =new GDLoaiSP(this.maNV);
					gd1.setVisible(true);
				}
				btnSua.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một Loại sản phẩm");
		}
		if(e.getSource()==btnXoa) {
			if(!maLoaiSPChon.equals("")) {
				if(new CTLoaiSP().xoaLoaiSP(maLoaiSPChon)) {
					this.setVisible(false);
					GDLoaiSP gd1 =new GDLoaiSP(this.maNV);
					gd1.setVisible(true);
				}else JOptionPane.showMessageDialog(null, "Không xoá được.");
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một Loại sản phẩm");
		}
		if(e.getSource()==btnDanhSach) {
			btnTimKiem.setBackground(null);
			btnDanhSach.setBackground(Color.YELLOW);
			btnThem.setBackground(null);
			btnSua.setBackground(null);
			btnXoa.setBackground(null);
			scpTableDS.setVisible(true);
			scpTableTK.setVisible(false);
		}
	}

}

