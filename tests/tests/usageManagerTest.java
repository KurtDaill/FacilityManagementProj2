package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.*;

import org.junit.jupiter.api.Test;
import src.FacilityPackage.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;

class usageManagerTest {

	@Test
	void testAssignFacilityToUse() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.MONTH, 3);
		endCal.set(Calendar.DATE, 30);
		endCal.set(Calendar.HOUR, 4);
		endCal.set(Calendar.MINUTE, 0);
		Date end = endCal.getTime();
		
		LinkedList<TimeStamp> appt = new LinkedList<TimeStamp>();
		LinkedList<UsageTimeStamp> UsageTimeStampList = new LinkedList<UsageTimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"tesing the dates");
		appt.add(maintAppt);
		UsageTimeStampList.add(maintAppt);
		
		Facility fac = new Facility(null, null, null, 0, null, "fac1");
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		UsageManager useMan = new UsageManager(null, scheduleMan);
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		FacilityTracker fact = new FacilityTracker(scheduleMan, useMan, maintMan);
		fact.addFacility(fac, appt, UsageTimeStampList);
		
		assertEquals(useMan.assignFacilityToUse("fac1", maintAppt), true);
	}
	
	@Test
	void testListActualUsage() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		Calendar endCal = Calendar.getInstance();
		endCal.set(Calendar.MONTH, 3);
		endCal.set(Calendar.DATE, 30);
		endCal.set(Calendar.HOUR, 4);
		endCal.set(Calendar.MINUTE, 0);
		Date end = endCal.getTime();
		
		LinkedList<TimeStamp> appt = new LinkedList<TimeStamp>();
		LinkedList<UsageTimeStamp> UsageTimeStampList = new LinkedList<UsageTimeStamp>();
		UsageTimeStamp maintAppt = new UsageTimeStamp(start,end,"tesing the dates");
		appt.add(maintAppt);
		UsageTimeStampList.add(maintAppt);
		
		Facility fac = new Facility(null, null, null, 0, null, "fac1");
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		UsageManager useMan = new UsageManager(null, scheduleMan);
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		FacilityTracker fact = new FacilityTracker(scheduleMan, useMan, maintMan);
		fact.addFacility(fac, appt, UsageTimeStampList);
		
		assertEquals(useMan.listActualUsage("fac1").get(0).getStartTime(), start);
	}

}
