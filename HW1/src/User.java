public class User implements Person{
    private String password;
    private String name;
    private String surname;
    private String email;

    public User(String name,String surname,String email,String password){
        this.password=password;
        this.name=name;
        this.surname=surname;
        this.email=email;
    }
    @Override
    public boolean equals(Object obj) {
        return getEmail().equals(((User)obj).getEmail()) && (getPassword().equals(((User)obj).getPassword()));
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public void setName(String name) {
        this.name=name;
    }
    public void setSurname(String surname) {
        this.surname=surname;
    }
    public String getPassword() {
        return this.password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public boolean logIn(String id,String password){
        return true;
    }
}
