package HospitalManagement;

import Object.NurseList;
import Object.Nurse;
import Object.PatientList;
import java.io.File;
import java.util.ArrayList;

import java.util.HashMap;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner box = new Scanner(System.in);

        NurseList nurseList = new NurseList();
        NurseList nurseAvailableList = new NurseList();
        nurseAvailableList = nurseList.filterAvailableNurses(nurseList, nurseAvailableList);
        PatientList patientList = new PatientList();

        String absolutePathPatientData = new File("").getAbsolutePath() + "\\src\\DataWarhouse\\data.txt";
        String absolutePathPatient = new File("").getAbsolutePath() + "\\src\\DataWarhouse\\patients.dat";
        String absolutePathNurse = new File("").getAbsolutePath() + "\\src\\DataWarhouse\\nurses.dat";

        ArrayList<String> optionStatement = new ArrayList<String>();
        optionStatement.add("Create a nurse");
        optionStatement.add("Find the nurse");
        optionStatement.add("Update the nurse");
        optionStatement.add("Delete the nurse");
        optionStatement.add("Add a patient");
        optionStatement.add("Display patient");
        optionStatement.add("Sort the patient list");
        optionStatement.add("Save data");
        optionStatement.add("Load data");
        optionStatement.add("Exit program");
        int option;
        System.out.println("List of current Nurses");
        nurseList.printAllNurse();

        String confirmation = "";
        do {
            System.out.println("************************* Hospital manager program **************************");
            option = Menu.getChoice(optionStatement);

            switch (option) {
                case 1:

                    nurseList.addNurse();
                    nurseList.printAllNurse();
                    break;
                case 2:
                    nurseList.findNurse();
                    break;
                case 3:
                    nurseList.updateNurse();
                    System.out.println("\t\t\tAfter updating:");
                    nurseList.printAllNurse();
                    break;
                case 4:
                    nurseList.deleteNurse();
                    break;
                case 5:

                    nurseAvailableList = nurseList.filterAvailableNurses(nurseList, nurseAvailableList);
                    patientList.addPatient(nurseAvailableList);
                    break;
                case 6:
                    patientList.displayAllPatients();

                    break;
                case 7:

                    patientList.sortPatientOption();

                    break;
                case 8:
                    patientList.savePatientToBinaryFile(absolutePathPatient);
                    nurseList.saveNurseToBinaryFile(absolutePathNurse);
                    break;
                case 9:

                    if (nurseList.loadFromFile(absolutePathNurse)) {
                        System.out.println("Load Nurses data successfully");
                    } else {
                        System.out.println("Failed to load file");
                    }

                    if (patientList.loadFromFile(absolutePathPatientData, nurseList)) {
                        System.out.println("Load Patient data successfully");
                    } else {
                        System.out.println("Failed to load file");
                    }

                    break;
                case 10:

                    do {
                        System.out.print("Are you sure to quite this program(y/n): ");
                        confirmation = box.nextLine().toLowerCase();
                    } while (!confirmation.equals("n") && !confirmation.equals("y"));

                    patientList.savePatientToBinaryFile(absolutePathPatient);
                    nurseList.saveNurseToBinaryFile(absolutePathNurse);
                    break;

                default:
                    System.out.println("Please enter your options in range 1-10");
            }
        } while (option != 10 || (confirmation.equalsIgnoreCase("n") && option == 10) || option < 0 || option > 10);

    }

}
