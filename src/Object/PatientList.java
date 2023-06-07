package Object;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class PatientList extends HashMap<String, Patient> implements Serializable {

    NurseList nurseList;

    public PatientList() {

    }

    public PatientList(NurseList nurseList) {
        this.nurseList = nurseList;
    }

//    public PatientList filterAvailableNuse(PatientList patientList) {
//        PatientList availableNurseList = new PatientList();
//        for () {
//
//        }
//        return availableNurseList;
//    }
    public void addPatient(NurseList listAvailableNurse) {
        Scanner box = new Scanner(System.in);
        Patient patientAdd = new Patient();
        System.out.println("Add a new patient: ");

        //input patient id
        String newPatientID, newName, newGender, newAddress, newPhone, newDiagnosis, newAdmissionDate, newDischargeDate, newNurseAssigned;
        int newAge;
        String formatPatientID = "^P\\d{4}";
        do {

            System.out.print("Input a new patient'sID (Patient's ID must be in this form 'P0000'):  ");
            newPatientID = box.nextLine();
            if (!newPatientID.trim().matches(formatPatientID)) {
                System.out.println("Invalid Patient's ID");
                System.out.println("Try again!");

            } else if (this.checkPatientExist(newPatientID)) {
                System.out.println("Error: Patient's ID is already existed!");
                System.out.println("Try Again!!!");
            } else {
                System.out.println("Valid Patient ID");
            }
        } while (!newPatientID.trim().matches(formatPatientID) || this.checkPatientExist(formatPatientID));
        patientAdd.setId(newPatientID.trim());

        //input name
        do {
            System.out.print("Input name: ");
            newName = box.nextLine();
            if (newName.trim().isEmpty()) {
                System.out.println("Invalid input. Name cannot be a blank");
                System.out.println("Try again!!!");
            } else {
                break;
            }

        } while (true);
        patientAdd.setName(newName);

        //input age
        do {
            System.out.print("Input age: ");
            newAge = Integer.parseInt(box.nextLine());
            if (newAge <= 0) {
                System.out.println("Invalid input. Age must be a positive integer. ");

            } else {
                break;
            }
        } while (true);
        patientAdd.setAge(newAge);

        //input gender
        do {
            System.out.print("Input nurse's gender(male/female): ");
            newGender = box.nextLine();
            if (!checkValidGender(newGender)) {
                System.out.println("Invalid gender");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        patientAdd.setGender(newGender);

        //input address
        do {
            System.out.print("Input address: ");
            newAddress = box.nextLine();
            if (newAddress.trim().isEmpty()) {
                System.out.println("Invalid address. Address cannot be a blank");
                System.out.println("Try again!!!");

            } else {
                break;
            }
        } while (true);
        patientAdd.setAddress(newAddress);

        //input phoneNumber
        String phoneFormat = "^0\\d{9}$";

        do {
            System.out.print("Input phone number: ");
            newPhone = box.nextLine();

            if (!newPhone.matches(phoneFormat)) {
                System.out.println("Invalid phone number.  Phone number must has 10 digits,  starts with 0 and following by digits");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        patientAdd.setPhone(newPhone);

        // input diagnosis
        System.out.print("Input diagnosis: ");
        newDiagnosis = box.nextLine();
        patientAdd.setDiagnosis(newDiagnosis);

        //input admissionDate
        System.out.print("Input admission date: ");
        Date admissionDate = inputDate();
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        newAdmissionDate = formatter.format(admissionDate);

        patientAdd.setAdmissionDate(newAdmissionDate);

        // input dischargeDate
        Date dischargeDate;
        do {
            System.out.print("Input discharge date: ");
            dischargeDate = inputDate();
            if (dischargeDate.compareTo(admissionDate) < 0) {
                System.out.println("Invalid value. Discharge date must be after admission date ");
                System.out.println("Try again");
            }
        } while (dischargeDate.compareTo(admissionDate) < 0);

        newDischargeDate = formatter.format(dischargeDate);
        patientAdd.setDischargeDate(newDischargeDate);

        // input Nurse 
        System.out.println("\n\nAssign which nurse to take care this patient: ");
        System.out.println("Display available nurse: ");

        listAvailableNurse.printAllIDNurse();
        System.out.println("Choose 2 nurse to assign taking care this patient: ");
        String idFirstNurse, idSecondNurse;

        String formatNurseID = "^N\\d{4}$";
        do {

            System.out.print("Input first Nurse ID to take care (ID must be in this form 'N0000'):  ");
            idFirstNurse = box.nextLine();
            if (!idFirstNurse.trim().matches(formatNurseID)) {
                System.out.println("Invalid Nurse's ID");
                System.out.println("Try again!");

            } else if (listAvailableNurse.checkNurseExist(idFirstNurse)) {
                patientAdd.assignedNurseList.put(idFirstNurse, listAvailableNurse.get(idFirstNurse));
                listAvailableNurse.get(idFirstNurse).assignedPatientList.put(newPatientID, this.get(newPatientID));
                listAvailableNurse.remove(idFirstNurse);
            }
        } while (!idFirstNurse.trim().matches(formatNurseID));

        do {

            System.out.print("Input second Nurse ID to take care (ID must be in this form 'N0000'):  ");
            idSecondNurse = box.nextLine();
            if (!idSecondNurse.trim().matches(formatNurseID)) {
                System.out.println("Invalid Nurse's ID");
                System.out.println("Try again!");

            } else if (listAvailableNurse.checkNurseExist(idSecondNurse)) {

                if (idFirstNurse.equalsIgnoreCase(idSecondNurse)) {
                    System.out.println("First Nurse ID cannot be Second Nurse ID");
                    System.out.println("Try again");
                } else {

                    patientAdd.assignedNurseList.put(idSecondNurse, listAvailableNurse.get(idSecondNurse));
                    listAvailableNurse.get(idSecondNurse).assignedPatientList.put(newPatientID, this.get(newPatientID));
                    listAvailableNurse.remove(idSecondNurse);
                }
            }
        } while (!idSecondNurse.trim().matches(formatNurseID) || (idFirstNurse.equalsIgnoreCase(idSecondNurse)));

        this.put(newPatientID, patientAdd);
        System.out.println("Adding successfully!!");
        /// continuing
    }

    public boolean checkPatientExist(String patientIdCheck) {
        return this.containsKey(patientIdCheck);
    }

    public boolean checkValidGender(String inputGender) {
        String[] validGenders = {"male", "female"};
        for (String option : validGenders) {
            if (option.equalsIgnoreCase(inputGender)) {
                return true;
            }
        }

        return false;
    }

    public Date inputDate() {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");
        dateFormat.setLenient(false);

        Date date = null;
        boolean isValidInput = false;

        do {
            System.out.print("\nEnter a date (dd/mm/yy): ");
            String userInput = scanner.nextLine();

            try {
                date = dateFormat.parse(userInput);
                isValidInput = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please enter a date in the format dd/mm/yy");
            }
        } while (!isValidInput);
        return date;
    }

    public void displayPatientInputDate() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/mm/yy");

        System.out.println("\t\t\tList of patient");
        System.out.print("Start date: ");
        Date startDate = inputDate();
        System.out.println("End date: ");
        Date endDate = inputDate();
        for (Map.Entry<String, Patient> entry : this.entrySet()) {
            Date entryDate = dateFormat.parse(entry.getValue().getAdmissionDate());
            if (compareDate(startDate, endDate, entryDate)) {
                entry.getValue().toString();
            }
        }
    }

    public boolean compareDate(Date startDate, Date endDate, Date entryDate) {
        if (entryDate.compareTo(startDate) >= 0 && entryDate.compareTo(endDate) <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public void sortPatientOption() {
        Scanner box = new Scanner(System.in);

        int optionField = 0;
        int optionOrder = 0;

        do {
            System.out.println("Which field: ");
            System.out.println("1 - Sorting by ID");
            System.out.println("2 - Sorting by Name");
            try {
                System.out.print("Choose your option:  ");
                optionField = Integer.parseInt(box.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("Try again!!");
            }

        } while (optionField != 1 && optionField != 2);

        do {
            System.out.println("Which order: ");
            System.out.println("1 - Sorting asending in order");
            System.out.println("2 - Sorting descending in order");
            try {
                System.out.print("Choose your option:  ");
                optionOrder = Integer.parseInt(box.nextLine());

            } catch (Exception e) {
                System.out.println("Invalid input");
                System.out.println("Try again!!");
            }

        } while (optionOrder != 1 && optionOrder != 2);

        if (optionField == 1 && optionOrder == 1) {
            sortByIDASC();
            System.out.println("After sorting: ");

        }
        if (optionField == 1 && optionOrder == 2) {
            sortByIdDESC();
            System.out.println("After sorting: ");

        }
        if (optionField == 2 && optionOrder == 1) {
            sortByNameASC();
            System.out.println("After sorting: ");

        }
        if (optionField == 2 && optionOrder == 2) {
            sortByNameDESC();
            System.out.println("After sorting: ");
            displayAllPatients();
        }
    }

    //    }
    //
    //    public List<String> loadPatientFromFile(String pathName) {
    //
    //        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(pathName))) {
    //            return (List<String>) inputStream.readObject();
    //        } catch (IOException | ClassNotFoundException e) {
    //            System.out.println("Error occurred while loading data from " + pathName + ": " + e.getMessage());
    //            return null;
    //        }
    //    }
    public List<String> loadPatientFromFile(String pathName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(pathName))) {
            List<String> data = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line);
            }
            return data;
        } catch (IOException e) {
            System.out.println("Error occurred while loading data from " + pathName + ": " + e.getMessage());
            return null;
        }
    }

    public boolean loadFromFile(String pathName, NurseList nurseList) {

        List<String> data = loadPatientFromFile(pathName);
        if (data != null) {
            for (String line : data) {

                if (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String patientId = tokenizer.nextToken().trim();
                    String patientName = tokenizer.nextToken().trim();
                    int patientAge = Integer.parseInt(tokenizer.nextToken().trim());
                    String patientGender = tokenizer.nextToken().trim();
                    String patientAddress = tokenizer.nextToken().trim();
                    String patientPhone = tokenizer.nextToken().trim();
                    String patientDiagnosis = tokenizer.nextToken().trim();
                    String patientAdmission = tokenizer.nextToken().trim();
                    String patientDischarge = tokenizer.nextToken().trim();
                    String idFirstNurseTakeCare = tokenizer.nextToken().trim();
                    String idSecondNurseTakeCare = tokenizer.nextToken().trim();

                    HashMap<String, Nurse> nurseTakeCare = new HashMap<String, Nurse>();

                    nurseTakeCare.put(idFirstNurseTakeCare, nurseList.get(idFirstNurseTakeCare));
                    nurseTakeCare.put(idSecondNurseTakeCare, nurseList.get(idSecondNurseTakeCare));

                    Patient patient = new Patient(patientId, patientName, patientAge, patientGender, patientAddress, patientPhone, patientDiagnosis, patientAdmission, patientDischarge, nurseTakeCare);
                    nurseList.get(idFirstNurseTakeCare).assignedPatientList.put(idFirstNurseTakeCare, patient);
                    nurseList.get(idSecondNurseTakeCare).assignedPatientList.put(idSecondNurseTakeCare, patient);

                    this.put(patientId, patient);
                }
            }
            return true;
        }
        return false;

    }

    public void savePatientToBinaryFile(String fileName) {

        List<String> data = new ArrayList<>();
        for (Map.Entry<String, Patient> entry : this.entrySet()) {
            data.add(entry.toString());
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
            System.out.println("Data saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data to " + fileName + ": " + e.getMessage());
        }
    }

    public void sortByNameDESC() {
        List<Patient> listPatient = new ArrayList<>(this.values());
        listPatient.sort(Comparator.comparing(Patient::getName).reversed());

        this.clear();
        System.out.println("List of all patients:");
        System.out.println("ID\tAdmissionDate\tName\tPhone\tDiasgnosis");
        for (Patient patient : listPatient) {
            this.put(patient.getId(), patient);
            patient.display();

        }
    }

    public void sortByIDASC() {
        List<Patient> listPatient = new ArrayList<>(this.values());
        listPatient.sort(Comparator.comparing(Patient::getId));

        this.clear();
        System.out.println("List of all patients:");
        System.out.println("ID\tAdmissionDate\tName\tPhone\tDiasgnosis");
        for (Patient patient : listPatient) {
            this.put(patient.getId(), patient);
            patient.display();
        }
    }

    public void sortByIdDESC() {
        List<Patient> listPatient = new ArrayList<>(this.values());
        listPatient.sort(Comparator.comparing(Patient::getId).reversed());

        this.clear();
        System.out.println("List of all patients:");
        System.out.println("ID\tAdmissionDate\tName\tPhone\tDiasgnosis");
        for (Patient patient : listPatient) {
            this.put(patient.getId(), patient);
            patient.display();

        }
    }

    public void sortByNameASC() {
        List<Patient> listPatient = new ArrayList<>(this.values());
        listPatient.sort(Comparator.comparing(Patient::getName));

        this.clear();
        System.out.println("List of all patients:");
        System.out.println("ID\tAdmissionDate\tName\tPhone\tDiasgnosis");
        for (Patient patient : listPatient) {
            this.put(patient.getId(), patient);
            patient.display();

        }
    }

    public void displayAllPatients() {
        System.out.println("List of all patients:");
        System.out.println("ID\tAdmissionDate\tName\tPhone\tDiasgnosis");
        for (Map.Entry<String, Patient> entry : this.entrySet()) {

            entry.getValue().display();
        }
    }
}
