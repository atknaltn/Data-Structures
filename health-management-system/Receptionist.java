import java.util.*;
import java.util.concurrent.ConcurrentSkipListSet;
/**
 * A class to keep Receptionist's information and
 * actions
 */
public class Receptionist extends User{
	//Methods
	/**
	 * Create Receptionist object with given data
	 * @param person personal data
	 * @param hospital hospital
	 */
	Receptionist(PersonalClass person, Hospital hospital){
		super(person, hospital);
		//Create new receptionist	
	}
	/**
	 * To add a patient to the hospital
	 * @param patient to add
	 * @return true if it can add,
	 * 			otherwise false
	 */
	public boolean addPatient(Patient patient){
		if( getHospital().getPatients().contains(patient) ){
			System.out.println("WARNING: Cannot add. The patient already exists.");
			return false;
		}
		getHospital().getPatients().add(patient);
		return true;
	}
	/**
	 * Add an appointment to the hospital
	 * @param appointment to add
	 * @return true if it can add,
	 * 			otherwise false
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
		return true;
	}
	/**
	 * The function to use selecting the appropriate time
	 * for an appointment
	 *
	 * @return GregorianCalender after manipulating it's day and hours
	 * within the given interval
	 */
	private GregorianCalendar chooseAnAppointmentTime() {
		int[][] workhours = {{9, 0}, {9, 30}, {10, 0}, {10, 30}, {11, 0}, {11, 30}, {12, 0}, {12, 30}, {13, 30}, {14, 0}
				, {14, 30}, {15, 0}, {15, 30}, {16, 0}, {16, 30}};
		GregorianCalendar now = new GregorianCalendar();
		int year, day, hour, minute;
		Scanner scanner = new Scanner(System.in);
		String choice = null;
		do {
			now = new GregorianCalendar();
			System.out.println("Please select a day from by entering their code numbers: ");
			for (int i = 0; i < 5; i++) {
				now.add(Calendar.DAY_OF_MONTH, 1);
				if (now.get(Calendar.DAY_OF_WEEK) == 7 || now.get(Calendar.DAY_OF_WEEK) == 1) {
					now.add(Calendar.DAY_OF_MONTH, 1);
					i--;
				} else {
					System.out.print((i+1) + "." + now.get(Calendar.DAY_OF_MONTH) + " ");
				}
			}
			choice = scanner.nextLine();
		} while (!(0 <= Integer.parseInt(choice) && Integer.parseInt(choice) < 6));

		now = new GregorianCalendar();
		now.add(Calendar.DAY_OF_MONTH, Integer.parseInt(choice));
		do {
			System.out.println("\nPlease select an hour");
			for (int i = 0; i < workhours.length; i++) {
				System.out.print("  " + i + ". " + workhours[i][0] + "  " + workhours[i][1]);
				if (workhours[i][1] == 0) {
					System.out.print('0');
				}
			}
			choice = scanner.nextLine();
		} while (!(0 <= Integer.parseInt(choice) && Integer.parseInt(choice) < 16));
		now.set(Calendar.HOUR_OF_DAY, workhours[Integer.parseInt(choice)][0]);
		now.set(Calendar.MINUTE, workhours[Integer.parseInt(choice)][1]);
		return now;
	}
	/**
	 * The function that used for seeing all the registered
	 * doctors of the system
	 * */
	private void viewDoctorsList() {
		ConcurrentSkipListSet<Doctor> temp = new ConcurrentSkipListSet<>();
		Iterator<Doctor> t_iterator = temp.iterator();
		Doctor t_doctor = null;
		while (t_iterator.hasNext()){
			t_doctor=t_iterator.next();
			System.out.println("  "+t_doctor.getPersonalData().getId()+"  "+t_doctor.toString());
		}
		boolean exit = true;
		Scanner scanner = new Scanner(System.in);
		String choice = "";
		t_iterator = temp.iterator();
		t_doctor=null;
		do {
			System.out.println("For search please enter 1," +
					"\nFor viewing searched doctors appointments enter 2"+
					"\nFor exit press  0");
			choice = scanner.nextLine();
			switch (choice){
				case "1":
					System.out.println();
					System.out.print("Please enter the patient ID: ");
					String doctorID = scanner.nextLine();
					t_doctor = this.searchDoctor(Integer.parseInt(doctorID));
					if (t_doctor!=null)
						System.out.print("Doctor is successfully selected.");
					break;
				case "2":
					if(t_doctor!=null){
						viewDoctorHistory(t_doctor);
					}
					break;
				case "0":
					choice = "0";
					break;
				default:
					System.out.println("For search please enter a valid input");
					break;
			}
		}while (!choice.equals("0"));
	}
	/**
	 * This function takes a doctor and prints
	 *	the attached appointments
	 * @param doctor is a a Doctor
	 * */
	private void viewDoctorHistory(Doctor doctor) {
		PriorityQueue<Appointment> temp = this.getHospital().getAppointments();
		Iterator<Appointment> t_iterator = temp.iterator();
		Appointment t_appointment = null;
		GregorianCalendar now = new GregorianCalendar();
		while (t_iterator.hasNext()){
			t_appointment=t_iterator.next();
			if (t_appointment.getDoctor()==doctor&&t_appointment.getGregorianCalendar().after(now))
				t_appointment.print();
		}
	}
	/**
	 * This function takes an id of a doctor and returns
	 *	the doctor with the given id number
	 * @param doctorID is an ID of a doctor
	 * @return the doctor if found or null
	 * */
	private Doctor searchDoctor(int doctorID) {
		ConcurrentSkipListSet<Doctor> temp = getHospital().getDoctors();
		Iterator<Doctor> t_iterator = temp.iterator();
		Doctor doctor=null;
		while (t_iterator.hasNext()){
			doctor=t_iterator.next();
			if (doctor.getPersonalData().getId()==doctorID)
				return doctor;
		}
		return null;
	}
	/**
	 * The function that used for seeing all the registered
	 * patients of the system
	 * */
	private void viewPatientList() {
		TreeSet<Patient> temp = getHospital().getPatients();
		Iterator<Patient> t_iterator = temp.iterator();
		Patient patient=null;
		while (t_iterator.hasNext()){
			System.out.println(t_iterator.next().print());
		}
		boolean exit = true;
		Scanner scanner = new Scanner(System.in);
		String choice = "";
		t_iterator = temp.iterator();
		do {
			System.out.println("For search please enter 1," +
					"\nFor viewing searched patients history enter 2"+
					"\nFor exit press  0");
			choice = scanner.nextLine();
			switch (choice){
				case "1":
					System.out.println();
					System.out.print("Please enter the patient ID: ");
					String patientID = scanner.nextLine();
					patient = this.searchPatient(Integer.parseInt(patientID));
					if (patient!=null)
						System.out.print("Patient is successfully selected.");
					break;
				case "2":
					if(patient!=null){
						viewPatientHistory(patient);
					}
					break;
				case "0":
					choice = "0";
					break;
				default:
					System.out.println("For search please enter a valid input");
					break;
			}
		}while (!choice.equals("0"));
	}
	/**
	 * This function takes a patient and prints
	 *	the attached appointments
	 * @param patient is a a patient
	 * */
	private void viewPatientHistory(Patient patient) {
		PriorityQueue<Appointment> temp = this.getHospital().getAppointments();
		Iterator<Appointment> t_iterator = temp.iterator();
		Appointment t_appointment = null;
		while (t_iterator.hasNext()){
			t_appointment=t_iterator.next();
			if (t_appointment.getPatient()==patient)
				t_appointment.print();
		}
	}
	/**
	 * This function takes an id of a patient and returns
	 *	the patient with the given id number
	 * @param patientID is an ID of a patient
	 * @return the patient if found or null
	 * */
	private Patient searchPatient(int patientID) {
		TreeSet<Patient> temp = getHospital().getPatients();
		Iterator<Patient> t_iterator = temp.iterator();
		Patient patient=null;
		while (t_iterator.hasNext()){
			patient=t_iterator.next();
			if (patient.getPersonalData().getId()==patientID)
				return patient;
		}
		return null;
	}
	/**
	 * The menu for the Receptionist class and users
	 * */
	public void menu(){
		System.out.println("\n Welcome Receptionist " + this.getPersonalData().getName() + " " + this.getPersonalData().getSurname());
		String choice = "";
		boolean exit = true;
		Scanner scanner = new Scanner(System.in);
		do{
			System.out.println("\n 1 - View patient list");
			System.out.println(" 2 - Add a patient");
			System.out.println(" 3 - View doctors list");
			System.out.println(" 4 - Create an appointment");
			System.out.println(" 5 - Request for free time");
			System.out.println(" 6 - Edit profile");
			System.out.println(" 7 - Deactivate an appointment ");
			System.out.println(" 0 - Log out");
			System.out.print("Please select: ");
			choice = scanner.nextLine();
			switch (choice) {
				case "1":
					System.out.println();
					this.viewPatientList();
					break;
				case "2":
					System.out.print("Enter patient name: ");
					String name = scanner.nextLine();
					System.out.print("Enter patient surname: ");
					String surname = scanner.nextLine();
					System.out.print("Enter patient mail: ");
					String mail = scanner.nextLine();
					System.out.print("Enter patient password: ");
					String password = scanner.nextLine();
					PersonalClass newPatientPersonal = new PersonalClass(name, surname, mail, password);
					Patient newPatient = new Patient(newPatientPersonal, getHospital());
					addPatient(newPatient);
					break;
				case "3":
					System.out.println();
					this.viewDoctorsList();
					break;
				case "4": {
					boolean exit1 = true;
					while (exit1) {
						System.out.println();
						System.out.println(" * Patients * ");
						this.getHospital().getAdmin().patientList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Patient user1 = null;
						try {
							for (Patient patient : this.getHospital().getPatients()) {
								if (patient.getPersonalData().getId() == Integer.parseInt(ID)) {
									user1 = patient;
									break;
								}
							}
							if (user1 != null) {
								boolean exit2 = true;
								while (exit2) {
									System.out.println();
									System.out.println(" * Doctors * ");
									this.getHospital().getAdmin().doctorList();
									System.out.print("Please enter the ID: ");
									ID = scanner.nextLine();
									Doctor user2 = null;
									try {
										for (Doctor doctor : this.getHospital().getDoctors()) {
											if (doctor.getPersonalData().getId() == Integer.parseInt(ID)) {
												user2 = doctor;
												break;
											}
										}
										if (user2 != null) {
											GregorianCalendar time = chooseAnAppointmentTime();
											Appointment newApp = new Appointment(user2, user1, time);
											addAppointment(newApp);
											exit2 = false;
										} else {
											System.out.println(" ! Invalid ID.");
										}
									} catch (Exception e) {
										System.out.println("Please try again and enter valid value.");
									}
								}
								exit1 = false;
								exit2 = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "5":
					System.out.print("You have 1 hour break. Have a good time.");
					choice = "0";
					break;
				case "6":
					//Edit profile
					System.out.print("Enter new name: ");
					name = scanner.nextLine();
					System.out.print("Enter new surname: ");
					surname = scanner.nextLine();
					System.out.print("Enter new mail: ");
					mail = scanner.nextLine();
					System.out.print("Enter new password: ");
					password = scanner.nextLine();
					this.editProfile(this, mail, name, surname, password);
					break;
				case "7":
					boolean exit1 = true;
					while (exit1) {
						System.out.println();
						System.out.println(" * Patients * ");
						this.getHospital().getAdmin().patientList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Patient user1 = null;
						try {
							for (Patient patient : this.getHospital().getPatients()) {
								if (patient.getPersonalData().getId() == Integer.parseInt(ID)) {
									user1 = patient;
									break;
								}
							}
							Iterator<Appointment> t_iterator = this.getHospital().getAppointments().iterator();
							Appointment t_appointment = null;
							int i = 0;

							while (t_iterator.hasNext()){
								t_appointment=t_iterator.next();
								if (t_appointment.getPatient()==user1
								&& t_appointment.isAwake()){
									System.out.print((i+1)+". ");
									i++;
									t_appointment.print();
								}
							}
							i = 0;
							System.out.println("Please select an appointment: ");
							String selection = scanner.nextLine();
							while (t_iterator.hasNext()){
								t_appointment=t_iterator.next();
								if (t_appointment.getPatient()==user1){
									if ((i+1)==Integer.parseInt(selection)){
										t_iterator.next().setAwake(false);
										break;
									}
									System.out.print((i+1)+". ");
									i++;
									t_appointment.print();
								}
							}
							exit1 = false;
						} catch(Exception e){
							System.out.println("Please try again and enter valid value.");
						}
					}
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