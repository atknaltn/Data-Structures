/**
 * To hold user's general data
 */
public abstract class User implements UserInterface, Comparable<User>{
	/**To hold personal data*/
	private PersonalClass person;
	/**To hold the hospital*/
	private Hospital hospital;

	/**
	 * Constructor to create User
	 * @param person personal data
	 * @param hospital hospital
	 */
	User(PersonalClass person, Hospital hospital){
		this.person = person;
		this.hospital = hospital;
	}

	/**
	 * Getter for personal data
	 * @return Hospital object of the user
	 */
	public PersonalClass getPersonalData() {
		return person;
	}

	/**
	 * Getter for hospital
	 * @return Hospital object of the user
	 */
	public Hospital getHospital() {
		return hospital;
	}

	/**
	 * Setter for hospital
	 * @param hospital new hospital object
	 */
	public void setHospital(Hospital hospital) {
		this.hospital = hospital;
	}

	/**
	 * To edit the user's profile
	 * @param mail new mail
	 * @param name new name
	 * @param surname new surname
	 * @param password new password
	 */
	public void editProfile(User user, String mail, String name, String surname, String password){
		getHospital().getAdmin().editUserProfile(user,mail,name,surname,password);
	}

	/**
	 * @param o 			other data for compare
	 * @return				returns 0 if their mail and password are same
	 */
	@Override
	public int compareTo(User o) {
		return person.compareTo(o.getPersonalData());
	}

	@Override
	public String toString(){
		return person.toString();
	}
}
