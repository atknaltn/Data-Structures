import java.util.*;
/**
 * To hold doctor's data and actions
 */
public class Doctor extends User{
	//Data fields
	/**Doctors can expert a special area*/
	//https://en.wikipedia.org/wiki/Medical_specialty
	private String specialty;
	/**Stores free and appointed times*/
	private PriorityQueue<Appointment> appointments;
	/**False when all the available appointment times are full*/
	private boolean available = true ;
	//Methods
	/**Constructors*/
	/**
	 * Create doctor object with given data
	 * @param person personal data
	 * @param hospital hospital
	 * @param specialty specialty
	 */
	public Doctor(PersonalClass person, Hospital hospital, String specialty) {
		super(person, hospital);
		this.specialty = specialty;
		appointments=new PriorityQueue<>();
	}
	/**
	 * Create doctor object with given datas
	 * @param mail mail
	 * @param password password
	 */
	public Doctor(String mail, String password){
		super(new PersonalClass(mail, password), new Hospital("Grup 2"));
		appointments=new PriorityQueue<>();
	}
	/**
	 * Function to return speciality
	 * @return specialty
	 */
	public String getSpecialty() {
		return specialty;
	}
	/**
	 * Function to return appointments
	 * @return appointments
	 */
	public PriorityQueue<Appointment> getAppointments(){
		return appointments;
	}
	/**
	 * Add an appointment to the doctor's appointment list
	 * @param appointment to take an appointment
	 * @return if adding successfully, return true
	 * 		otherwise false
	 */
	public boolean addAppointment(Appointment appointment) {
		for(Appointment appointment1: getHospital().getAppointments()){
			if(appointment.compareTo(appointment1) == 0){
				System.out.println("WARNING: Cannot add. The appointment is full.");
				return false;
			}
		}
		System.out.println(" The appointment is added.");
		getHospital().getAppointments().offer(appointment);
		getAppointments().add(appointment);
		return true;
	}
	/**
	 * Doctors can view active appointment list
	 */
	public void viewAppointmentList(){
		PriorityQueue<Appointment> activeAppointments = new PriorityQueue<>();
		int index = 0;
		System.out.println("HEEY: "+ appointments.size());
		while(appointments.size()>index) {
			index++;
			Appointment appointment = appointments.peek();
			if( appointment.isAwake() )
				activeAppointments.offer(appointment);
		}
		if(activeAppointments.isEmpty()){
			System.out.println(" -> WARNING: There is no active appointment");
			return;
		}
		for(Appointment appointment: activeAppointments){
			System.out.println(" - " + appointment.printForDoctor());
		}
	}
	/**
	 * Doctors can view active patient list
	 * @return true if there is a active patient
	 * 			otherwise return false
	 */
	public boolean viewPatientList(){
		PriorityQueue<Appointment> activeAppointments = new PriorityQueue<>();
		int index = 0;
		while(appointments.size()>index) {
			index++;
			Appointment appointment = appointments.peek();
			if( appointment.isAwake() )
				activeAppointments.offer(appointment);
		}
		if(activeAppointments.isEmpty()){
			System.out.println(" -> WARNING: There is no active appointment");
			return false;
		}
		for(Appointment appointment: activeAppointments){
			System.out.print(" -> " + appointment.getPatient().print());
		}
		return true;
	}
	/**
	 * Searching a patient with given ID
	 * @param patientID ID to search
	 * @return if it can find, returns the patient
	 * 			otherwise returns null
	 */
	public Patient searchPatient(int patientID){
		for(Appointment appointment: getHospital().getAppointments()){
			if(appointment.getPatient().getPersonalData().getId() == patientID)
				return appointment.getPatient();
		}
		return null;
	}
	/**
	 * To add a disease to the patient
	 * @param patient the patient
	 * @param disease patient's disease
	 */
	public void addDisease(Patient patient, String disease){
		if(!patient.getDiseases().contains(disease)){
			patient.getDiseases().add(disease);
			System.out.println(" -> The disease is added.");
			return;
		}
		System.out.println(" -> WARNING: The disease already exists. Cannot add.");
	}
	/**
	 * To view a specific patient's history
	 * @param patient patient
	 */
	public void viewPatientHistory(Patient patient){
		patient.showHistory();
	}
	/**
	 * Doctors can prescribe to the patient
	 * @param prescription prescription of the patient
	 * @param medicine medicine
	 */
	public void addPrescription(Prescription prescription, Medicine medicine){
		if(prescription.getMedicines().containsKey(medicine.getId())){
			System.out.println(" -> WARNING: The medicine already exists. Cannot add.");
			return;
		}
		prescription.getMedicines().put(medicine.getId(), medicine);
		System.out.println(" -> The medicine is added.");
	}
	/**
	 * add suggestion
	 * @param user the user(doctor or pharmacist)
	 * @throws IllegalArgumentException
	 */
	public void addSuggestion(User user) throws IllegalArgumentException{
		if(this.getHospital().getRelatedUsers().isEdge(this, user)){
			System.out.println(" -> The suggestion already exists");
			return;
		}
		if(user instanceof Doctor || user instanceof Pharmacist){
			getHospital().getRelatedUsers().insert(new Edge<User>(this, user));
			System.out.println(" -> The suggestion is added.");
		}
		else
			throw new IllegalArgumentException();
	}
	/** Returns true if available appointment time exist
	 * false if appointment times are full
	 * @return available
	 * */
	public boolean isAvailable() {
		return available;
	}
	/**
	 * remove suggestion
	 * @param user the user(doctor or pharmacist)
	 */
	public void removeSuggestion(User user){
		if(user instanceof Doctor || user instanceof Pharmacist){
			getHospital().getRelatedUsers().remove(new Edge<User>(this, user));
			System.out.println(" -> The suggestion is removed.");
		}
		else
			throw new IllegalArgumentException();
	}
	/**
	 * sees own suggestions
	 * @return returns true if there is a suggestion,
	 * 			otherwise false
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
		stringBuilder.append("Doctor | " + super.toString() + " | Specialty: " + this.specialty);
		return stringBuilder.toString();
	}
	/**
	 * The menu of the Doctor
	 */
	public void menu(){
		System.out.println("\n Welcome Doctor " + this.getPersonalData().getName() + " " + this.getPersonalData().getSurname());
		String choice = "";
		boolean exit = true;
		int count =0;
		Scanner scanner = new Scanner(System.in);
		do{
			System.out.println("\n 1 - View patient&appointment list");
			System.out.println(" 2 - Add a disease&prescription to the patient");
			System.out.println(" 3 - View history of the patient");
			System.out.println(" 4 - Request for free time");
			System.out.println(" 5 - Edit profile");
			System.out.println(" 6 - Add a suggestion");
			System.out.println(" 7 - Remove a suggestion");
			System.out.println(" 8 - View suggestion list");
			System.out.println(" 0 - Log out");
			System.out.print("Please select: ");
			choice = scanner.nextLine();
			switch (choice){
				case "1":
				count=0;
					System.out.println();
					for (Appointment appointment : getHospital().getAppointments()) {
						if (appointment.getDoctor().getPersonalData().getId()==getPersonalData().getId()) {
							System.out.println("ID: "+appointment.getPatient().getPersonalData().getId()+" NAME: "+appointment.getPatient().getPersonalData().getName());
							count ++ ;
						}
					}
					if (count==0) {
						System.out.println("There is no active appointment right now.");
						break;
					}
					break;
				case "2":
					exit = true;
					while(exit) {
						System.out.println();
						/*if (!this.viewPatientList()) {
							break;
						}*/
						count =0;
						for (Appointment appointment : getHospital().getAppointments()) {
							if (appointment.getDoctor().getPersonalData().getId()==getPersonalData().getId()) {
								System.out.println("ID: "+appointment.getPatient().getPersonalData().getId()+" NAME: "+appointment.getPatient().getPersonalData().getName());
								count ++ ;
							}
						}
						if (count==0) {
							System.out.println("There is no active appointment right now.");
							break;
						}
						System.out.println();
						System.out.print("Please enter the patient ID: ");
						String patientID = scanner.nextLine();
						Patient patient = this.searchPatient(Integer.parseInt(patientID));
						if (patient != null) {
							System.out.print("Please enter a disease: ");
							String disease = scanner.nextLine();
							this.addDisease(patient, disease);
							boolean exit2 = true;
							Prescription prescription = new Prescription(this, patient);
							boolean checkPrescription = false;
							while (exit2) {
								System.out.println("\n 1 - Add a medicine");
								System.out.println(" 0 - Return back");
								System.out.print("Selection: ");
								String selection = scanner.nextLine();
								switch (selection) {
									case "1":
										try{
											for(Medicine medicine: this.getHospital().getAllMedicines()){
												System.out.println(" -> ID: " + medicine.getId() + " | Name: " + medicine.getName());
											}
											System.out.print("Please enter medicine ID: ");
											String medicineID = scanner.nextLine();
											Medicine temp = null;
											for(Medicine medicine: this.getHospital().getAllMedicines()){
												if(medicine.getId() == Integer.parseInt(medicineID)){
													temp = medicine;
												}
											}
											if(temp != null){
												System.out.print("Please enter quantity: ");
												String quantity = scanner.nextLine();
												this.addPrescription(prescription, new Medicine(temp.getId(), temp.getName(), Integer.parseInt(quantity),temp.getCost()));
												checkPrescription = true;
											}else{
												System.out.print(" WARNING: The medicine already exists in the prescription. ");
											}

										} catch (Exception e) {
											System.out.println("Please try again and enter valid value.");
										}
										break;
									case "0":
										exit2 = false;
										break;
									default:
										System.out.println("Please enter valid value.");
										break;
								}

							}
							if (checkPrescription)
								patient.getPrescriptions().add(prescription);
							for (Appointment appointment : this.appointments) {
								if (appointment.getPatient().equals(patient) &&
										appointment.getDoctor().equals(this)) {
									appointment.setAwake(false);
									break;
								}
							}
							exit = false;
						} else {
							System.out.println(" ! Invalid Patient ID.");
						}
					}
					break;
				case "3":
					exit = true;
					while(exit){
						System.out.println();
						/*if(!this.viewPatientList()){
							break;
						}*/
						count =0;
						for (Appointment appointment : getHospital().getAppointments()) {
							if (appointment.getDoctor().getPersonalData().getId()==getPersonalData().getId()) {
								System.out.println("ID: "+appointment.getPatient().getPersonalData().getId()+" NAME: "+appointment.getPatient().getPersonalData().getName());
								count ++ ;
							}
						}
						if (count==0) {
							System.out.println("There is no active appointment right now.");
							break;
						}
						System.out.println();
						System.out.print("Please enter the patient ID: ");
						String patientID = scanner.nextLine();
						Patient patient = this.searchPatient(Integer.parseInt(patientID));
						if(patient != null){
							this.viewPatientHistory(patient);
							exit = false;
						} else{
							System.out.println(" ! WARNING: Invalid Patient ID.");
						}
					}
					break;
				case "4":
					try {
						GregorianCalendar time = this.getHospital().getAdmin().chooseAnAppointmentTime();
						Appointment newApp = new Appointment(this, new Patient(new PersonalClass("Free", "Time", "unused", "unused"), getHospital()), time);
						addAppointment(newApp);
					} catch (Exception e) {
						System.out.println("Please try again and enter valid value.");
					}
					break;
				case "5":
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
				case "6":
					exit = true;
					while(exit){
						System.out.println();
						System.out.println(" * Doctors * ");
						for (Doctor doctor: this.getHospital().getDoctors()) {
							if(!doctor.equals(this)){
								System.out.println(" -> " + doctor);
							}
						}
						System.out.println(" * Pharmacists * ");
						for (Pharmacist pharmacist: this.getHospital().getPharmacists()) {
							System.out.println(" -> " + pharmacist);
						}
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						user = null;
						try{
							for(Doctor doctor: this.getHospital().getDoctors()){
								if(doctor.getPersonalData().getId() == Integer.parseInt(ID)
									&& this.getPersonalData().getId() != Integer.parseInt(ID)){
									user = doctor;
									break;
								}
							}
							if(user == null ){
								for(Pharmacist pharmacist: this.getHospital().getPharmacists()){
									if(pharmacist.getPersonalData().getId() == Integer.parseInt(ID)){
										user = pharmacist;
										break;
									}
								}
							}
							if(user != null){
								this.addSuggestion(user);
								exit = false;
							} else{
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e){
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				case "7":
					exit = true;
					while(exit){
						if(this.showSuggestions()){
							System.out.print("Please enter the ID: ");
							String ID = scanner.nextLine();
							user = null;
							try{
								for( Doctor doctor: this.getHospital().getDoctors()){
									if(doctor.getPersonalData().getId() == Integer.parseInt(ID)
											&& this.getPersonalData().getId() != Integer.parseInt(ID)){
										user = doctor;
										break;
									}
								}
								if(user == null ){
									for(Pharmacist pharmacist: this.getHospital().getPharmacists()){
										if(pharmacist.getPersonalData().getId() == Integer.parseInt(ID)){
											user = pharmacist;
											break;
										}
									}
								}
								if(user != null &&
										this.getHospital().getRelatedUsers().isEdge(this, user)){
									this.removeSuggestion(user);
									exit = false;
								} else{
									System.out.println(" ! Invalid ID.");
								}
							} catch (Exception e){
								System.out.println("Please try again and enter valid value.");
							}
						} else{
							exit = false;
						}
					}
					break;
				case "8":
					this.showSuggestions();
					break;
				case "0":
					System.out.println(" --> Log out... <-- ");
					break;
				default:
					System.out.println(" WARNING: Please enter a valid value.");
					break;
			}
		}while(!choice.equals("0"));
	}
}
