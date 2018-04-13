package model;

/**
 * @author khamchand
 * This class is a model of Company
 * and it will store the details of a company
 */
public class Company {
	int company_id,company_tin;
	String company_name,established_year,mobile,address,registration,email,company_logo_url,company_website;
	public int getCompany_id() {
		return company_id;
	}
	public void setCompany_id(int company_id) {
		this.company_id = company_id;
	}
	public int getCompany_tin() {
		return company_tin;
	}
	public void setCompany_tin(int company_tin) {
		this.company_tin = company_tin;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getEstablished_year() {
		return established_year;
	}
	public void setEstablished_year(String established_year) {
		this.established_year = established_year;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRegistration() {
		return registration;
	}
	public void setRegistration(String registration) {
		this.registration = registration;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCompany_logo_url() {
		return company_logo_url;
	}
	public void setCompany_logo_url(String company_logo_url) {
		this.company_logo_url = company_logo_url;
	}
	public String getCompany_website() {
		return company_website;
	}
	public void setCompany_website(String company_website) {
		this.company_website = company_website;
	}
	
}
