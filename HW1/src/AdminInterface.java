public interface AdminInterface {
    public void addBranches(Branch branch) throws Exception;
    public void removeBranches(Branch branch) throws Exception;
    public void addBranchEmployee(Branch branch, Employee employee);
    public void removeBranchEmployee(Branch branch, Employee employee);
    public void queryProductState() throws Exception;
}
