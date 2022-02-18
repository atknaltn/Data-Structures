/**
 * User abstract class.
 */
public abstract class User implements Person,Comparable<User>{

    private String name;
    private Integer id;
    private static Integer userID=10000001;
    private String password;
    private Company company;


    protected String role;
    public User(String name,String password,Company company){
        setName(name);
        setId(userID++);
        setPassword(password);
        setCompany(company);
    }
    /**
     * @return true if the user enters the system succesfully
     */
    @Override
    public boolean login() {
        if (company.findUser(this.getId())==null) {
            return false;
        }
        return true;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return this.id;
    }

    protected void setId(Integer id) {
        this.id = id;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return this.role;
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
