import java.util.TreeMap;
/**
 * A class to keep medicine
 * information given to the patient
 */
public class Prescription{
	//Data fields
	/**to hold owner of the prescription*/
	private Patient patient;
	/**to hold doctor of the prescription*/
	private Doctor doctor;
	/**to hold names of the meds*/
	private TreeMap<Integer,Medicine> meds;

	//Methods
	/**
	 * Constructor
	 * to create empty prescription for the patient
	 * @param patient owner of the prescription
	 */
	public Prescription(Doctor doctor, Patient patient){
		this.patient = patient;
		this.doctor=doctor;
		meds = new TreeMap<>();
	}

	/**
	 * tells who owns the prescription
	 * @return owner of the prescription
	 */
	public Patient getPatient(){
		return patient;
	}

	/**
	 * Get the meds list
	 * @return meds' names as an ArrayList<String>
	 */
	public TreeMap<Integer,Medicine> getMedicines(){
		return meds;
	}

	/**
	 * Adds medicine to the prescription
	 * If this medicine is already on the list,
	 * it will not be added again
	 * Only doctors can use this action
	 * @param medicine medicine name as a string
	 */
	protected boolean addMedicine(Medicine medicine){
		if(meds.containsValue(medicine)){
			System.out.println("WARNING: Cannot add. The medicine already exists.");
			return false;
		}
		meds.put(medicine.getId(), medicine);
		return true;
	}
	/**
	 * Sorts medicines according to their cost.
	 * @param arr Value array
	 */
	static public void sort(Medicine[] arr) {
		int n = arr.length;

		// Build heap (rearrange array)
		for (int i = n / 2 - 1; i >= 0; i--)
			heapify(arr, n, i);

		// One by one extract an element from heap
		for (int i = n - 1; i > 0; i--) {
			// Move current root to end
			Medicine temp = arr[0];
			arr[0] = arr[i];
			arr[i] = temp;

			// call max heapify on the reduced heap
			heapify(arr, i, 0);
		}
	}

	// To heapify a subtree rooted with node i which is
	// an index in arr[]. n is size of heap
	static void heapify(Medicine[] arr, int n, int i) {
		int largest = i; // Initialize largest as root
		int l = 2 * i + 1; // left = 2*i + 1
		int r = 2 * i + 2; // right = 2*i + 2

		// If left child is larger than root
		if (l < n && arr[l].getCost() > arr[largest].getCost())
			largest = l;

		// If right child is larger than largest so far
		if (r < n && arr[r].getCost() > arr[largest].getCost())
			largest = r;

		// If largest is not root
		if (largest != i) {
			Medicine swap = arr[i];
			arr[i] = arr[largest];
			arr[largest] = swap;
			// Recursively heapify the affected sub-tree
			heapify(arr, n, largest);
		}
	}

	@Override
	public String toString(){
		Medicine[] sortedMeds = new Medicine[this.getMedicines().size()];
		this.getMedicines().values().toArray(sortedMeds);
		sort(sortedMeds);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("\nThe Prescription: \n");
		int n = sortedMeds.length;
		for (int i = 0; i < n; ++i)
			stringBuilder.append(sortedMeds[i]+"\n");
		return stringBuilder.toString();
	}
	/**
	 * Prints discounted costs for medicines.
	 */
	public void printDiscountedPrescribes(){
		Medicine[] sortedMeds = new Medicine[this.getMedicines().size()];
		this.getMedicines().values().toArray(sortedMeds);
		sort(sortedMeds);
		StringBuilder stringBuilder = new StringBuilder();
		System.out.println("\nThe Prescription:");
		int n = sortedMeds.length;
		for (int i = 0; i < n; ++i)
			sortedMeds[i].printDiscountedPrice();
	}
	/**
	 * It calculates total cost for given prescription. It takes care for contracts.
	 * @param p pharmacist
	 * @param d doctor
	 * @return total cost
	 */
	public double medicineCost(Pharmacist p , Doctor d){
		Medicine[] medicines = new Medicine[this.getMedicines().size()];
		meds.values().toArray(medicines);
		double total = 0.0;
		for (int i = 0; i < medicines.length; i++) {
			total+=medicines[i].getCost();
		}
		if (getPatient().getHospital().getRelatedUsers().isEdge(p, d)){
			return total/2;
		}
		return total;
	}
	/**
	 * Gets related doctor.
	 * @return Doctor
	 */
	public Doctor getDoctor() {
		return this.doctor;
	}
	/**
	 * Changes Related Doctor.
	 * @param doctor Doctor
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
}