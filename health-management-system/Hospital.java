import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
/**
 * Stores all information about hospital and users.*
 */
public class Hospital {
	
	private String hospitalName;
	private Admin admin;
	private AVLTree<User> allUsers;
	private ConcurrentSkipListSet<Doctor> doctors;
	private TreeSet<Patient> patients;
	private ArrayList<Receptionist> receptionists;
	private ArrayList<Pharmacist> pharmacists;
	private ArrayList<Prescription> prescriptions;
	private ArrayList<Medicine> medicines;
	private PriorityQueue<Appointment> appointments;
	private ListGraph<User> relatedUsers;
	
	/**
	 * Creates a hospital with the given name.
	 * @param name Hospital's name
	 */
	public Hospital(String name) {
		this.hospitalName=name;
		admin = new Admin(new PersonalClass("Admin", "Default", "admin", "123"), this);
		doctors = new ConcurrentSkipListSet<>();
		patients = new TreeSet<>();
		receptionists = new ArrayList<>();
		pharmacists = new ArrayList<>();
		prescriptions = new ArrayList<>();
		allUsers = new AVLTree<>();
		appointments = new PriorityQueue<>();
		relatedUsers = new ListGraph<User>(100, false);
		medicines = new ArrayList<>();
		allUsers.add(admin);
	}
	/**
	 * Hospital name getter.
	 * @return Hospital name
	 */
	public String getName() {
		return this.hospitalName;
	}
	/**
	 * Hospital name setter.
	 * @param name New Hsopital Name
	 */
	public void setName(String name) {
		this.hospitalName=name;
	}
	/**
	 * Admin name getter.
	 */
	public Admin getAdmin() {
		return  admin;
	}
	/**
	 * Doctors getter.
	 * @return All doctors in the hospital.
	 */
	public ConcurrentSkipListSet<Doctor> getDoctors(){
		return this.doctors;
	}
	/**
	 * Patients getter.
	 * @return All patients in the hospital.
	 */
	public TreeSet<Patient> getPatients(){
		return this.patients;
	}
	/**
	 * Receptionists getter
	 * @return All receptionists in the hospital.
	 */
	public ArrayList<Receptionist> getReceptionists(){
		return this.receptionists;
	}
	/**
	 * Pharmacists getter
	 * @return All pharmacists in the hospital.
	 */
	public ArrayList<Pharmacist> getPharmacists(){
		return this.pharmacists;
	}
	/**
	 * Prescriptions getter.
	 * @return All prescriptions in the hospital.
	 */
	public ArrayList<Prescription> getPrescriptions(){
		return this.prescriptions;
	}
	/**
	 * Appointments getter.
	 * @return All appointments in the hospital.
	 */
	public PriorityQueue<Appointment> getAppointments(){
		return this.appointments;
	}
	/**
	 * All users getter.
	 * @return All users in the hospital.
	 */
	public AVLTree<User> getAllUsers() {
		return this.allUsers;
	}
	/**
	 * All medicines getter.
	 * @return All medicines in the hospital.
	 */
	public ArrayList<Medicine> getAllMedicines() {
		return this.medicines;
	}
	/**
	 * Finds users by their id.
	 * @param id User id
	 * @return User
	 */
	public User getUserByID(Integer id){
		return findUser(this.getAllUsers(), id);
	}
	/**
	 * Helper function for getUserByID
	 * @param root root of all users.
	 * @param id id for user will be found
	 * @return User
	 */
	private User findUser(BinaryTree<User> root,Integer id){
		if (root==null) {
			return null;
		}
		if (root.getData().getPersonalData().getId()==id) {
			return root.getData();
		}
		else if (root.getData().getPersonalData().getId()<id) {
			return findUser(root.getLeftSubtree(), id);
		}
		else
			return findUser(root.getRightSubtree(), id);
	}
	/**
	 * Related users getter.
	 * @return All related users in the hospital.
	 */
	public ListGraph<User> getRelatedUsers(){
		return relatedUsers;
	}
	/**
	 * Login to the hospital's system
	 * @param mail mail of the user
	 * @param password password of the user
	 * @return true if there exists user,
	 * 			otherwise false
	 */
	public boolean login(String mail, String password){
		if(this.getAllUsers().size()==0){
			System.out.println("WARNING: Empty hospital.");
			return false;
		}
		User user = findUser(this.getAllUsers(), mail, password);
		if( user == null ){
			System.out.println("WARNING: The system does not have a user with this information.");
			return false;
		}
		user.menu();
		return true;
	}
	/**
	 * Function to find user with mail and password
	 * @param root root of all users.
	 * @param mail mail for the user will be found
	 * @param password password for the user will be found
	 * @return User
	 */
	public User findUser(BinaryTree<User> root, String mail, String password){
		if (root==null) {
			return null;
		}
		if (root.getData().getPersonalData().getMail().equals(mail) &&
				root.getData().getPersonalData().getPassword().equals(password)) {
			return root.getData();
		}
		else if (root.getData().getPersonalData().getMail().compareTo(mail)<0) {
			return findUser(root.getLeftSubtree(), mail, password);
		}
		else
			return findUser(root.getRightSubtree(), mail, password);
	}
	/**
	 * Function to searching doctors by speciality
	 * This function can be used by primarly patient class
	 * @param specialityInput is the string input
	 * @return temp or null if not finded
	 * */
	public ArrayList<Doctor> findDoctorBySpeciality(String specialityInput){
		Iterator<Doctor> iter = doctors.descendingIterator();
		ArrayList<Doctor> tempDoctors = new ArrayList<>();
		Doctor temp = new Doctor(new PersonalClass(null, null), this, null);
		while (iter.hasNext()){
			temp = iter.next();
			if(temp.getSpecialty().contains(specialityInput)&&temp.isAvailable())
				tempDoctors.add(temp);
		}
		if (tempDoctors.size()==0)
			return null;
		else
			return tempDoctors;
	}
	/**
	 * Function to searching doctors by name
	 * This function can be used by primarly patient class
	 * @param nameInput is the string input
	 * @return temp or null if not finded
	 * */
	public Doctor findDoctorByName(String nameInput){
		Iterator<Doctor> iter = doctors.descendingIterator();
		Doctor temp = new Doctor(new PersonalClass(null, null), this, null);
		while (iter.hasNext()){
			temp = iter.next();
			if(temp.getPersonalData().getName().contains(nameInput)&&temp.isAvailable())
				return temp;
		}
		return null;
	}

	/**
	 * To create hospital's default data
	 */
	public void createDefaultData(){
		/*Create hospital's datas*/
		/*Create doctors*/
		PersonalClass p1 = new PersonalClass("Atakan","Altin","atakan","123");
		PersonalClass p2 = new PersonalClass("Emine","Sultan","emine","123");
		PersonalClass p3 = new PersonalClass("Emrullah","Salik","emrullah","123");
		Doctor doctor1 = new Doctor(p1,this,"kalp");
		Doctor doctor2 = new Doctor(p2,this,"dahiliye");
		Doctor doctor3 = new Doctor(p3,this,"goz");
		admin.addDoctor(doctor1);
		admin.addDoctor(doctor2);
		admin.addDoctor(doctor3);
		/*Create patients*/
		PersonalClass p4 = new PersonalClass("Ozan","Onceken","ozan","123");
		PersonalClass p5 = new PersonalClass("Furkan","Ergin","furkan","123");
		PersonalClass p6 = new PersonalClass("Minnoş","Savran","minnos","123");
		Patient patient1 = new Patient(p4,this);
		Patient patient2 = new Patient(p5,this);
		Patient patient3 = new Patient(p6,this);
		admin.addPatient(patient1);
		admin.addPatient(patient2);
		admin.addPatient(patient3);
		/*Create medicine*/
		Medicine medicine1 = new Medicine(0,"Nurofen",0,15);
		Medicine medicine2 = new Medicine("Arveles",8);
		Medicine medicine3 = new Medicine("Parol", 5);
		Medicine medicine4 = new Medicine("Deloday",2);
		Medicine medicine5 = new Medicine("Aspirin",45);
		Medicine medicine6 = new Medicine("Dolorex",101);
		Medicine medicine7 = new Medicine("Zedprex",211);
		Medicine medicine8 = new Medicine("Majezik",91);
		Medicine medicine9 = new Medicine("Apranax",23);
		Medicine medicine10 = new Medicine("Novalgin",56);
		admin.getHospital().getAllMedicines().add(medicine1);
		admin.getHospital().getAllMedicines().add(medicine2);
		admin.getHospital().getAllMedicines().add(medicine3);
		admin.getHospital().getAllMedicines().add(medicine4);
		admin.getHospital().getAllMedicines().add(medicine5);
		admin.getHospital().getAllMedicines().add(medicine6);
		admin.getHospital().getAllMedicines().add(medicine7);
		admin.getHospital().getAllMedicines().add(medicine8);
		admin.getHospital().getAllMedicines().add(medicine9);
		admin.getHospital().getAllMedicines().add(medicine10);
		/*Create pharmacists*/
		PersonalClass p7 = new PersonalClass("Ali","Yılmaz","ali","123");
		PersonalClass p8 = new PersonalClass("Ahmet","Sezer","ahmet","123");
		PersonalClass p9 = new PersonalClass("Ayse","Yildirim","ayse","123");
		Pharmacist pharmacist1 = new Pharmacist(p7,"YASAM ECZANESI",this);
		Pharmacist pharmacist2 = new Pharmacist(p8,"MERKEZ ECZANESI",this);
		Pharmacist pharmacist3 = new Pharmacist(p9,"YILDIZ ECZANESI",this);
		admin.addPharmacist(pharmacist1);
		admin.addPharmacist(pharmacist2);
		admin.addPharmacist(pharmacist3);
		/*Create receptionist*/
		PersonalClass p10 = new PersonalClass("Sinem","Kaya","sinem","123");
		PersonalClass p11 = new PersonalClass("Burak","Koca","burak","123");
		PersonalClass p12 = new PersonalClass("Esra","Kucuk","esra","123");
		Receptionist receptionist1 = new Receptionist(p10,this);
		Receptionist receptionist2 = new Receptionist(p11,this);
		Receptionist receptionist3 = new Receptionist(p12,this);
		admin.addReceptionist(receptionist1);
		admin.addReceptionist(receptionist2);
		admin.addReceptionist(receptionist3);
	}
}