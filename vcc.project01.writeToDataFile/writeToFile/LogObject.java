package writeToFile;

import java.sql.Timestamp;

public class LogObject {
	private Timestamp timeCreate; 
	private Timestamp cookieCreate; 
	private int browserCode; 
	private String browserVer; 
	private int osCode; 
	private String osVer; 
	private Long ip; 
	private int locId; 
	private String domain; 
	private int siteId; 
	private int cId; 
	private String path; 
	private String referer; 
	private Long guid; 
	private String flashVersion; 
	private String jre; 
	private String sr; 
	private String sc; 
	private int geographic;
	public Timestamp getTimeCreate() {
		return timeCreate;
	}
	public void setTimeCreate(Timestamp timeCreate) {
		this.timeCreate = timeCreate;
	}
	public Timestamp getCookieCreate() {
		return cookieCreate;
	}
	public void setCookieCreate(Timestamp cookieCreate) {
		this.cookieCreate = cookieCreate;
	}
	public int getBrowserCode() {
		return browserCode;
	}
	public void setBrowserCode(int browserCode) {
		this.browserCode = browserCode;
	}
	public String getBrowserVer() {
		return browserVer;
	}
	public void setBrowserVer(String browserVer) {
		this.browserVer = browserVer;
	}
	public int getOsCode() {
		return osCode;
	}
	public void setOsCode(int osCode) {
		this.osCode = osCode;
	}
	public String getOsVer() {
		return osVer;
	}
	public void setOsVer(String osVer) {
		this.osVer = osVer;
	}
	public Long getIp() {
		return ip;
	}
	public void setIp(Long ip) {
		this.ip = ip;
	}
	public int getLocId() {
		return locId;
	}
	public void setLocId(int locId) {
		this.locId = locId;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public int getSiteId() {
		return siteId;
	}
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}
	public int getcId() {
		return cId;
	}
	public void setcId(int cId) {
		this.cId = cId;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getReferer() {
		return referer;
	}
	public void setReferer(String referer) {
		this.referer = referer;
	}
	public Long getGuid() {
		return guid;
	}
	public void setGuid(Long guid) {
		this.guid = guid;
	}
	public String getFlashVersion() {
		return flashVersion;
	}
	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}
	public String getJre() {
		return jre;
	}
	public void setJre(String jre) {
		this.jre = jre;
	}
	public String getSr() {
		return sr;
	}
	public void setSr(String sr) {
		this.sr = sr;
	}
	public String getSc() {
		return sc;
	}
	public void setSc(String sc) {
		this.sc = sc;
	}
	public int getGeographic() {
		return geographic;
	}
	public void setGeographic(int geographic) {
		this.geographic = geographic;
	}
	
	
}

