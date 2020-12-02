package demo.entity.user;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import demo.entity.BaseEntity;
import demo.view.entityView.UserView;

@Entity
@Table(name = "user")
public class User extends BaseEntity {
	@Column(name = "username")
	private String username;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "password")
	private String password;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "website")
	private String website;

	@Column(name = "addr_street")
	private String street;

	@Column(name = "addr_suite")
	private String suite;

	@Column(name = "addr_city")
	private String city;

	@Column(name = "addr_zipcode")
	private String zipcode;

	@Column(name = "addr_geo_lat")
	private Double geoLat;

	@Column(name = "addr_geo_lng")
	private Double geoLng;

	@Column(name = "co_name")
	private String companyName;

	@Column(name = "co_catch_phrase")
	private String companyCatchPhrase;

	@Column(name = "co_bs")
	private String comanyBusiness;

	public User() {
		super();
	}

	public User(UserView userV) {
		super();
		this.setStatus(1);
		this.setCreateDate(new Date());
		this.setUpdateDate(new Date());

		this.username = userV.getUsername();
		this.firstName = userV.getFirstName();
		this.lastName = userV.getLastName();

		this.password = userV.getPassword();
		this.email = userV.getEmail();
		this.phone = userV.getPhone();
		this.website = userV.getWebsite();

		if (userV.getAddress() != null) {
			this.street = userV.getAddress().getStreet();
			this.suite = userV.getAddress().getSuite();
			this.city = userV.getAddress().getCity();
			this.zipcode = userV.getAddress().getZipcode();
			if (userV.getAddress().getGeo() != null) {
				this.geoLat = userV.getAddress().getGeo().getLat();
				this.geoLng = userV.getAddress().getGeo().getLng();
			}
		}

		if (userV.getCompany() != null) {
			this.companyName = userV.getCompany().getName();
			this.companyCatchPhrase = userV.getCompany().getCatchPhrase();
			this.comanyBusiness = userV.getCompany().getBs();
		}
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getSuite() {
		return suite;
	}

	public void setSuite(String suite) {
		this.suite = suite;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Double getGeoLat() {
		return geoLat;
	}

	public void setGeoLat(Double geoLat) {
		this.geoLat = geoLat;
	}

	public Double getGeoLng() {
		return geoLng;
	}

	public void setGeoLng(Double geoLng) {
		this.geoLng = geoLng;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyCatchPhrase() {
		return companyCatchPhrase;
	}

	public void setCompanyCatchPhrase(String companyCatchPhrase) {
		this.companyCatchPhrase = companyCatchPhrase;
	}

	public String getComanyBusiness() {
		return comanyBusiness;
	}

	public void setComanyBusiness(String comanyBusiness) {
		this.comanyBusiness = comanyBusiness;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public String toString() {
		return "User [userName=" + username + ", password=" + password + "]";
	}

}
