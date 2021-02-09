package demo.view.entityview;

import java.io.Serializable;
import java.util.List;

import demo.entity.User;

public class UserView extends BaseEntityView implements Serializable {
	private static final long serialVersionUID = 8801350529438033008L;

	private String username;

	private String name;

	private String firstName;

	private String lastName;

	private String password;

	private String email;

	private String phone;

	private String website;

	private Company company;

	private Address address;
	
	private List<GroupView> groups;

	public UserView() {

	}

	public UserView(User user) {
		this.setId(user.getId());
		this.setStatus(user.getStatus());
		this.setDateCreate(user.getDateCreate());
		this.setDateUpdate(user.getDateUpdate());

		this.username = user.getUsername();
		this.name = user.getFirstName() + " " + user.getLastName();
		this.firstName = user.getFirstName();
		this.lastName = user.getLastName();
		this.password = user.getPassword();
		this.email = user.getEmail();
		this.phone = user.getPhone();
		this.website = user.getWebsite();
		Company co = new Company();
		co.setName(user.getCompanyName());
		co.setCatchPhrase(user.getCompanyCatchPhrase());
		co.setBs(user.getComanyBusiness());
		this.company = co;

		Address addr = new Address();
		addr.setCity(user.getCity());
		addr.setStreet(user.getStreet());
		addr.setSuite(user.getSuite());
		addr.setZipcode(user.getZipcode());

		Geo geo = new Geo();
		geo.setLat(user.getGeoLat());
		geo.setLng(user.getGeoLng());
		addr.setGeo(geo);

		this.address = addr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public List<GroupView> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupView> groups) {
		this.groups = groups;
	}



	public static class Address implements Serializable {

		private static final long serialVersionUID = -7684351022544149028L;

		private String street;

		private String suite;

		private String city;

		private String zipcode;

		private Geo geo;

		public Address() {

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

		public Geo getGeo() {
			return geo;
		}

		public void setGeo(Geo geo) {
			this.geo = geo;
		}

	}

	public static class Geo implements Serializable {

		private static final long serialVersionUID = -3875432866340814789L;

		private Double lat;

		private Double lng;

		public Geo() {

		}

		public Double getLat() {
			return lat;
		}

		public void setLat(Double lat) {
			this.lat = lat;
		}

		public Double getLng() {
			return lng;
		}

		public void setLng(Double lng) {
			this.lng = lng;
		}
	}

	public static class Company implements Serializable {

		private static final long serialVersionUID = 5772967186026128391L;

		private String name;

		private String catchPhrase;

		private String bs;

		public Company() {

		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCatchPhrase() {
			return catchPhrase;
		}

		public void setCatchPhrase(String catchPhrase) {
			this.catchPhrase = catchPhrase;
		}

		public String getBs() {
			return bs;
		}

		public void setBs(String bs) {
			this.bs = bs;
		}

	}

}
