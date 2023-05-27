package Object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Patient extends Person implements Serializable {

    //attributes
    private String diagnosis;
    private String admissionDate;
    private String dischargeDate;
    public HashMap<String, Nurse> assignedNurseList;

    //get,set
    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(String admissionDate) {
        this.admissionDate = admissionDate;
    }

    public String getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(String dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public HashMap<String, Nurse> getAssignedNurseList() {
        return assignedNurseList;
    }

    public void setAssignedNurseList(HashMap<String, Nurse> assignedNurseList) {
        this.assignedNurseList = assignedNurseList;
    }

    //constructors
    //method
    public Patient() {
        super();
        this.diagnosis = "None";
        this.admissionDate = "None";
        this.dischargeDate = "NOne";
        this.assignedNurseList = new HashMap<String, Nurse>();
    }

    public Patient(String id, String name, int age, String gender, String address, String phone, String diagnosis, String admissionDate, String dischargeDate, HashMap<String, Nurse> nurseAssignedList) {
        super(id, name, age, gender, address, phone);
        this.diagnosis = diagnosis;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.assignedNurseList = new HashMap<String, Nurse>();
    }

    @Override
    public String toString() {
        return super.toString() + ", " + this.diagnosis + ", " + this.admissionDate + ", " + this.dischargeDate;
    }

    public void display() {
        System.out.println(this.id + "\t" + this.admissionDate + "\t" + this.name + "\t" + this.phone + "\t" + this.diagnosis);
    }
}
