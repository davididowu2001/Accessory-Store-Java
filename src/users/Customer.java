package users;

public class Customer extends User{
	//customer inherits from user
	//private attributes
    private int userId;
    private String userName;
    private String name;
    private int house_number;
    private String postcode;
    private String city;
    private String role;

    public Customer(int userId, String userName, String name, int house_number, String postcode, String city, String role) {
        super(userId, userName, name, house_number, postcode, city, role);


        this.userId = userId;
        this.userName = userName;
        this.name = name;
        this.house_number = house_number;
        this.postcode = postcode;
        this.city = city;
        this.role = role;
    }
    //functions to access private attributes
    public String getName() {
        return name;
    }

    public int getUserId() {
        return userId;
    }

    public int getHouse_number() {
        return house_number;
    }

    public String getCity() {
        return city;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getRole() {
        return role;
    }

    public String getUserName() {
        return userName;
    }

}

