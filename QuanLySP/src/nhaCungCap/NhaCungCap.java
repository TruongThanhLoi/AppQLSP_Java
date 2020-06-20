package nhaCungCap;

import org.omg.PortableServer.ForwardRequestHelper;

public class NhaCungCap {
	private String maNCC;
	private String tenNCC;
	private String dicChiNCC;
	private String sdtNCC;
	private String emailNCC;
	
	public String getMaNCC() {
		return maNCC;
	}
	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}
	public String getTenNCC() {
		return tenNCC;
	}
	
	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}
	public String getDicChiNCC() {
		return dicChiNCC;
	}
	public void setDicChiNCC(String dicChiNCC) {
		this.dicChiNCC = dicChiNCC;
	}
	public String getSdtNCC() {
		return sdtNCC;
	}
	public void setSdtNCC(String sdtNCC) {
		this.sdtNCC = sdtNCC;
	}
	public String getEmailNCC() {
		return emailNCC;
	}
	public void setEmailNCC(String emailNCC) {
		this.emailNCC = emailNCC;
	}
	
	public NhaCungCap(String maNCC, String tenNCC, String dicChiNCC, String sdtNCC, String emailNCC) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.dicChiNCC = dicChiNCC;
		this.sdtNCC = sdtNCC;
		this.emailNCC = emailNCC;
	}
	public NhaCungCap(String tenNCC) {
		super();
		this.tenNCC = tenNCC;
	}
	public NhaCungCap() {
	}
	
	@Override
	public String toString() {
		return "NhaCungCap [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", dicChiNCC=" + dicChiNCC + ", sdtNCC=" + sdtNCC
				+ ", emailNCC=" + emailNCC + "]";
	}
	
	
}
