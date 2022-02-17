import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
/**
 * A Class to hold Admin's information
 * and actions
 */
public class Admin extends User {
	private final Hospital hospital;
	private PersonalClass adminData;
	/**
	 * To create Admin object
	 * @param data data
	 * @param hospital the hospital
	 */
	public Admin(PersonalClass data, Hospital hospital) {
		super(data, hospital);
		this.hospital = hospital;
		this.adminData = data;
	}
	/**
	 * Adds given doctor to the system.
	 * @param doctor Doctor
	 * @return Always True
	 */
	public boolean addDoctor(Doctor doctor) {
		return hospital.getAllUsers().add(doctor) && hospital.getDoctors().add(doctor);
	}
	/**
	 * Adds given patient to the system.
	 * @param patient Patient
	 * @return
	 */
	public boolean addPatient(Patient patient) {
		return hospital.getAllUsers().add(patient) && hospital.getPatients().add(patient);
	}
	/**
	 * Adds pharmacist to the system
	 * @param pharmacist Pharmacist
	 * @return
	 */
	public boolean addPharmacist(Pharmacist pharmacist) {
		hospital.getAllUsers().add(pharmacist);
		hospital.getPharmacists().add(pharmacist);
		return true;
	}
	/**
	 * Adds receptionist to the system
	 * @param receptionist Receptionist
	 * @return
	 */
	public boolean addReceptionist(Receptionist receptionist) {
		hospital.getAllUsers().add(receptionist);
		hospital.getReceptionists().add(receptionist);
		return true;
	}
	/**
	 * Adds appointment to the system
	 * @param appointment Appointment
	 * @return
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
	 * Removes the doctor from the system
	 * @param doctor Doctor
	 * @return
	 */
	public boolean removeDoctor(Doctor doctor) {
		return hospital.getAllUsers().remove(doctor) && hospital.getDoctors().remove(doctor);
	}
	/**
	 * Removes the patient from the system
	 * @param patient Patient
	 * @return
	 */
	public boolean removePatient(Patient patient) {
		return hospital.getAllUsers().remove(patient) && hospital.getPatients().remove(patient);
	}
	/**
	 * Removes the pharmacist from the system
	 * @param pharmacist Pharmacist
	 * @return
	 */
	public boolean removePharmacist(Pharmacist pharmacist) {

		return hospital.getAllUsers().remove(pharmacist) && hospital.getPharmacists().remove(pharmacist);
	}
	/**
	 * Removes the receptionist from the system
	 * @param receptionist Receptionist
	 * @return
	 */
	public boolean removeReceptionist(Receptionist receptionist) {
		return hospital.getAllUsers().remove(receptionist) && hospital.getReceptionists().remove(receptionist);
	}
	/**
	 * Edits given user's profile with given data
	 * @param user User
	 * @param mail mail
	 * @param name name
	 * @param surname surname
	 * @param password password
	 */
	public void editUserProfile(User user, String mail, String name, String surname, String password) {
		User temp = user;
		user.getPersonalData().setMail(mail);
		user.getPersonalData().setName(name);
		user.getPersonalData().setPassword(password);
		user.getPersonalData().setSurname(surname);
		System.out.println(" The profile is edited.");
	}

	/**
	 * To view doctor's list
	 */
	public void doctorList() {
		for (Doctor doctor : hospital.getDoctors()) {
			System.out.println(doctor);
		}
	}
	/**
	 * To view patient's list
	 */
	public void patientList() {
		for (Patient patient : getHospital().getPatients()) {
			System.out.println(patient.print());
		}
	}
	/**
	 * To view pharmacist's list
	 */
	public void pharmacistList() {
		for (Pharmacist pharmacist : getHospital().getPharmacists()) {
			System.out.println(pharmacist);
		}
	}
	/**
	 * To view receptionist's list
	 */
	public void ReceptionistList() {
		for (Receptionist receptionist : getHospital().getReceptionists()) {
			System.out.println(receptionist);
		}
	}
	/**
	 * To view appointment's list
	 */
	public void appointmentList() {
		if (hospital.getAppointments().size() == 0)
			System.out.println("There are no appointments right now!");
		for (Appointment appointment : hospital.getAppointments()){
			System.out.println("---");
			System.out.println(appointment.getDoctor() + "\n"
					+ "Patient: " + appointment.getPatient().getPersonalData().getName()
					+ " " + appointment.getPatient().getPersonalData().getName()
					+ "\nTime: " + appointment.getGregorianCalendar().getTime());
		}
	}
	/**
	 * To view prescription's list
	 */
	public void prescriptionList() {
		if (hospital.getPrescriptions().size() == 0)
			System.out.println("There are no prescriptions in the hospital depo!");
		for (int i = 0; i < hospital.getPrescriptions().size(); i++)
			System.out.println(hospital.getPrescriptions().get(i));
	}

	public void showPatientHistoryData(Patient patient) {
		System.out.println("Patient information: ");
		System.out.println(patient);
		System.out.println("Patient's appointment history: ");
		int count=0;
		for (Appointment app : getHospital().getAppointments()) {
			if (app.getPatient().getPersonalData().getId()==patient.getPersonalData().getId()) {
				System.out.println(app);
				count++;
			}
		}
		if (count==0) {
			System.out.println("You currently do not have an appointment.");
		}
	}

	@Override
	public String toString() {
		return String.format("\nName: %s\nSurname: %s\nMail: %s\nUser ID: %d\n",
				adminData.getName(), adminData.getSurname(), adminData.getMail(), adminData.getId());
	}
	/**
	 * The function to use selecting the appropriate time
	 * for an appointment
	 *
	 * @return GregorianCalender after manipulating it's day and hours
	 * within the given interval
	 */
	public GregorianCalendar chooseAnAppointmentTime() {
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
	 * menu for admin
	 */
	public void menu() {
		System.out.println("\n Welcome Admin " + this.getPersonalData().getName() + " " + this.getPersonalData().getSurname());
		String choice = "";
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\n 1 - Add patient");
			System.out.println(" 2 - Remove patient ");
			System.out.println(" 3 - Add doctor");
			System.out.println(" 4 - Remove doctor");
			System.out.println(" 5 - Add Pharmacist");
			System.out.println(" 6 - Remove Pharmacist");
			System.out.println(" 7 - Add Receptionist");
			System.out.println(" 8 - Remove Receptionist");
			System.out.println(" 9 - Create appointment");
			System.out.println("10 - View Patient History");
			System.out.println("11 - View Patient List");
			System.out.println("12 - View Doctor List");
			System.out.println("13 - View Pharmacist List");
			System.out.println("14 - View Receptionist List");
			System.out.println("15 - View All Related Users");
			System.out.println("16 - View Prescription List");
			System.out.println("17 - Edit admin data");
			System.out.println("18 - Edit a personnel's data");
			System.out.println("19 - View All Appointment List");
			System.out.println(" 0 - Log out");
			System.out.print("Please select: ");
			choice = scanner.nextLine();
			switch (choice) {
				case "1": {
					System.out.print("Please enter patient name: ");
					String name = scanner.nextLine();
					System.out.print("Please enter patient surname: ");
					String surname = scanner.nextLine();
					System.out.print("Please enter patient mail: ");
					String mail = scanner.nextLine();
					System.out.print("Please enter patient password: ");
					String password = scanner.nextLine();
					this.addPatient(new Patient(new PersonalClass(name, surname, mail, password), this.getHospital()));
					break;
				}
				case "2": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						System.out.println(" * Patients * ");
						this.patientList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Patient user = null;
						try {
							for (Patient patient : this.getHospital().getPatients()) {
								if (patient.getPersonalData().getId() == Integer.parseInt(ID)) {
									user = patient;
									break;
								}
							}
							if (user != null) {
								this.removePatient(user);
								exit = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "3": {
					System.out.print("Please enter doctor name: ");
					String name = scanner.nextLine();
					System.out.print("Please enter doctor surname: ");
					String surname = scanner.nextLine();
					System.out.print("Please enter doctor mail: ");
					String mail = scanner.nextLine();
					System.out.print("Please enter doctor password: ");
					String password = scanner.nextLine();
					System.out.print("Please enter doctor specialty: ");
					String specialty = scanner.nextLine();
					this.addDoctor(new Doctor(new PersonalClass(name, surname, mail, password), this.getHospital(), specialty));
					break;
				}
				case "4": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						System.out.println(" * Doctors * ");
						this.doctorList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Doctor user = null;
						try {
							for (Doctor doctor : this.getHospital().getDoctors()) {
								if (doctor.getPersonalData().getId() == Integer.parseInt(ID)) {
									user = doctor;
									break;
								}
							}
							if (user != null) {
								this.removeDoctor(user);
								exit = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "5": {
					System.out.print("Please enter pharmacist name: ");
					String name = scanner.nextLine();
					System.out.print("Please enter pharmacist surname: ");
					String surname = scanner.nextLine();
					System.out.print("Please enter pharmacist mail: ");
					String mail = scanner.nextLine();
					System.out.print("Please enter pharmacist password: ");
					String password = scanner.nextLine();
					System.out.print("Please enter pharmacy name: ");
					String pharmacy = scanner.nextLine();
					this.addPharmacist(new Pharmacist(new PersonalClass(name, surname, mail, password), pharmacy, this.getHospital()));
					break;
				}
				case "6": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						System.out.println(" * Pharmacist * ");
						this.pharmacistList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Pharmacist user = null;
						try {
							for (Pharmacist pharmacist : this.getHospital().getPharmacists()) {
								if (pharmacist.getPersonalData().getId() == Integer.parseInt(ID)) {
									user = pharmacist;
									break;
								}
							}
							if (user != null) {
								this.removePharmacist(user);
								exit = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "7": {
					System.out.print("Please enter receptionist name: ");
					String name = scanner.nextLine();
					System.out.print("Please enter receptionist surname: ");
					String surname = scanner.nextLine();
					System.out.print("Please enter receptionist mail: ");
					String mail = scanner.nextLine();
					System.out.print("Please enter receptionist password: ");
					String password = scanner.nextLine();
					this.addReceptionist(new Receptionist(new PersonalClass(name, surname, mail, password), this.getHospital()));
					break;
				}
				case "8": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						System.out.println(" * Receptionist * ");
						this.ReceptionistList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Receptionist user = null;
						try {
							for (Receptionist receptionist : this.getHospital().getReceptionists()) {
								if (receptionist.getPersonalData().getId() == Integer.parseInt(ID)) {
									user = receptionist;
									break;
								}
							}
							if (user != null) {
								this.removeReceptionist(user);
								exit = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "9": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						System.out.println(" * Patients * ");
						this.patientList();
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
									this.doctorList();
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
								exit = false;
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
				case "10": {
					boolean exit = true;
					while(exit){
						System.out.println();
						this.patientList();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						Patient user = null;
						try {
							for (Patient patient : this.getHospital().getPatients()) {
								if (patient.getPersonalData().getId() == Integer.parseInt(ID)) {
									user = patient;
									break;
								}
							}
							if(user != null){
								user.showHistory();
								exit = false;
							} else{
								System.out.println(" ! WARNING: Invalid Patient ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
					}
					break;
				}
				case "11": {
					this.patientList();
					break;
				}
				case "12": {
					this.doctorList();
					break;
				}
				case "13": {
					this.pharmacistList();
					break;
				}
				case "14": {
					this.ReceptionistList();
					break;
				}
				case "15": {
					System.out.println(this.getHospital().getRelatedUsers());
					break;
				}
				case "16": {
					this.prescriptionList();
					break;
				}
				case "17": {
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
				}
				case "18": {
					boolean exit = true;
					while (exit) {
						System.out.println();
						this.patientList();
						this.doctorList();
						this.ReceptionistList();
						this.pharmacistList();
						System.out.println();
						System.out.print("Please enter the ID: ");
						String ID = scanner.nextLine();
						User user = null;
						try {
							for (Doctor doctor : this.getHospital().getDoctors()) {
								if (doctor.getPersonalData().getId() == Integer.parseInt(ID)
										&& this.getPersonalData().getId() != Integer.parseInt(ID)) {
									user = doctor;
									break;
								}
							}
							if (user == null) {
								for (Pharmacist pharmacist : this.getHospital().getPharmacists()) {
									if (pharmacist.getPersonalData().getId() == Integer.parseInt(ID)) {
										user = pharmacist;
										break;
									}
								}
							}
							if (user == null) {
								for (Patient patient : this.getHospital().getPatients()) {
									if (patient.getPersonalData().getId() == Integer.parseInt(ID)) {
										user = patient;
										break;
									}
								}
							}
							if (user == null) {
								for (Receptionist receptionist : this.getHospital().getReceptionists()) {
									if (receptionist.getPersonalData().getId() == Integer.parseInt(ID)) {
										user = receptionist;
										break;
									}
								}
							}
							if (user != null) {
								System.out.print("Enter new name: ");
								String name = scanner.nextLine();
								System.out.print("Enter new surname: ");
								String surname = scanner.nextLine();
								System.out.print("Enter new mail: ");
								String mail = scanner.nextLine();
								System.out.print("Enter new password: ");
								String password = scanner.nextLine();
								this.editProfile(user, mail, name, surname, password);
								exit = false;
							} else {
								System.out.println(" ! Invalid ID.");
							}
						} catch (Exception e) {
							System.out.println("Please try again and enter valid value.");
						}
						break;
					}
				}
				case "19":{
					this.appointmentList();
				}
				case "0":
					break;
				default:
					System.out.println(" WARNING: Please enter a valid value.");
					break;
			}
		} while (!choice.equals("0"));
	}
}