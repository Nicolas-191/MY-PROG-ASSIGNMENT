package com.mycompany.hospitalpatientsystem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HospitalSystemTest {
    @BeforeEach
    public void setUp() {
        HospitalSystem.patients.clear();
        for(int i=0; i<HospitalSystem.TOTAL_BEDS; i++) HospitalSystem.beds[i]=false;
        for(int r=0; r<HospitalSystem.ward.length; r++) for(int c=0; c<HospitalSystem.BEDS_PER_ROW; c++) HospitalSystem.ward[r][c]=null;
    }
    @Test public void testAllocateSuccess() { assertTrue(HospitalSystem.allocateInPatient("P01","John","Doe")); }
    @Test public void testDuplicateID() { HospitalSystem.allocateInPatient("P01","John","Doe"); assertFalse(HospitalSystem.allocateInPatient("P01","Jane","Doe")); }
    @Test public void testWardFull() { for(int i=0;i<20;i++) HospitalSystem.allocateInPatient("ID"+i,"N"+i,"S"+i); assertFalse(HospitalSystem.allocateInPatient("LAST","No","Bed")); }
    @Test public void testDischarge() { HospitalSystem.allocateInPatient("P01","John","Doe"); assertTrue(HospitalSystem.dischargePatient("P01")); }
    @Test public void testFindFreeBed() { assertEquals(0, HospitalSystem.findFreeBed()); }
}