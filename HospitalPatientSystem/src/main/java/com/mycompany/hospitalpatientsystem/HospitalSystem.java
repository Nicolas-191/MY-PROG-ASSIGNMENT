
package com.mycompany.hospitalpatientsystem;

import java.util.*;

enum PatientType {
    INPATIENT, OUTPATIENT, EMERGENCY;
    public String getDescription() {
        return switch (this) {
            case INPATIENT -> "Requires bed allocation";
            case OUTPATIENT -> "Day visit only - No bed";
            case EMERGENCY -> "Priority care - No bed";
        };
    }
}

abstract class Person {
    protected String id, name, surname;
    protected PatientType type;
    public Person(String id, String n, String s) { this.id=id; this.name=n; this.surname=s; }
    public abstract PatientType getType();
    @Override public String toString() { return String.format("ID: %-5s | Name: %-10s %-10s | Type: %-10s", id, name, surname, getType()); }
}

class InPatient extends Person {
    private int bedNumber;
    public InPatient(String id, String n, String s, int bed) { super(id,n,s); this.bedNumber=bed; this.type=PatientType.INPATIENT; }
    @Override public PatientType getType() { return PatientType.INPATIENT; }
    public int getBedNumber() { return bedNumber; }
    @Override public String toString() { return super.toString() + String.format(" | Bed: %02d | %s", bedNumber, getType().getDescription()); }
}

class OutPatient extends Person {
    public OutPatient(String id, String n, String s) { super(id,n,s); this.type=PatientType.OUTPATIENT; }
    @Override public PatientType getType() { return PatientType.OUTPATIENT; }
}

class EmergencyPatient extends Person {
    private String severity;
    public EmergencyPatient(String id, String n, String s, String sev) { super(id,n,s); this.severity=sev; this.type=PatientType.EMERGENCY; }
    @Override public PatientType getType() { return PatientType.EMERGENCY; }
    @Override public String toString() { return super.toString() + String.format(" | Type: %-10s | Severity: %s", getType(), severity); }
}

public class HospitalSystem {
    public static final int TOTAL_BEDS = 20;
    public static final int BEDS_PER_ROW = 5;
    public static boolean[] beds = new boolean[TOTAL_BEDS];
    public static Person[][] ward = new Person[4][BEDS_PER_ROW];
    public static ArrayList<Person> patients = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n HOSPITAL PATIENT MANAGEMENT SYSTEM");
            System.out.println("1. Admit In-Patient (Allocate Bed)\n2. Register Out-Patient\n3. Register Emergency Patient\n4. Discharge Patient\n5. View All Patients\n6. View Ward Bed Layout\n7. Exit");
            System.out.print("Choice: ");
            try { choice = Integer.parseInt(sc.nextLine()); } catch(Exception e){ choice=0; }
            switch(choice){
                case 1: admitInPatientUI(); break;
                case 2: registerOutPatientUI(); break;
                case 3: registerEmergencyUI(); break;
                case 4: dischargeUI(); break;
                case 5: viewPatients(); break;
                case 6: viewWard(); break;
                case 7: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid choice");
            }
        } while(choice!=7);
    }

    public static boolean allocateInPatient(String id, String name, String surname){
        if(isDuplicateID(id)) return false;
        int bed = findFreeBed(); if(bed==-1) return false;
        InPatient p = new InPatient(id,name,surname,bed);
        patients.add(p); beds[bed]=true; ward[bed/BEDS_PER_ROW][bed%BEDS_PER_ROW]=p; return true;
    }
    public static int findFreeBed(){ for(int i=0;i<TOTAL_BEDS;i++) if(!beds[i]) return i; return -1; }
    public static boolean isDuplicateID(String id){ for(Person p:patients) if(p.id.equalsIgnoreCase(id)) return true; return false; }
    public static boolean dischargePatient(String id){
        for(int i=0;i<patients.size();i++){ Person p=patients.get(i); if(p.id.equalsIgnoreCase(id)){
            if(p instanceof InPatient){ int b=((InPatient)p).getBedNumber(); beds[b]=false; ward[b/BEDS_PER_ROW][b%BEDS_PER_ROW]=null; }
            patients.remove(i); return true; } } return false;
    }
    static void admitInPatientUI(){ System.out.print("ID: "); String id=sc.nextLine(); System.out.print("Name: "); String n=sc.nextLine(); System.out.print("Surname: "); String s=sc.nextLine(); if(allocateInPatient(id,n,s)) System.out.println("Admitted!"); else System.out.println("Failed - duplicate or full."); }
    static void registerOutPatientUI(){ System.out.print("ID: "); String id=sc.nextLine(); System.out.print("Name: "); String n=sc.nextLine(); System.out.print("Surname: "); String s=sc.nextLine(); if(isDuplicateID(id)){ System.out.println("Duplicate ID"); return; } patients.add(new OutPatient(id,n,s)); System.out.println("Out-Patient registered"); }
    static void registerEmergencyUI(){ System.out.print("ID: "); String id=sc.nextLine(); System.out.print("Name: "); String n=sc.nextLine(); System.out.print("Surname: "); String s=sc.nextLine(); System.out.print("Severity: "); String sev=sc.nextLine(); if(isDuplicateID(id)){ System.out.println("Duplicate ID"); return; } patients.add(new EmergencyPatient(id,n,s,sev)); System.out.println("Emergency registered"); }
    static void dischargeUI(){ System.out.print("ID to discharge: "); String id=sc.nextLine(); if(dischargePatient(id)) System.out.println("Discharged"); else System.out.println("Not found"); }
    static void viewPatients(){ if(patients.isEmpty()){ System.out.println("No patients"); return; } for(Person p:patients) System.out.println(p); }
    static void viewWard(){ System.out.println("\n--- WARD LAYOUT (X = Occupied) ---"); for(int r=0;r<ward.length;r++){ for(int c=0;c<BEDS_PER_ROW;c++){ if(ward[r][c]==null) System.out.print("[ ] "); else System.out.print("[ X] "); } System.out.println(); } }
}