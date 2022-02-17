public interface  Person {
    public String getName();
    public String getSurname();
    public void setName(String name);
    public void setSurname(String surname);
    public String getPassword();
    public void setPassword(String password);
    public String getEmail();
    public void setEmail(String email);
    public boolean logIn(String id,String password);
}