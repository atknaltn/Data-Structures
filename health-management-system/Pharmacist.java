import java.util.*;

public class Pharmacist extends User{
	private String pharmacyName;
	/**
	 * It creates pharmacist with given parameters.
	 * @param person Pharmacist's personal data
	 * @param pharmacyName Pharmacy name
	 * @param hospital Hospital
	 */
	public Pharmacist(PersonalClass person,String pharmacyName ,Hospital hospital){
		super(person, hospital);
		this.pharmacyName=pharmacyName;
	}
	/**
	 * It creates pharmacist with given parameters.
	 * @param person Pharmacist's personal data
	 * @param hospital Hospital
	 */
	public Pharmacist(PersonalClass person, Hospital hospital){
		super(person, hospital);
	}
	/**
	 * add suggestion
	 * @param user the user(doctor or pharmacist)
	 */
	public void addSuggestion(User user) throws IllegalArgumentException{
		if(this.getHospital().getRelatedUsers().isEdge(this, user)){
			System.out.println(" -> The suggestion already exists");
			return;
		}
		if(user instanceof Doctor){
			getHospital().getRelatedUsers().insert(new Edge<User>(this, user));
			System.out.println(" -> The suggestion is added.");
		}
		else
			throw new IllegalArgumentException();
	}

	/**
	 * remove suggestion
	 * @param user the user(doctor or pharmacist)
	 */
	public void removeSuggestion(User user){
		if(user instanceof Doctor){
			getHospital().getRelatedUsers().remove(new Edge<User>(this, user));
			System.out.println(" -> The suggestion is removed.");
		}
		else
			throw new IllegalArgumentException();
	}

	/**
	 * Sees own suggestions
	 */
	public boolean showSuggestions(){
		String print = getHospital().getRelatedUsers().print(this);
		if(print.equals("There is no suggestion.\n")){
			System.out.print(print);
			return false;
		}
		System.out.print(print);
		return true;
	}

	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Pharmacist | " + super.toString());
		stringBuilder.append("\nPharmacy Name: "+this.getPharmacyName());
		return stringBuilder.toString();
	}
	/**
	 * Gets Pharmacy name.
	 * @return Returns Pharmacy Name
	 */
	public String getPharmacyName() {
		return this.pharmacyName;
	}
	/**
	 * Sets Pharmacy name.
	 * @param new name.
	 */
	public void setPharmacyName(String pharmacyName) {
		this.pharmacyName = pharmacyName;
	}
	/**
	 * Shows the pharmacist's special menu. It provides user to perform pharmacist operations.
	 */
	public void menu(){
		boolean exit = true;
		int selection,id;
		Patient p=null;
		Scanner sc = new Scanner(System.in);
		while (exit) {
			System.out.println("\n Welcome " + this.getPersonalData().getName() + " " + this.getPersonalData().getSurname());
			System.out.println("1. Show Prescription");
			System.out.println("2. Add Suggestion");
			System.out.println("3. Remove Suggestion");
			System.out.println("4. Show Suggestions");
			System.out.println("5. Show Profile");
			System.out.println("6. Edit Profile");
			System.out.println("7. Exit");
			System.out.println("Selection: ");
			selection=sc.nextInt();
			switch (selection) {
				case 1:
					for (Patient iter : getHospital().getPatients()) {
						System.out.println("ID: "+iter.getPersonalData().getId()+" NAME: "+iter.getPersonalData().getName()+" "+iter.getPersonalData().getSurname());
					}
					System.out.print("Please enter the Patient ID: ");
					id = sc.nextInt();
					for(Patient patient: this.getHospital().getPatients()){
						if(patient.getPersonalData().getId() == id){
							p = patient;
							break;
						}
					}
					if (p==null) {
						System.out.println("-> Invalid ID.");
					}
					else{
						if (p.getPrescriptions().size()==0) {
							System.out.println("\n-> This patient does not have a registered prescription.");
						}
						else{
							for (int i = 0; i < p.getPrescriptions().size(); i++) {
								System.out.println(p.getPrescriptions().get(i));
							}
						}
					}
					break;
				case 2:
					boolean exit2 = true;
					while(exit2){
						System.out.println();
						System.out.println(" * Doctors * ");
						for (Doctor doctor: this.getHospital().getDoctors()) {
							System.out.println(" -> " + doctor);
						}
						System.out.print("Please enter the ID: ");
						id= sc.nextInt();
						User user = null;
						try{
							for(Doctor doctor: this.getHospital().getDoctors()){
								if(doctor.getPersonalData().getId() == id){
									user = doctor;
									break;
								}
							}
							if(user != null){
								this.addSuggestion(user);
								exit2 = false;
							} else{
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e){
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				case 3:
					boolean exit3 = true;
					while(exit3){
						if(this.showSuggestions()){
							System.out.print("Please enter the ID: ");
							id = sc.nextInt();
							User user = null;
							try{
								for( Doctor doctor: this.getHospital().getDoctors()){
									if(doctor.getPersonalData().getId() == id){
										user = doctor;
										break;
									}
								}
								if(user != null &&
										this.getHospital().getRelatedUsers().isEdge(this, user)){
									this.removeSuggestion(user);
									exit3 = false;
								} else{
									System.out.println(" ! Invalid ID.");
								}
							} catch (Exception e){
								System.out.println("Please try again and enter valid value.");
							}
						} else{
							exit3 = false;
						}
					}
					break;
				case 4:
					showSuggestions();
					break;
				case 5:
					System.out.println(this);
					break;
				case 6:
					Scanner scanner = new Scanner(System.in);
					System.out.print("Enter new name: ");
					String name = scanner.nextLine();
					System.out.print("Enter new surname: ");
					String surname = scanner.nextLine();
					System.out.print("Enter new mail: ");
					String mail = scanner.nextLine();
					System.out.print("Enter new password: ");
					String password = scanner.nextLine();
					User user =this.getHospital().findUser(this.getHospital().getAllUsers(), mail, password);
					if (user==null) {
						this.editProfile(this, mail, name, surname, password);
					}else
						System.out.println("\n-> Invalid mail and password.");
					break;
				case 7:
					exit = false;
					break;
				default:
					System.err.println("WARNING: Wrong Command.");
					break;
			}
		}
	}
}
