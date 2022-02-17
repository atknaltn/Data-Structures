import java.util.*;
/**
 * To hold patient's data and actions
 */
public class Patient extends User{
    private int age;
    private int weight;
    private int height;
    private String bloodType;
    private ArrayList<String> diseases;
    private Stack<Prescription> prescriptions;
    private ArrayList<Prescription> pastPrescriptions;
    /**
     * It creates a patient with given parameters.
     * @param data Patient's personal data
     * @param hospital Hospital
     */
    public Patient(PersonalClass data, Hospital hospital){
        super(data, hospital);
        this.age=-1;
        this.weight=-1;
        this.height=-1;
        this.bloodType="Unknown";
        prescriptions = new Stack<>();
        diseases = new ArrayList<>(10);
        pastPrescriptions = new ArrayList<>();
    }

    /**
     * It creates a patient with given parameters.
     * @param age Patient's age.
     * @param weight Patient's weight.
     * @param height Patient's height.
     * @param bloodType Patient's bloodtype.
     * @param data Patient's personal age.
     * @param hospital Hospital
     */
    public Patient(int age, int weight, int height, String bloodType,PersonalClass data, Hospital hospital) {
        super(data,hospital);
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.bloodType = bloodType;
        prescriptions = new Stack<>();
        diseases = new ArrayList<>(10);
        pastPrescriptions = new ArrayList<>();
    }

    @Override
    public String toString() {
        return String.format("Name: %s\nSurname: %s\nAge: %d\nWeight: %d\nHeight: %d\nBlood Type: %s\n",
                getPersonalData().getName(),getPersonalData().getSurname(),
                getAge(),getWeight(),getHeight(),getBloodType());
    }
    /**
     * Gets Patient's age.
     * @return age
     */
    public int getAge() { return age; }
    /**
     * It changes patient's age.
     * @param age
     */
    public void setAge(int age) { this.age = age;}
    /**
     * Gets Patient's weight.
     * @return weight
     */
    public int getWeight() { return weight; }
    /**
     * It changes patient's weight.
     * @param weight
     */
    public void setWeight(int weight) { this.weight = weight; }
    /**
     * Gets Patient's height.
     * @return height
     */
    public int getHeight() { return height; }
    /**
     * It changes patient's height.
     * @param height
     */
    public void setHeight(int height) { this.height = height; }
    /**
     * Gets Patient's bloodtype.
     * @return bloodtype.
     */
    public String getBloodType() { return bloodType; }
    /**
     * It changes patient's bloodtype.
     * @param bloodtype
     */
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }
    /**
     * It returns all the diseases the patient's have.
     * @return diseases
     */
    public ArrayList<String> getDiseases(){
        return diseases;
    }
    /**
     * It returns all the prescriptions the patient's have.
     * @return diseases
     */
    public Stack<Prescription> getPrescriptions(){
        return prescriptions;
    }
    /**
     * It prints personal data of patient.
     * @return
     */
    public String print(){
        return super.toString();
    }
    /**
     * Shows the patient's past and present diseases and prescriptions.
     */
    public void showHistory(){
        System.out.println(" * Personal Data * ");
        System.out.println(this.print());
        System.out.println(" -> Age: " + age);
        System.out.println(" -> Weight: " + weight);
        System.out.println(" -> Height: " + height);
        System.out.println(" -> Blood type: " + bloodType);
        System.out.println(" * Diseases * ");
        if(diseases.isEmpty()){
            System.out.println(" -> The patient does not have any disease.");
        }else{
            for(String disease: diseases){
                System.out.println(" -> " + disease);
            }
        }
        System.out.println(" * Prescriptions * ");
        if(prescriptions.isEmpty()){
            System.out.println(" -> The patient does not have any prescription.");
        }else{
            for(Prescription prescription: prescriptions){
                System.out.println(" -> " + prescription);
            }
        }
    }
    /**
     * It calculates total cost for all prescriptions.
     * @param p pharmacist
     * @return total cost
     */
    public double totalPrescriptionCost(Pharmacist p){
        double total=0;
        for (int i = 0; i < getPrescriptions().size(); i++) {
            total += getPrescriptions().get(i).medicineCost(p,getPrescriptions().get(i).getDoctor());
        }
        return total;
    }
    /*
     * sees a specific user's suggestions
     * @param user the user(doctor or pharmacist)
     */
    public void showSuggestions(User user){
        if(user instanceof Doctor || user instanceof Pharmacist)
            System.out.println(getHospital().getRelatedUsers().print(user));
        else
            throw new IllegalArgumentException();
    }
    /**
     * Shows Patient's special menu. It provides user to perform patient operations.
     */
    public void menu(){
        boolean exit = true,exit2=true,exit3=true;
        int selection,selection2,selection3,id;
        Scanner sc1 = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        String input;
        while (exit) {
            System.out.println("\nWelcome " + this.getPersonalData().getName()+" "+this.getPersonalData().getSurname());
            System.out.println("1. Show Profile");
            System.out.println("2. Edit Profile");
            System.out.println("3. Show Prescriptions");
            System.out.println("4. Take an Appointment");
            System.out.println("5. Show Appointments");
            System.out.println("6. Order Medicine From Pharmacy");
            System.out.println("7. Exit");
            System.out.print("Selection: ");
            selection=sc1.nextInt();
            switch (selection) {
                case 1:
                    System.out.println(this);
                    break;
                case 2:
                    System.out.println("\nEdit Profile");
                    System.out.print("Name: ");
                    input=sc2.nextLine();
                    getPersonalData().setName(input);
                    System.out.print("Surname: ");
                    input=sc2.nextLine();
                    getPersonalData().setSurname(input);
                    System.out.print("Age: ");
                    input=sc2.nextLine();
                    setAge(Integer.parseInt(input));
                    System.out.print("Weight: ");
                    input=sc2.nextLine();
                    setWeight(Integer.parseInt(input));
                    System.out.print("Height: ");
                    input=sc2.nextLine();
                    setHeight(Integer.parseInt(input));
                    System.out.print("Blood Type: ");
                    input=sc2.nextLine();
                    setBloodType(input);
                    System.out.println("Done!");
                    break;
                case 3:
                    System.out.println("\n");
                    for (int i = 0; i < prescriptions.size(); i++) {
                        System.out.println(prescriptions.get(i));
                    }
                    break;
                case 4:
                    exit3=true;
                    while (exit3) {
                        System.out.println("\n1. Search doctor by policlinic");
                        System.out.println("2. See all doctors");
                        System.out.println("3. Exit");
                        System.out.print("Selection: ");
                        selection3=sc2.nextInt();
                        switch (selection3) {
                            case 1:
                                System.out.print("\nEnter Policlinic Name: ");
                                input=sc2.nextLine();
                                input=sc2.nextLine();
                                ArrayList<Doctor> tempList =getHospital().findDoctorBySpeciality(input);
                                if (tempList==null) {
                                    System.out.println("\n-> There is no avaliable doctor in this policlinic.");
                                }else{
                                    for (Doctor iter : tempList) {
                                        System.out.println(iter);
                                    }
                                    System.out.print("Enter Doctor ID you want to take an appointment: ");
                                    id = sc2.nextInt();
                                    Doctor tempDoc = null;
                                    for (Doctor doctor : tempList) {
                                        if (doctor.getPersonalData().getId()==id){
                                            tempDoc=doctor;
                                        }
                                    }
                                    if (tempDoc==null)
                                        System.out.println("-> Invalid ID.");
                                    else{
                                        GregorianCalendar time =  this.getHospital().getAdmin().chooseAnAppointmentTime();
                                        Appointment newApp = new Appointment(tempDoc, this, time);
                                        this.getHospital().getAdmin().addAppointment(newApp);
                                        tempDoc.addAppointment(newApp);
                                    }
                                }
                                break;
                            case 2:
                                getHospital().getAdmin().doctorList();
                                System.out.print("Enter Doctor ID you want to take an appointment: ");
                                id = sc2.nextInt();
                                Doctor tempDoc = null;
                                for (Doctor doctor : getHospital().getDoctors()) {
                                    if (doctor.getPersonalData().getId()==id){
                                        tempDoc=doctor;
                                    }
                                }
                                if (tempDoc==null)
                                    System.out.println("-> Invalid ID.");
                                else{
                                    GregorianCalendar time =  this.getHospital().getAdmin().chooseAnAppointmentTime();
                                    Appointment newApp = new Appointment(tempDoc, this, time);
                                    this.getHospital().getAdmin().addAppointment(newApp);
                                }
                                break;
                            case 3:
                                exit3=false;
                                break;
                            default:
                                System.err.println("WARNING: Wrong Command.");
                                break;
                        }
                    }
                    break;
                case 5:
                    int count=0;
                    for (Appointment app : getHospital().getAppointments()) {
                        if (app.getPatient().getPersonalData().getId()==this.getPersonalData().getId()) {
                            System.out.println(app);
                            count++;
                        }
                    }
                    if (count==0) {
                        System.out.println("You currently do not have an appointment.");
                    }
                    break;
                case 6:
                    int i = 0;
                    exit2=true;
                    while (exit2) {
                        System.out.println("\nPlease select a pharmacy: ");
                        for (i = 0; i < getHospital().getPharmacists().size(); i++) {
                            System.out.println(i+". "+getHospital().getPharmacists().get(i).getPharmacyName());
                        }
                        System.out.println(i+". Exit");
                        System.out.print("Selection: ");
                        selection2=sc1.nextInt();
                        if (selection2==i) {
                            break;
                        }
                        if (prescriptions.size()!=0) {
                            for (i = 0; i < getPrescriptions().size(); i++) {
                                Doctor doc = getPrescriptions().get(i).getDoctor();
                                if (getHospital().getRelatedUsers().isEdge(getHospital().getPharmacists().get(selection2),doc)){
                                    getPrescriptions().get(i).printDiscountedPrescribes();
                                    System.out.println("\nTotal = "+getPrescriptions().get(i).medicineCost(getHospital().getPharmacists().get(selection2),doc));
                                    System.out.println("Since there is a contract between this pharmacy and the prescribing doctor, you will be able to benefit from a 50% discount.");
                                }else{
                                    System.out.println(getPrescriptions().get(i));
                                    System.out.println("\nTotal = "+getPrescriptions().get(i).medicineCost(getHospital().getPharmacists().get(selection2),doc));
                                }
                            }
                            int sel;
                            while (true) {
                                System.out.println("\nDo you want to take medicines ? You will pay "+ totalPrescriptionCost(getHospital().getPharmacists().get(selection2))+"TL");
                                System.out.println("1. YES");
                                System.out.println("2. NO");
                                System.out.print("Selection: ");
                                sel=sc2.nextInt();
                                if (sel==1){
                                    while (getPrescriptions().size()!=0) {
                                        pastPrescriptions.add(getPrescriptions().pop());
                                    }
                                    exit2=false;
                                    break;
                                }
                                else if(sel==2)
                                    break;
                                else
                                    System.out.println("WARNING: Wrong Command.");
                            }
                        }
                        else{
                            System.out.println("\nYou do not have any prescription right now!");
                            exit2=false;
                        }
                    }
                    break;
                case 7:
                    exit=false;
                    break;
                default:
                    System.err.println("WARNING: Wrong Command.");
                    break;
            }
        }
    }
}
