package hoaDon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
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
import loaiSanPham.GDLoaiSP;
import nhaCungCap.GDNhaCungCap;
import nhomHang.GDNhomHang;
import sanPham.GDSanPham;

public class GDHoaDon extends JFrame implements ActionListener {

	JPanel pNorth, pSouth, pCenter, pSouthUp, pSouthDown;
	JButton btnSanPham, btnNhaCC, btnLoai, btnHoaDon,btnNhomHang,btnDangXuat;
	JComboBox cbNcc, cbLoaiSp, cbSp;
	JLabel lbHoaDon, lbNgayLap;
	JTextField jtfMaHD,jtfNgayLap;
	JButton btnTimKiem, btnLapHDN, btnLapHDX, btnDanhSachHD,btnDanhSachCT;
	JTable tableHD,tableTK,tableCT;
	DefaultTableModel modelHD,modelTK,modelCT;
	JScrollPane scpTableHD,scpTableTK,scpTableCT;
	private ArrayList<HoaDon> listHD,listTK;
	private ArrayList<CTHD> listCT;
	private String maHDDuocChon="";
	private String maNV;
	Locale local=new Locale("vi", "vn");
	NumberFormat formatter = NumberFormat.getCurrencyInstance(local);

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public GDHoaDon(String maNV) {
		setMaNV(maNV);
		initComponents();
		listHD = new CTHoaDon().getListHoaDon();
		modelHD = (DefaultTableModel) tableHD.getModel();
		modelHD.setColumnIdentifiers(new Object[] {
				"STT","Mã hóa đơn","Mã nhân viên","Ngày lập","Tổng tiền"
		});
		modelTK = (DefaultTableModel) tableTK.getModel();
		modelTK.setColumnIdentifiers(new Object[] {
				"STT","Mã hóa đơn","Mã nhân viên","Ngày lập","Tổng tiền"
		});
		listCT=new CTHoaDon().getListCTHD();
		modelCT = (DefaultTableModel) tableCT.getModel();

		modelCT.setColumnIdentifiers(new Object[] {
				"STT","Mã hóa đơn","Mã Loại sản phẩm","Số lượng","Giá bán","Thành tiền"
		});
		showTable();
	}

	public void showTable() {
		int stt=0;
		for (HoaDon hd : listHD) {
			modelHD.addRow(new Object[] { 
					++stt,hd.getMaHD(), hd.getNhanVien().getMaNV(), hd.getNgayLapHD(),formatter.format(hd.getTongTien())
			}); 
		}
		int stt1=0;
		for (CTHD ct : listCT) {
			modelCT.addRow(new Object[] { 
					++stt1,ct.getMaHD(),ct.getLsp().getMaLoai(),ct.getSoLuong(),formatter.format(ct.getLsp().getDonGiaBan()),formatter.format(ct.getTongTien())
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
		pNorth.add(btnHoaDon = new JButton("HÓA ĐƠN"));
		btnHoaDon.setBounds(920, 0, 230, 50);
		btnHoaDon.setBackground(Color.YELLOW);
		pNorth.add(btnDangXuat = new JButton("ĐĂNG XUẤT"));
		btnDangXuat.setBounds(1150, 0, 214, 50);
		
		pCenter.add(lbHoaDon = new JLabel("Mã hóa đơn:"));
		lbHoaDon.setBounds(10, 10, 100, 30);
		pCenter.add(jtfMaHD = new JTextField());
		jtfMaHD.setBounds(110, 10, 200, 30);
		pCenter.add(lbNgayLap = new JLabel("Ngày lập:"));
		lbNgayLap.setBounds(310, 10, 100, 30);
		pCenter.add(jtfNgayLap = new JTextField());
		jtfNgayLap.setBounds(410, 10, 200, 30);
		pCenter.add(btnTimKiem = new JButton("Tìm"));
		btnTimKiem.setBounds(610, 10, 150, 30);
		btnTimKiem.setBackground(null);

		pCenter.add(btnLapHDN = new JButton("Lập hóa đơn nhập"));
		btnLapHDN.setBounds(450, 70, 200, 40);
		btnLapHDN.setBackground(null);
		pCenter.add(btnLapHDX = new JButton("Lập hóa đơn xuất"));
		btnLapHDX.setBounds(720, 70, 200, 40);
		btnLapHDX.setBackground(null);
		//

		pSouth.add(btnDanhSachHD = new JButton("Danh Sách hóa đơn:"));
		btnDanhSachHD.setBounds(0, 0, 150, 30);
		btnDanhSachHD.setBackground(Color.YELLOW);
		pSouth.add(btnDanhSachCT = new JButton("Chi tiết hóa đơn:"));
		btnDanhSachCT.setBounds(610, 0, 150, 30);
		btnDanhSachCT.setBackground(Color.YELLOW);

		modelHD = new DefaultTableModel();
		tableHD = new JTable(modelHD) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableHD = new JScrollPane(tableHD));
		scpTableHD.setBounds(0, 31, 600, 500);
		tableHD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maHDDuocChon=(String) tableHD.getValueAt(tableHD.getSelectedRow(), 1);
				deleteModel(modelCT);
				listCT=new CTHoaDon().getListCTHDTheoMa(maHDDuocChon);
				int stt1=0;
				for (CTHD ct : listCT) {
					modelCT.addRow(new Object[] { 
							++stt1,ct.getMaHD(),ct.getLsp().getMaLoai(),ct.getSoLuong(),formatter.format(ct.getLsp().getDonGiaBan()),formatter.format(ct.getTongTien())
					}); 
				}
				btnDanhSachCT.setBackground(null);
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
		scpTableTK.setBounds(0, 31, 600, 500);
		scpTableTK.setVisible(false);
		tableTK.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if(e.getValueIsAdjusting()) {
					return;
				}
				maHDDuocChon=(String) tableTK.getValueAt(tableTK.getSelectedRow(), 1);
				deleteModel(modelCT);
				listCT=new CTHoaDon().getListCTHDTheoMa(maHDDuocChon);
				int stt1=0;
				for (CTHD ct : listCT) {
					modelCT.addRow(new Object[] { 
							++stt1,ct.getMaHD(),ct.getLsp().getMaLoai(),ct.getSoLuong(),formatter.format(ct.getLsp().getDonGiaBan()),formatter.format(ct.getTongTien())
					}); 
				}
				btnDanhSachCT.setBackground(null);
			}
		});
		//
		modelCT = new DefaultTableModel();
		tableCT = new JTable(modelCT) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		pSouth.add(scpTableCT = new JScrollPane(tableCT));
		scpTableCT.setBounds(610, 31, 760, 500);
		//
		setTitle("App Quản lí sản phẩm");
		setBounds(-2, 0, 1370, 730);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		
		// Sử lý button
		btnNhaCC.addActionListener(this);
		btnLoai.addActionListener(this);
		btnSanPham.addActionListener(this);
		btnNhomHang.addActionListener(this);
		btnDangXuat.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnDanhSachCT.addActionListener(this);
		btnDanhSachHD.addActionListener(this);
		btnLapHDN.addActionListener(this);
		btnLapHDX.addActionListener(this);
	}

	public void deleteModel(DefaultTableModel model) {
		for(int i=0;i<model.getRowCount();) {
			model.removeRow(i);
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnNhaCC) {
			this.setVisible(false);
			GDNhaCungCap gd = new GDNhaCungCap(this.maNV);
			gd.setVisible(true);
		}
		if(e.getSource()==btnLoai) {
			this.setVisible(false);
			GDLoaiSP gd = new GDLoaiSP(this.maNV);
			gd.setVisible(true);
		}
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
		if(e.getSource()==btnDangXuat) {
			GDChinh gd = new GDChinh();
			gd.setVisible(true);
			this.setVisible(false);
		}
		if(e.getSource()==btnLapHDN) {
			GD_HoaDonNhap gd = new GD_HoaDonNhap(this.maNV);
			gd.setModal(true);
			gd.setVisible(true);
		}
		if(e.getSource()==btnLapHDX) {
			GD_HoaDonXuat gd = new GD_HoaDonXuat(this.maNV);
			gd.setModal(true);
			gd.setVisible(true);
		}
		if(e.getSource()==btnTimKiem) {
			if(!jtfMaHD.getText().equals("")||!jtfNgayLap.getText().equals("")) {
				deleteModel(modelTK);
				listTK=new CTHoaDon().getListHoaDonTK(jtfMaHD.getText().toString(), jtfNgayLap.getText().toString());
				int stt=0;
				for (HoaDon hd : listTK) {
					modelTK.addRow(new Object[] { 
							++stt,hd.getMaHD(), hd.getNhanVien().getMaNV(), hd.getNgayLapHD(),formatter.format(hd.getTongTien())
					}); 
				}

				btnDanhSachHD.setBackground(null);
				btnTimKiem.setBackground(Color.YELLOW);
				scpTableHD.setVisible(false);
				scpTableTK.setVisible(true);
			}
		}
		if(e.getSource()==btnDanhSachHD) {
			btnDanhSachHD.setBackground(Color.YELLOW);
			btnTimKiem.setBackground(null);
			scpTableHD.setVisible(true);
			scpTableTK.setVisible(false);
		}
		if(e.getSource()==btnDanhSachCT) {
			maHDDuocChon="";
			deleteModel(modelCT);
			listCT = new CTHoaDon().getListCTHD();
			int stt1=0;
			for (CTHD ct : listCT) {
				modelCT.addRow(new Object[] { 
						++stt1,ct.getMaHD(),ct.getLsp().getMaLoai(),ct.getSoLuong(),formatter.format(ct.getLsp().getDonGiaBan()),formatter.format(ct.getTongTien())
				}); 
			}
			btnDanhSachCT.setBackground(Color.YELLOW);
		}
	}
}

