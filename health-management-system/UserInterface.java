/**
 * User interface includes what all users do
 */
public interface UserInterface {
    /**
     * To edit the user's profile
	 * @param user
     * @param mail new mail
     * @param name new name
     * @param surname new surname
     * @param password new password
     */
    public void editProfile(User user, String mail, String name, String surname, String password);

    /**
     * display of user-specific menu
     */
    public void menu();
}
