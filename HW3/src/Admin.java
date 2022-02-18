/**
 * Admin Class
 */
public class Admin extends User implements AdminInterface{
    private Company company;
    /**
     * It creates admin for the system
     * @param company admin's company
     * @param name admin name
     * @param surname admin surname
     * @param email admin email
     * @param password admin password
     */
    public Admin(Company company,String name, String surname, String email,String password) {
        super(name, surname,email,password);
        setCompany(company);
    }
    /**
     * Adding Branches to the company.
     * @param branch branch to add
     */
    @Override
    public void addBranches(Branch branch){
        getCompany().getBranches().addLast(branch);
    }
    /**
     * Removing Branches from the company.
     * @param branch branch to remove
     */
    @Override
    public void removeBranches(Branch branch) throws Exception{
        if (!getCompany().getBranches().remove(branch)) {
            throw new Exception();
        }
    }
    /**
     * Adding Employee to the branch.
     * @param branch branch
     * @param employee employee
     */
    @Override
    public void addBranchEmployee(Branch branch, Employee employee) {
        getCompany().getBranches().get(getCompany().getBranches().indexOf(branch)).getEmployees().add(employee);
    }
    /**
     * 
     */
    @Override
    public void removeBranchEmployee(Branch branch, Employee employee) {
        try {
            int i=getCompany().getBranches().get(getCompany().getBranches().indexOf(branch)).getEmployees().indexOf(employee);
            getCompany().getBranches().get(getCompany().getBranches().indexOf(branch)).getEmployees().remove(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * Checking if there is product need to be supplied.
     */
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
    /**
     * Listing All Branches
     * @throws Exception
     */
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
    /**
     * returns company
     * @return company
     */
    public Company getCompany() {
        return this.company;
    }
    /**
     * sets company
     * @param company
     */
    public void setCompany(Company company) {
        this.company = company;
    }
}
