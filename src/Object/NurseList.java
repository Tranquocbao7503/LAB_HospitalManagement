package Object;

import com.sun.imageio.plugins.jpeg.JPEG;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class NurseList extends HashMap<String, Nurse> implements Serializable {

    public NurseList() {
    }

    // add Patient
    //create a nurse
    public void addNurse(Nurse nurseAdd) {
        this.put(nurseAdd.getId(), nurseAdd);
    }

    public void addNurse() {
        Nurse nurseAdd = new Nurse();
        Scanner box = new Scanner(System.in);
        System.out.println("Add a new nurse: ");

        // input staffID
        String nurseAddID, newName, newGender, newAddress, newPhone, newDepartment, newShift;
        int newAge;
        double newSalary;
        String formatNurseID = "^N\\d{4}$";
        do {

            System.out.print("Input a new staff's ID (StaffID must be in this form 'N0000'):  ");
            nurseAddID = box.nextLine();
            if (!nurseAddID.trim().matches(formatNurseID)) {
                System.out.println("Invalid Staff's ID");
                System.out.println("Try again!");

            } else if (this.checkNurseExist(nurseAddID)) {
                System.out.println("Error: Staff's ID is already existed!");
                System.out.println("Try Again!!!");
            } else {
                System.out.println("Valid Staff ID");
            }
        } while (!nurseAddID.trim().matches(formatNurseID) || this.checkNurseExist(nurseAddID));
        // set staffID
        nurseAdd.setStaffID(nurseAddID.trim());
        nurseAdd.setId(nurseAddID.trim());

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

        nurseAdd.setName(newName.trim());

        // input gender
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
        nurseAdd.setGender(newGender);

        // input address
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
        nurseAdd.setAddress(newAddress);

        // input department
        do {
            System.out.print("Input department: ");
            newDepartment = box.nextLine();
            if (newDepartment.trim().isEmpty()) {
                System.out.println("Department cannot be a blank");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        nurseAdd.setDepartment(newDepartment.trim());

        // input shift
        do {
            System.out.print("Input nurse's shift (morning/afternoon/evening/night): ");
            newShift = box.nextLine();
            if (newShift.trim().isEmpty()) {
                System.out.println("Invalid shift. Shift cannot be a blank");
                System.out.println("Try again!!!");

            } else if (!checkValidShift(newShift)) {
                System.out.println("Your shift input is invalid");
                System.out.println("Try again!!!");

            } else {
                break;
            }
        } while (true);

        nurseAdd.setShift(newShift);

        //input phone number
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
        nurseAdd.setPhone(newPhone);

        // input age
        do {
            System.out.print("Input age: ");
            newAge = Integer.parseInt(box.nextLine());
            if (newAge <= 0) {
                System.out.println("Invalid input. Age must be a positive integer. ");

            } else {
                break;
            }
        } while (true);
        nurseAdd.setAge(newAge);

        // input salary
        do {
            System.out.print("Input salary: ");
            newSalary = Double.parseDouble(box.nextLine());
            if (newSalary <= 0) {
                System.out.println("Invalid input. Salary must be a positive number");
                System.out.println("Try again!!!");
            } else {
                break;
            }
        } while (true);
        nurseAdd.setSalary(newSalary);

        this.put(nurseAddID, nurseAdd);

    }

    public NurseList filterAvailableNurses(NurseList nurseList, NurseList nurseAvailableList) {
       
        for (Nurse nurse : nurseList.values()) {
            if (nurse.getAssignedPatientList() == null || nurse.getAssignedPatientList().size() <= 1) {
               
                nurseAvailableList.put(nurse.getId(), nurse);
            }
        }

        return nurseAvailableList;
    }

    public boolean checkNurseExist(String nurseIdCheck) {
        return this.containsKey(nurseIdCheck);
    }

    public void printAllNurse() {

        if (this == null || this.isEmpty()) {
            System.out.println("The list of nurse is empty");
        } else {

            for (Map.Entry<String, Nurse> entry : this.entrySet()) {
                System.out.println("\n\n");
                System.out.println("Nurse ID: " + entry.getKey());
                System.out.println("Nurse details: " + entry.getValue());
            }
        }
    }

    public void printAllIDNurse() {

        if (this == null || this.isEmpty()) {
            System.out.println("The list of nurse is empty");
        } else {

            for (Map.Entry<String, Nurse> entry : this.entrySet()) {
                System.out.println("Nurse ID: " + entry.getKey());

            }
        }
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

    public boolean checkValidShift(String inputShift) {
        String[] validShifts = {"morning", "afternoon", "evening", "night"};
        for (String option : validShifts) {
            if (option.equalsIgnoreCase(inputShift)) {
                return true;
            }

        }
        return false;
    }

    // find a nurse
    public void findNurse() {
        Scanner box = new Scanner(System.in);
        System.out.print("Input pattern name searching: ");
        String patternSearch = box.nextLine();
        int count = 0;
        if (patternSearch.trim().isEmpty()) {
            System.out.println("Pattern name cannot be a blank");
        } else {

            for (Map.Entry<String, Nurse> entry : this.entrySet()) {
                if (entry.getValue().getName().toLowerCase().contains(patternSearch.trim().toLowerCase())) {
                    System.out.println("Nurse info: " + entry.getValue());
                    count++;
                }
            }
            if (count == 0) {
                System.out.println("The nurse doesn't exist");
            }

        }

    }

    // update Nurse
    public void updateNurse() {
        Scanner box = new Scanner(System.in);
        String[] attributes = {"name", "age", "address", "phoneNumber", "department", "shift", "salary", "gender"};

        String nurseUpdateID, newName, newGender, newAddress, newPhone, newDepartment, newShift;
        int newAge;
        double newSalary;
        System.out.println("\t\t\t\tUpdate Program");
        System.out.print("Input nurse's ID to update:  ");

        String idNurseUpdate;

        String formatNurseID = "^N\\d{4}$";
        do {

            System.out.print("Input nurse's ID to update (nurse's ID must be in this form 'N0000'):  ");
            idNurseUpdate = box.nextLine();
            if (!idNurseUpdate.trim().matches(formatNurseID)) {
                System.out.println("Invalid nurse's ID");
                System.out.println("Try again!");

            } else {
                System.out.println("Valid Staff ID");
            }
        } while (!idNurseUpdate.trim().matches(formatNurseID));

        if (!checkNurseExist(idNurseUpdate)) {
            System.out.println("The nurse with ID " + this.get(idNurseUpdate).getId() + " does not exist");
        } else {

            //update name
            if (chooseYesOrNo(attributes[0])) {
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

                this.get(idNurseUpdate).setName(newName.trim());
            }
            //update age
            if (chooseYesOrNo(attributes[1])) {
                do {
                    System.out.print("Input age: ");
                    newAge = Integer.parseInt(box.nextLine());
                    if (newAge <= 0) {
                        System.out.println("Invalid input. Age must be a positive integer. ");

                    } else {
                        break;
                    }
                } while (true);
                this.get(idNurseUpdate).setAge(newAge);
            }
            // update gender
            if (chooseYesOrNo(attributes[7])) {
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
                this.get(idNurseUpdate).setGender(newGender);
            }
            //update address
            if (chooseYesOrNo((attributes[2]))) {
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
                this.get(idNurseUpdate).setAddress(newAddress);
            }

            //update phone
            if (chooseYesOrNo((attributes[3]))) {
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
                this.get(idNurseUpdate).setPhone(newPhone);
            }
            //update department

            if (chooseYesOrNo((attributes[4]))) {
                do {
                    System.out.print("Input department: ");
                    newDepartment = box.nextLine();
                    if (newDepartment.trim().isEmpty()) {
                        System.out.println("Department cannot be a blank");
                        System.out.println("Try again!!!");
                    } else {
                        break;
                    }
                } while (true);
                this.get(idNurseUpdate).setDepartment(newDepartment.trim());
            }
            //update shift

            if (chooseYesOrNo((attributes[5]))) {
                do {
                    System.out.print("Input nurse's shift (morning/afternoon/evening/night): ");
                    newShift = box.nextLine();
                    if (newShift.trim().isEmpty()) {
                        System.out.println("Invalid shift. Shift cannot be a blank");
                        System.out.println("Try again!!!");

                    } else if (!checkValidShift(newShift)) {
                        System.out.println("Your shift input is invalid");
                        System.out.println("Try again!!!");

                    } else {
                        break;
                    }
                } while (true);

                this.get(idNurseUpdate).setShift(newShift);
            }
            //update salary
            if (chooseYesOrNo((attributes[6]))) {
                do {
                    System.out.print("Input salary: ");
                    newSalary = Double.parseDouble(box.nextLine());
                    if (newSalary <= 0) {
                        System.out.println("Invalid input. Salary must be a positive number");
                        System.out.println("Try again!!!");
                    } else {
                        break;
                    }
                } while (true);
                this.get(idNurseUpdate).setSalary(newSalary);
            }
        }

        System.out.println("\t\t\tUpdate nurse with ID:" + this.get(idNurseUpdate).getId() + " successfully!");

    }

    public boolean chooseYesOrNo(String attribute) {
        Scanner scanner = new Scanner(System.in);
        String userInput;
        do {
            System.out.print("Do you want to edit this nurse's " + attribute + " (y/n): ");
            userInput = scanner.nextLine().toLowerCase();
        } while (!userInput.equals("y") && !userInput.equals("n"));

        return userInput.equals("y");
    }

    // delete Nurse
    public void deleteNurse() {
        Scanner box = new Scanner(System.in);
        String formatNurseID = "^N\\d{4}$";
        System.out.println("\t\t\tDelete Program");

        String idNurseDelete;

        do {
            System.out.print("Input nurse's ID to delete (nurse's ID must be in this form 'N0000'):  ");
            idNurseDelete = box.nextLine();
            if (!idNurseDelete.trim().matches(formatNurseID)) {
                System.out.println("Invalid nurse's ID");
                System.out.println("Try again!");

            } else {
                System.out.println("Valid Staff ID");
            }
        } while (!idNurseDelete.trim().matches(formatNurseID));

        if (!checkNurseExist(idNurseDelete)) {
            System.out.println("The nurse with ID " + idNurseDelete + " does not exist");
        } else {
            if (checkAssignedPatientListToDelete(idNurseDelete)) {
                if (confirmDelete()) {
                    this.remove(idNurseDelete);
                }
                System.out.println("Deleting nurse ID: " + idNurseDelete + " successfully!");

            } else {
                System.out.println("Deleting nurse ID: " + idNurseDelete + " failed . Because this nurse are taking care some patient!");
            }

        }
    }

    public boolean confirmDelete() {
        Scanner box = new Scanner(System.in);
        String confirmOption;
        do {
            System.out.print("Are you sure to delete this nurse(y/n): ");
            confirmOption = box.nextLine().toLowerCase();
        } while (!confirmOption.equals("y") && !confirmOption.equals("n"));
        return confirmOption.equals("y");
    }

    public boolean checkAssignedPatientListToDelete(String idNurseDelete) throws NullPointerException {

        if (this.get(idNurseDelete).getAssignedPatientList().size() == 0 || this.get(idNurseDelete).getAssignedPatientList() == null) {
            return true;
        } else {
            return false;
        }

    }

    public void saveNurseToBinaryFile(String fileName) {

        List<String> data = new ArrayList<>();
        for (Map.Entry<String, Nurse> entry : this.entrySet()) {
            data.add(entry.toString());
        }
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName))) {
            outputStream.writeObject(data);
            System.out.println("Data saved to " + fileName + " successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while saving data to " + fileName + ": " + e.getMessage());
        }
    }

    public boolean loadFromFile(String filename) {

        List<String> data = loadNurseFromFile(filename);
        if (data != null) {
            for (String line : data) {

                if (line != null) {
                    StringTokenizer tokenizer = new StringTokenizer(line, ",");
                    String nurseId = tokenizer.nextToken().trim();
                    String nurseName = tokenizer.nextToken().trim();
                    int nurseAge = Integer.parseInt(tokenizer.nextToken().trim());
                    String nurseGender = tokenizer.nextToken().trim();
                    String nurseAddress = tokenizer.nextToken().trim();
                    String nursePhone = tokenizer.nextToken().trim();
                    String nurseStaff = tokenizer.nextToken().trim();
                    String nurseDep = tokenizer.nextToken().trim();
                    String nurseShift = tokenizer.nextToken().trim();
                    double nurseSalary = Double.parseDouble(tokenizer.nextToken().trim());
                    HashMap<String, Patient> patienTakeCareList = new HashMap<String, Patient>();
                    Nurse nurse = new Nurse(nurseStaff, nurseName, nurseAge, nurseGender, nurseAddress, nursePhone, nurseStaff, nurseDep, nurseShift, nurseSalary, patienTakeCareList);

                    this.put(nurseStaff, nurse);
                }
            }
            return true;
        }
        return false;
    }

    public List<String> loadNurseFromFile(String pathName) {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(pathName))) {
            return (List<String>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading data from " + pathName + ": " + e.getMessage());
            return null;
        }
    }

}
