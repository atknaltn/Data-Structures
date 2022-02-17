import java.util.GregorianCalendar;
/**
 * To hold appointment data.
 */
public class Appointment implements Comparable<Appointment>{
    private GregorianCalendar appointment_day;
    private Boolean awake = true;
    private Doctor relatedDoctor;
    private Patient attachedPatient;

    public Appointment(){
        appointment_day = null;
        relatedDoctor = null;
        attachedPatient = null;
        awake = true;
    }
    public Appointment(Patient input_PATIENT,  GregorianCalendar input_GregorianCalendar){
        // Default Constructor without doctor data
        attachedPatient = input_PATIENT;
        appointment_day = input_GregorianCalendar;
        awake = true;
    }
    public Appointment(Doctor input_DOCTOR, Patient input_PATIENT,  GregorianCalendar input_GregorianCalendar){
        // Default Constructor with GregorianCalendar datatype
        relatedDoctor = input_DOCTOR;
        attachedPatient = input_PATIENT;
        appointment_day = input_GregorianCalendar;
        awake = true;
    }

    public Appointment(Doctor input_DOCTOR, Patient input_PATIENT, int year, int month, int day, int hour, int minute){
        //  Default constructor with integer inputs
        relatedDoctor = input_DOCTOR;
        attachedPatient = input_PATIENT;
        appointment_day = new GregorianCalendar(year,month,day,hour,minute);
        awake = true;
    }

    public Boolean isAwake(){
        // Method that will return if appointment is checked out by Patient or by Doctor or not
        return awake;
    }


    public GregorianCalendar getGregorianCalendar(){
        // Returning appointment time
        return appointment_day;
    }

    public Doctor getDoctor(){
        // Returning assigned doctor class
        return relatedDoctor;
    }

    public Patient getPatient(){
        // Returning assigned patient class
        return attachedPatient;
    }

    public void setGregorianCalendar(GregorianCalendar input_GregorianCalendar) {
        // Setting a new GregorianCalendar
        this.appointment_day = input_GregorianCalendar;
    }

    public void setDoctor(Doctor doctor) {
        // Setting a new GregorianCalendar
        this.relatedDoctor = doctor;
    }

    public void setPatient(Patient patient) {
        // Setting a new GregorianCalendar
        this.attachedPatient = patient;
    }

    public void setAwake(Boolean input_B) {
        // Setting a status to appointment
        this.awake = input_B;
    }

    public String printForDoctor(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Patient: " + this.getPatient().print() + "\n Time: " + this.getGregorianCalendar().getTime());
        return stringBuilder.toString();
    }

    public void print(){
        GregorianCalendar now = new GregorianCalendar();
        System.out.print("\n"+toString());
        if (awake)
            System.out.print(" Status: Active");
        else if (appointment_day.before(now))
            System.out.print(" Status: Passed");
        else
            System.out.print(" Status: Non-Active");
    }

    @Override
    public String toString(){
        return "Doctor: " + this.getDoctor() + " Patient: " + this.getPatient() + " Time: " + this.getGregorianCalendar().getTime();
    }

    @Override
    public int compareTo(Appointment o) {
        if(o.getDoctor().equals(this.getDoctor())
                &&this.getGregorianCalendar().getTime().getHours() == o.getGregorianCalendar().getTime().getHours()
                &&this.getGregorianCalendar().getTime().getMinutes() == o.getGregorianCalendar().getTime().getMinutes()
                &&this.getGregorianCalendar().getTime().getMonth() == o.getGregorianCalendar().getTime().getMonth()
                &&this.getGregorianCalendar().getTime().getYear() == o.getGregorianCalendar().getTime().getYear()
                &&this.getGregorianCalendar().getTime().getDay() == o.getGregorianCalendar().getTime().getDay()
                ){
            return 0;
        }
        return -1;
    }
}