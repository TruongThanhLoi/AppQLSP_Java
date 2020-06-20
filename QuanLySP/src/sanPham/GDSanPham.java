package sanPham;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
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
import controller.CTHoaDon;
import controller.CTSanPham;
import hoaDon.GDHoaDon;
import loaiSanPham.GDLoaiSP;
import loaiSanPham.LoaiSP;
import nhaCungCap.GDNhaCungCap;
import nhaCungCap.NhaCungCap;
import nhomHang.GDNhomHang;
import nhomHang.NhomHang;
import sanPham.GD_NhapThang.NhapThang;
import sanPham.GD_SuaSP.KqSua;

public class GDSanPham extends JFrame implements ActionListener{
	JPanel pNorth, pSouth, pCenter, pSouthUp, pSouthDown;
	JButton btnSanPham, btnNhaCC, btnLoai, btnHoaDon,btnNhomHang,btnDangXuat;
	JComboBox<String> cbNcc, cbNhomHang, cbLoaiSP;
	JLabel lbNcc, lbNhomHang, lbLoaiSP,lbTenSP;
	JTextField jtfTimKiem;
	JButton btnTimKiem, btnSua, btnSPLoi,btnSPHetHan, btnSPHHThang, btnDanhSach;
	JTable tableDS,tableDsLoi,tableDsHH,tableTK,tableHHThang;
	DefaultTableModel modelDS,modelDsLoi,modelDsHH,modelTK,modelHHThang;
	JScrollPane scpTableDS,scpTableDsLoi,scpTableDsHH,scpTableTK,scpTableHHThang;
	private ArrayList<SanPham> listspDS= new CTSanPham().getListSanPham();
	private ArrayList<SanPham> listspDsLoi= new CTSanPham().getListSanPhamLoi();
	private ArrayList<SanPham> listspDsHH= new CTSanPham().getListSanPhamHH();
	private ArrayList<SanPham> listspTK,listspHHThang;
	private ArrayList<NhaCungCap> listNcc = new CTSanPham().getListTenNCC();
	private ArrayList<NhomHang> listNh = new CTSanPham().getListTenNH();
	private ArrayList<LoaiSP> listLsp;
	private ItemHandler handler = new ItemHandler();
	private String maSPDuocChon="";
	private String maNV;
	Locale local=new Locale("vi", "vn");
	NumberFormat formatter = NumberFormat.getCurrencyInstance(local);
	

	

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public GDSanPham(String maNV) {
		setMaNV(maNV);
		initComponents();
		modelDS = (DefaultTableModel) tableDS.getModel();
		modelDS.setColumnIdentifiers(new Object[] {
				"STT","Mã SP","Tên Loại SP","Ngày nhập","Đơn giá nhập","Đơn giá bán", "Trạng thái lỗi","Ngày hết hạn","Hạn bảo hành"
		});
		tableDS.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDS.getColumnModel().getColumn(0).setMaxWidth(50);
		////
		modelDsLoi = (DefaultTableModel) tableDsLoi.getModel();
		modelDsLoi.setColumnIdentifiers(new Object[] {
				"STT","Mã SP","Tên Loại SP","Ngày nhập","Đơn giá nhập","Đơn giá bán", "Trạng thái lỗi","Ngày hết hạn","Hạn bảo hành"
		});
		tableDsLoi.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDsLoi.getColumnModel().getColumn(0).setMaxWidth(50);
		///
		modelDsHH = (DefaultTableModel) tableDsHH.getModel();
		modelDsHH.setColumnIdentifiers(new Object[] {
				"STT","Mã SP","Tên Loại SP","Ngày nhập","Đơn giá nhập","Đơn giá bán", "Trạng thái lỗi","Ngày hết hạn","Hạn bảo hành"
		});
		tableDsHH.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableDsHH.getColumnModel().getColumn(0).setMaxWidth(50);
		///
		modelTK = (DefaultTableModel) tableTK.getModel();
		modelTK.setColumnIdentifiers(new Object[] {
				"STT","Mã SP","Tên Loại SP","Ngày nhập","Đơn giá nhập","Đơn giá bán", "Trạng thái lỗi","Ngày hết hạn","Hạn bảo hành"
		});
		tableTK.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableTK.getColumnModel().getColumn(0).setMaxWidth(50);
		////
		modelHHThang = (DefaultTableModel) tableHHThang.getModel();
		modelHHThang.setColumnIdentifiers(new Object[] {
				"STT","Mã SP","Tên Loại SP","Ngày nhập","Đơn giá nhập","Đơn giá bán", "Trạng thái lỗi","Ngày hết hạn","Hạn bảo hành"
		});
		tableHHThang.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
		tableHHThang.getColumnModel().getColumn(0).setMaxWidth(50);
		////
		showTable();
	}

	public void showTable() {
		int stt=0;
		for (SanPham sp : listspDS) {
			modelDS.addRow(new Object[] { 
					++stt,sp.getMaSP(),sp.getLoai().getTenLoai(),sp.getNgayNhap(),formatter.format(sp.getDonGiaNhap()),formatter.format(sp.getLoai().getDonGiaBan()),sp.getTrangThaiLoi(),
					sp.getHanSuDung(),sp.getHanBaoHanh()
			}); 
		}
		int stt1=0;
		for (SanPham sp : listspDsLoi) {
			modelDsLoi.addRow(new Object[] { 
					++stt1,sp.getMaSP(),sp.getLoai().getTenLoai(),sp.getNgayNhap(),formatter.format(sp.getDonGiaNhap()),formatter.format(sp.getLoai().getDonGiaBan()),sp.getTrangThaiLoi(),
					sp.getHanSuDung(),sp.getHanBaoHanh()
			}); 
		}
		int stt2=0;
		for (SanPham sp : listspDsHH) {
			modelDsHH.addRow(new Object[] { 
					++stt2,sp.getMaSP(),sp.getLoai().getTenLoai(),sp.getNgayNhap(),formatter.format(sp.getDonGiaNhap()),formatter.format(sp.getLoai().getDonGiaBan()),sp.getTrangThaiLoi(),
					sp.getHanSuDung(),sp.getHanBaoHanh()
			}); 
		}
	}
	
	public void setComboBox() {
		for (NhaCungCap ncc : listNcc) {
			 cbNcc.addItem(ncc.getTenNCC());
		}
		for (NhomHang nh : listNh) {
			 cbNhomHang.addItem(nh.getTenNhomHang());
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
		btnSanPham.setBackground(Color.YELLOW);
		pNorth.add(btnNhaCC = new JButton("NHÀ CUNG CẤP"));
		btnNhaCC.setBounds(230, 0, 230, 50);
		pNorth.add(btnLoai = new JButton("LOẠI SẢN PHẨM"));
		btnLoai.setBounds(460, 0, 230, 50);
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
		cbNcc.setBounds(110, 10, 150, 30);
		cbNcc.setBackground(Color.WHITE);
		pCenter.add(lbNhomHang = new JLabel("Nhóm hàng:"));
		lbNhomHang.setBounds(260, 10, 100, 30);
		pCenter.add(cbNhomHang = new JComboBox());
		cbNhomHang.addItem("");
		cbNhomHang.setBounds(340, 10, 150, 30);
		cbNhomHang.setBackground(Color.WHITE);
		cbNhomHang.addItemListener(handler);
		pCenter.add(lbLoaiSP = new JLabel("Loại sản phẩm:"));
		lbLoaiSP.setBounds(490, 10, 100, 30);
		pCenter.add(cbLoaiSP = new JComboBox());
		cbLoaiSP.setBounds(590, 10, 150, 30);
		cbLoaiSP.setBackground(Color.WHITE);
		
		pCenter.add(lbTenSP = new JLabel("Tên loại sản phẩm:"));
		lbTenSP.setBounds(740, 10, 120, 30);
		pCenter.add(jtfTimKiem = new JTextField());
		jtfTimKiem.setBounds(860, 10,150, 30);
		pCenter.add(btnTimKiem = new JButton("Tìm"));
		btnTimKiem.setBounds(590, 45, 150, 30);
		btnTimKiem.setBackground(null);
		setComboBox();

		pCenter.add(btnSua = new JButton("Chỉnh sửa"));
		btnSua.setBounds(405, 80, 270, 40);
		btnSua.setBackground(null);
		pCenter.add(btnSPHHThang = new JButton("Sản phẩm hết hạn theo tháng"));
		btnSPHHThang.setBounds(700, 80, 265, 40);
		btnSPHHThang.setBackground(null);
		//

		pSouth.add(btnDanhSach = new JButton("Danh sách sản phẩm:"));
		btnDanhSach.setBounds(0, 0, 200, 30);
		btnDanhSach.setBackground(Color.YELLOW);
		pSouth.add(btnSPLoi = new JButton("Danh sách sản phẩm lỗi:"));
		btnSPLoi.setBounds(200, 0, 200, 30);
		btnSPLoi.setBackground(null);
		pSouth.add(btnSPHetHan = new JButton("Danh sách sản phẩm hết hạn:"));
		btnSPHetHan.setBounds(400, 0, 220, 30);
		btnSPHetHan.setBackground(null);
		
		modelDS = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columnIndex) {
				if(columnIndex==0) return Integer.class;
				else return String.class;
			}
		};
		tableDS = new JTable(modelDS) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableDS = new JScrollPane(tableDS));
		scpTableDS.setBounds(0, 31, 1370, 500);
		//
		modelDsLoi = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columnIndex) {
				if(columnIndex==0) return Integer.class;
				else return String.class;
			}
		};
		
		tableDsLoi = new JTable(modelDsLoi) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableDsLoi = new JScrollPane(tableDsLoi));
		scpTableDsLoi.setBounds(0, 31, 1370, 500);
		scpTableDsLoi.setVisible(false);

		//
		modelDsHH = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columnIndex) {
				if(columnIndex==0) return Integer.class;
				else return String.class;
			}
		};
		tableDsHH = new JTable(modelDsHH) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableDsHH = new JScrollPane(tableDsHH));
		scpTableDsHH.setBounds(0, 31, 1370, 500);
		//
		modelTK = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columnIndex) {
				if(columnIndex==0) return Integer.class;
				else return String.class;
			}
		};
		tableTK = new JTable(modelTK) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableTK = new JScrollPane(tableTK));
		scpTableTK.setBounds(0, 31, 1370, 500);
		scpTableTK.setVisible(false);
		//
		modelHHThang = new DefaultTableModel() {
			@Override
			public Class getColumnClass(int columnIndex) {
				if(columnIndex==0) return Integer.class;
				else return String.class;
			}
		};
		tableHHThang = new JTable(modelHHThang) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableHHThang = new JScrollPane(tableHHThang));
		scpTableHHThang.setBounds(0, 31, 1370, 500);
		scpTableHHThang.setVisible(false);
		/////
		tableDS.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maSPDuocChon=(String) tableDS.getValueAt(tableDS.getSelectedRow(), 1);
			}
		});
		tableDsLoi.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maSPDuocChon=(String) tableDsLoi.getValueAt(tableDsLoi.getSelectedRow(), 1);
			}
		});
		tableDsHH.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maSPDuocChon=(String) tableDsHH.getValueAt(tableDsHH.getSelectedRow(), 1);
			}
		});
		tableTK.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maSPDuocChon=(String) tableTK.getValueAt(tableTK.getSelectedRow(), 1);
			}
		});
		tableHHThang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maSPDuocChon=(String) tableHHThang.getValueAt(tableHHThang.getSelectedRow(), 1);
			}
		});
		///
		setTitle("App Quản lí sản phẩm");
		setBounds(-2, 0, 1370, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Sử lý button
		btnNhaCC.addActionListener(this);
		btnLoai.addActionListener(this);
		btnHoaDon.addActionListener(this);
		btnNhomHang.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnDanhSach.addActionListener(this);
		btnSPLoi.addActionListener(this);
		btnSPHetHan.addActionListener(this);
		btnSPHHThang.addActionListener(this);
		btnSua.addActionListener(this);
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
		for(int i=0;i<model.getRowCount();) {
			model.removeRow(i);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNhaCC) {
			GDNhaCungCap gd = new GDNhaCungCap(this.maNV);
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnLoai) {
			GDLoaiSP gd = new GDLoaiSP(this.maNV);
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnHoaDon) {
			GDHoaDon gd = new GDHoaDon(this.maNV);
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnNhomHang) {
			GDNhomHang gd = new GDNhomHang(this.getMaNV());
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnDangXuat) {
			GDChinh gd = new GDChinh();
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnTimKiem) {
			if(!jtfTimKiem.getText().equals("") || !cbNcc.getSelectedItem().toString().equals("") || !cbNhomHang.getSelectedItem().toString().equals("")) {
				deleteModel(modelTK);
				int stt3=0;
				if(!jtfTimKiem.getText().equals("")) {
					listspTK= new CTSanPham().getListSanPhamTK(jtfTimKiem.getText());
				}
				else if(!cbNcc.getSelectedItem().toString().equals("") || !cbNhomHang.getSelectedItem().toString().equals("")) {
					
					listspTK= new CTSanPham().getListSanPhamTK_ComboBox(cbNcc.getSelectedItem().toString(), cbNhomHang.getSelectedItem().toString(),
							cbLoaiSP.getSelectedItem().toString());
				}
				for (SanPham sp : listspTK) {
					modelTK.addRow(new Object[] { 
							++stt3,sp.getMaSP(),sp.getLoai().getTenLoai(),sp.getNgayNhap(),formatter.format(sp.getDonGiaNhap()),formatter.format(sp.getLoai().getDonGiaBan()),sp.getTrangThaiLoi(),
							sp.getHanSuDung(),sp.getHanBaoHanh()
					}); 
				}
				btnTimKiem.setBackground(Color.YELLOW);
				btnSPHHThang.setBackground(null);
				btnDanhSach.setBackground(null);
				btnSPLoi.setBackground(null);
				btnSPHetHan.setBackground(null);
				scpTableDS.setVisible(false);
				scpTableDsLoi.setVisible(false);
				scpTableDsHH.setVisible(false);
				scpTableTK.setVisible(true);
				scpTableHHThang.setVisible(false);
			}
		}
		if(e.getSource()==btnSua) {
			if(!maSPDuocChon.equals("")) {
				btnSua.setBackground(Color.YELLOW);
				GD_SuaSP gd = new GD_SuaSP(maSPDuocChon);
				gd.setModal(true);
				gd.setVisible(true);
				if(gd.getKqSua()==KqSua.OK) {
					this.setVisible(false);
					GDSanPham gdSua = new GDSanPham(maNV);
					gdSua.setVisible(true);
				}
				btnSua.setBackground(null);
			}else JOptionPane.showMessageDialog(null, "Hãy chọn một sản phẩm");
		}
		if(e.getSource()==btnDanhSach) {
			btnTimKiem.setBackground(null);
			btnSPHHThang.setBackground(null);
			btnDanhSach.setBackground(Color.YELLOW);
			btnSPLoi.setBackground(null);
			btnSPHetHan.setBackground(null);
			scpTableDS.setVisible(true);
			scpTableDsLoi.setVisible(false);
			scpTableDsHH.setVisible(false);
			scpTableTK.setVisible(false);
			scpTableHHThang.setVisible(false);
		}
		if(e.getSource()==btnSPLoi) {
			btnTimKiem.setBackground(null);
			btnSPHHThang.setBackground(null);
			btnDanhSach.setBackground(null);
			btnSPLoi.setBackground(Color.YELLOW);
			btnSPHetHan.setBackground(null);
			scpTableDS.setVisible(false);
			scpTableDsLoi.setVisible(true);
			scpTableDsHH.setVisible(false);
			scpTableTK.setVisible(false);
			scpTableHHThang.setVisible(false);
		}
		if(e.getSource()==btnSPHetHan) {
			btnTimKiem.setBackground(null);
			btnSPHHThang.setBackground(null);
			btnDanhSach.setBackground(null);
			btnSPLoi.setBackground(null);
			btnSPHetHan.setBackground(Color.YELLOW);
			scpTableDS.setVisible(false);
			scpTableDsLoi.setVisible(false);
			scpTableDsHH.setVisible(true);
			scpTableTK.setVisible(false);
			scpTableHHThang.setVisible(false);
		}
		if(e.getSource()==btnSPHHThang) {
			btnSPHHThang.setBackground(Color.YELLOW);
			GD_NhapThang gd = new GD_NhapThang();
			gd.setModal(true);
			gd.setVisible(true);
		
			if(gd.getKqNhap()!=NhapThang.LOI) {
				deleteModel(modelHHThang);
				int stt4=0;
				if(gd.getKqNhap()==NhapThang.TRUOC) {
					if(LocalDate.now().getMonthValue()==1) {
						listspHHThang= new CTSanPham().getListSanPhamHHThang(12,LocalDate.now().getYear()-1);
					}
					else listspHHThang= new CTSanPham().getListSanPhamHHThang(LocalDate.now().getMonthValue()-1,LocalDate.now().getYear());
				}
				else if(gd.getKqNhap()==NhapThang.HIENTAI) {
					listspHHThang= new CTSanPham().getListSanPhamHHThang(LocalDate.now().getMonthValue(),LocalDate.now().getYear());
				}
				else if(gd.getKqNhap()==NhapThang.SAU) {
					if(LocalDate.now().getMonthValue()==12) {
						listspHHThang= new CTSanPham().getListSanPhamHHThang(1,LocalDate.now().getYear()+1);
					}
					else listspHHThang= new CTSanPham().getListSanPhamHHThang(LocalDate.now().getMonthValue()+1,LocalDate.now().getYear());
				}
				else if(gd.getKqNhap()==NhapThang.NHAP) 
					
					try {
						listspHHThang= new CTSanPham().getListSanPhamHHThang(Integer.parseInt(gd.jtfNhapThang.getText()),Integer.parseInt(gd.jtfNhapNam.getText()));
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Xin hãy nhập đàng hoàng!");
					}
				for (SanPham sp : listspHHThang) {
					modelHHThang.addRow(new Object[] { 
							++stt4,sp.getMaSP(),sp.getLoai().getTenLoai(),sp.getNgayNhap(),formatter.format(sp.getDonGiaNhap()),formatter.format(sp.getLoai().getDonGiaBan()),sp.getTrangThaiLoi(),
							sp.getHanSuDung(),sp.getHanBaoHanh()
					}); 
				}
				btnTimKiem.setBackground(null);
				btnSPHHThang.setBackground(Color.YELLOW);
				btnDanhSach.setBackground(null);
				btnSPLoi.setBackground(null);
				btnSPHetHan.setBackground(null);
				scpTableDS.setVisible(false);
				scpTableDsLoi.setVisible(false);
				scpTableDsHH.setVisible(false);
				scpTableTK.setVisible(false);
				scpTableHHThang.setVisible(true);
			}else 
				btnSPHHThang.setBackground(null);
		}
	}
}
