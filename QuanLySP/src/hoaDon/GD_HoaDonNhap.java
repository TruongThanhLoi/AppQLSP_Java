package hoaDon;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.Normalizer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import boundary.GDChinh;
import controller.CTHoaDon;
import controller.CTLoaiSP;
import controller.CTSanPham;
import loaiSanPham.LoaiSP;
import nhanVien.NhanVien;
import sanPham.SanPham;

public class GD_HoaDonNhap extends JDialog implements ActionListener{
	
	JPanel jpCenter,jpNorth,jpSouth;
	JTable table;
	DefaultTableModel model;
	JScrollPane scpTable;
	JLabel jlbMaNV,jlbMaHD,jlbNgayLapHD,jlbMaLoaiSP,jlbNgayHH,jlbSoLuong,jlbGiaNhap,jlbHanBH;
	JTextField jtfMaLoaiSP,jtfNgayHH,jtfSoLuong,jtfGiaNhap,jtfHanBH;
	

	JButton btnThem,btnHuy,btnXong;
	private ArrayList<CTHD> listCTHD=new ArrayList<CTHD>();
	private ArrayList<SanPham> listSP= new ArrayList<SanPham>();
	String maHD="HDN";
	private HoaDon hd=new HoaDon();
	public GD_HoaDonNhap(String maNV) {
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
		int slHD=new CTHoaDon().getLaySLHDN()+1;
		
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
				"Mã Loại SP","Số lượng", "Giá nhập","Hạn bảo hành","Hạn sử dụng"
		});
		table = new JTable(model) {
			public boolean isCellEditable(int row, int column) {                
                return false;               
			};
		};
		jpCenter.add(scpTable=new JScrollPane(table));
		scpTable.setBounds(0, 0, 550, 250);
		
		jpCenter.add(jlbMaLoaiSP=new JLabel("Mã loại sp:"));
		jlbMaLoaiSP.setBounds(10, 260, 90, 30);
		jpCenter.add(jlbSoLuong=new JLabel("Số lượng:"));
		jlbSoLuong.setBounds(10, 300, 90, 30);
		jpCenter.add(jlbGiaNhap=new JLabel("Giá nhập:"));
		jlbGiaNhap.setBounds(10, 340, 90, 30);
		jpCenter.add(jtfMaLoaiSP=new JTextField());
		jtfMaLoaiSP.setBounds(100, 260,150 , 30);
		jpCenter.add(jtfSoLuong=new JTextField());
		jtfSoLuong.setBounds(100, 300,150 , 30);
		jpCenter.add(jtfGiaNhap=new JTextField());
		jtfGiaNhap.setBounds(100, 340,150 , 30);
		
		jpCenter.add(jlbHanBH=new JLabel("Hạn bảo hành:"));
		jlbHanBH.setBounds(300, 300, 90, 30);
		jpCenter.add(jlbNgayHH=new JLabel("Ngày hết hạn:"));
		jlbNgayHH.setBounds(300, 340, 90, 30);
		jpCenter.add(jtfHanBH=new JTextField());
		jtfHanBH.setBounds(390, 300,150 , 30);
		jpCenter.add(jtfNgayHH=new JTextField());
		jtfNgayHH.setBounds(390, 340,150 , 30);
		
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
	//Bỏ dấu
	public static class StringUtils{
		public static String removeAccent(String s) {
			  
			  String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
			  Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
			  return pattern.matcher(temp).replaceAll("");
		}
	}
	//lấy chử cái đầu
	public String layChuDau(String tenLoaiSP) {
		String s="";
		if(tenLoaiSP.equals("")) return "xxx";
		else {
			if(tenLoaiSP.charAt(0)!=' ') s+=tenLoaiSP.charAt(0);
			for (int i = 1; i < tenLoaiSP.length(); i++) {
				if(tenLoaiSP.charAt(i-1)==' ' && tenLoaiSP.charAt(i)!=' ') s+=tenLoaiSP.charAt(i);
			}
		}
		return StringUtils.removeAccent(s).toUpperCase();
	}
	//Lấy số lượng sản phẩm theo Hóa đơn
	public int laySoLuongSP(String maLoaiSP) {
		int dem=0;
		try {
			ArrayList<SanPham> listSP= new ArrayList<SanPham>();
			listSP= new CTSanPham().getListSanPhamTheoHD(maLoaiSP);
			if(listSP.size()>0) {
				String s=listSP.get(listSP.size()-1).getMaSP();
				int chiSo=s.length();
				dem=Integer.parseInt("0"+s.charAt(chiSo-4))*1000+Integer.parseInt("0"+s.charAt(chiSo-3))*100+
						Integer.parseInt("0"+s.charAt(chiSo-2))*10+Integer.parseInt("0"+s.charAt(chiSo-1));
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "X sai lấy số lượng.");
		}return dem;
	}
	//tạo số lượng cho mã
	public String maSoLuong(int dem) {
		if(dem<10)return "000"+dem;
		else if(dem<100) return "00"+dem;
		else if(dem<1000) return "0"+dem;
		else return dem+"";
	}
	//tạo ngày
	public Date taoNgay(String nhapNgay) {
		try {
			int nam=Integer.parseInt(nhapNgay.charAt(0)+"")*1000+Integer.parseInt(nhapNgay.charAt(1)+"")*100+
					Integer.parseInt(nhapNgay.charAt(2)+"")*10+Integer.parseInt(nhapNgay.charAt(3)+"");
			int thang=Integer.parseInt(nhapNgay.charAt(5)+"")*10+Integer.parseInt(nhapNgay.charAt(6)+"");
			int ngay=Integer.parseInt(nhapNgay.charAt(8)+"")*10+Integer.parseInt(nhapNgay.charAt(9)+"");
			return Date.valueOf(LocalDate.of(nam, thang, ngay));
		} catch (Exception e) {
			return null;
		}
	}
	//kiểm tra trùng mã trong list
	public boolean trungMaTrongList(String maLoaiSP) {
		for (SanPham sp : listSP) {
			if(sp.getLoai().getMaLoai().equals(maLoaiSP)) return true;
		}return false;
	}
	///Thêm chi tiết hóa đơn
	public boolean themCTHD(ArrayList<CTHD> listCTHD) {
		for (CTHD cthd : listCTHD) {
			if(!new CTHoaDon().themCTHDNhap(cthd))
				return false;
		}return true;
	}
	//Thêm ds sản phẩm
	public boolean themDSSP(ArrayList<SanPham> listSP) {
		for (SanPham sanPham : listSP) {
			if(!new CTHoaDon().themSanPham(sanPham)) return false;
		} return true;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnThem) {
			if(!jtfMaLoaiSP.getText().equals("") && !jtfSoLuong.getText().equals("")&&!jtfGiaNhap.getText().equals("")) {
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
							for(int i=1;i<sl+1;i++) {
								SanPham sp = new SanPham();
								DateTimeFormatter ngay=DateTimeFormatter.ofPattern("yyyyMMdd");
								try {
									sp.setMaSP(layChuDau((new CTLoaiSP().getLoaiSPTheoMa(jtfMaLoaiSP.getText())).getTenLoai())+
											"-"+ngay.format(LocalDate.now())+"-"+maSoLuong(laySoLuongSP(jtfMaLoaiSP.getText())+i));
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "X Nhập mã đàng hoàng.");
								}
								
								sp.setLoai(cthd.getLsp());
								try {
									sp.setDonGiaNhap(Double.parseDouble(jtfGiaNhap.getText()));
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "X Nhập giá nhập đàng hoàng.");
								}
								sp.setNgayNhap(Date.valueOf(LocalDate.now()));
								sp.setTrangThaiLoi("FALSE");
								try {
									sp.setHanBaoHanh(taoNgay(jtfHanBH.getText()));
									sp.setHanSuDung(taoNgay(jtfNgayHH.getText()));
								} catch (Exception e2) {
									JOptionPane.showMessageDialog(null, "X Nhập lại ngày hết hạn hoặc hạn bảo hành.");
								}
								listSP.add(sp);
							}
							model.addRow(new Object[] { 
								cthd.getLsp().getTenLoai(),cthd.getSoLuong(),jtfGiaNhap.getText(),jtfHanBH.getText(),jtfNgayHH.getText()
							});
							jtfMaLoaiSP.setText("");
							jtfSoLuong.setText("");
							jtfGiaNhap.setText("");
							jtfNgayHH.setText("");
							jtfHanBH.setText("");
						}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập số lượng > 0.");
					}else JOptionPane.showMessageDialog(null, "X Đã có mã loại sản phẩm này trong hóa đơn.");
				}else JOptionPane.showMessageDialog(null, "X Chưa có mã loại sản phẩm này.");
			}else JOptionPane.showMessageDialog(null, "X Bắt buộc nhập mã loại sp, số lượng và đơn giá nhập.");
		}
		if(e.getSource()==btnHuy) {
			this.setVisible(false);
		}
		if(e.getSource()==btnXong) {
			if(listCTHD.size()>0) {
				if(new CTHoaDon().themHoaDonNhap(hd) && themCTHD(listCTHD) && themDSSP(listSP)) {
					JOptionPane.showMessageDialog(null, "! Đã  thêm một hóa đơn.");
					GDHoaDon gd = new GDHoaDon(hd.getNhanVien().getMaNV());
					this.setVisible(false);
					gd.setVisible(true);
				}else JOptionPane.showMessageDialog(null, "X Không thể tạo hóa đơn này.");
			}else JOptionPane.showMessageDialog(null, "X Chưa có đơn hàng nào.");
		}
	}
}

