package Object;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class Nurse extends Person implements Serializable {

    //attributes
    public String staffID;
    private String department;
    private String shift;
    private double salary;
    public HashMap<String, Patient> assignedPatientList;

    //get,set
    public HashMap<String, Patient> getAssignedPatientList() {
        return assignedPatientList;
    }

    public void setAssignedPatientList(HashMap<String, Patient> assignedPatientList) {
        this.assignedPatientList = assignedPatientList;
    }

    public String getStaffID() {
        return staffID;
    }

    public void setStaffID(String staffID) {
        this.staffID = staffID;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    //constructors
    public Nurse() {
        super();
        this.staffID = "None";
        this.department = "None";
        this.shift = "None";
        this.salary = 0;
        this.assignedPatientList = new HashMap<String, Patient>();
    }

    public Nurse(String id, String name, int age, String gender, String address, String phone, String staffID, String department, String shift, double salary, HashMap<String, Patient> assignedPatientList) {
        super(id, name, age, gender, address, phone);
        this.staffID = staffID;
        this.department = department;
        this.shift = shift;
        this.salary = salary;
        this.assignedPatientList = new HashMap<String, Patient>();
    }

    //methods
    @Override
    public String toString() {
        return super.toString() + "," + staffID + ", " + department + ", " + shift + ", " + salary;
    }

}
