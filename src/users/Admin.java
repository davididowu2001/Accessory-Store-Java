package users;

public class Admin extends User{
	//admin inherits from user


    public Admin(int userId, String userName, String name, int house_number, String postcode, String city, String role) {
        super(userId, userName, name, house_number, postcode, city, role);
    }

}
