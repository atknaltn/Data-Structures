public class Admin extends User implements AdminInterface{
    private Company company;

    public Admin(Company company,String name, String surname, String email,String password) {
        super(name, surname,email,password);
        setCompany(company);
    }
    @Override
    public void addBranches(Branch branch) throws Exception{
        if (!getCompany().getBranches().add(branch)) {
            throw new Exception();
        }
    }
    @Override
    public void removeBranches(Branch branch) throws Exception{
        if (!getCompany().getBranches().remove(branch)) {
            throw new Exception();
        }
    }
    @Override
    public void addBranchEmployee(Branch branch, Employee employee) {
        getCompany().getBranches().get(getCompany().getBranches().findIndex(branch)).getEmployees().add(employee);
    }
    @Override
    public void removeBranchEmployee(Branch branch, Employee employee) {
        getCompany().getBranches().get(getCompany().getBranches().findIndex(branch)).getEmployees().remove(employee);
    }
    @Override
    public void queryProductState() throws Exception{
        if (getCompany().getBranches().size()==0) {
            throw new Exception();
        }
        for (int i = 0; i < getCompany().getBranches().size(); i++) {
            for (int j = 0; j < getCompany().getBranches().get(i).getNeedToBeSupplied().size();j++) {
                System.out.println(getCompany().getBranches().get(i).getNeedToBeSupplied().get(j));
            }
        }
    }
    public void listBranches() throws Exception{
        if (getCompany().getBranches().size()==0) {
            throw new Exception();
        }
        System.out.println();
        for (int i = 0; i < getCompany().getBranches().size(); i++) {
            System.out.println(getCompany().getBranches().get(i));
        }
        System.out.println();
    }

    public Company getCompany() {
        return this.company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
