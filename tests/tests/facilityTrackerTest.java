package tests;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;
import src.FacilityPackage.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;
import org.junit.jupiter.api.Test;

class facilityTrackerTest {
	@Test
	void testFacilityProblems() {
		List<String> problems = new ArrayList<String>();
		Facility fac = new Facility(null, problems, null, 0, null, "fac1");
		String prob = "This is a problem";
		fac.addProblem(prob); 
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		Map<String, List<UsageTimeStamp>> usage = new HashMap<String, List<UsageTimeStamp>>();
		UsageManager usageMan = new UsageManager(null, null);
		Map<String, List<MaintTimeStamp>> maint = new HashMap<String, List<MaintTimeStamp>>();
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		Map<String, Facilities> facilityDir = new HashMap<String, Facilities>();
		FacilityTracker fact = new FacilityTracker(scheduleMan ,usageMan, maintMan);
		fact.addFacility(fac);
		System.out.println(fact.listFacilityProblems("fac1"));
		//assertEquals(fact.listFacilityProblems("fac1").contains("This is a problem"), true);
	}

	@Test
	void testFacilityInspections(){
		List<String> inspections = new ArrayList<String>();
		Facility fac = new Facility(null, null, inspections, 0, null, "fac1");
		String inspection = "This is an inspection";
		fac.addInspection(inspection); 
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		Map<String, List<UsageTimeStamp>> usage = new HashMap<String, List<UsageTimeStamp>>();
		UsageManager usageMan = new UsageManager(null, null);
		Map<String, List<MaintTimeStamp>> maint = new HashMap<String, List<MaintTimeStamp>>();
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		Map<String, Facilities> facilityDir = new HashMap<String, Facilities>();
		FacilityTracker fact = new FacilityTracker(scheduleMan ,usageMan, maintMan);
		fact.addFacility(fac);
		assertEquals(fact.listInspections("fac1").contains("This is an inspection"), true);
	}
	
	@Test
	void testFacilityDetails() {
		List<String> details = new ArrayList<String>();
		Facility fac = new Facility(details, null, null, 0, null, "fac1");
		String inspection = "This is a detail";
		fac.addDetail(inspection); 
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		Map<String, List<UsageTimeStamp>> usage = new HashMap<String, List<UsageTimeStamp>>();
		UsageManager usageMan = new UsageManager(null, null);
		Map<String, List<MaintTimeStamp>> maint = new HashMap<String, List<MaintTimeStamp>>();
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		Map<String, Facilities> facilityDir = new HashMap<String, Facilities>();
		FacilityTracker fact = new FacilityTracker(scheduleMan ,usageMan, maintMan);
		fact.addFacility(fac);
		assertEquals(fact.getFacilityInformation("fac1").contains("This is a detail"), true);
	}
	
	@Test
	void testFacilityCapacity() {
		Facility fac = new Facility(null, null, null, 150, null, "fac1");
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		Map<String, List<UsageTimeStamp>> usage = new HashMap<String, List<UsageTimeStamp>>();
		UsageManager usageMan = new UsageManager(null, null);
		Map<String, List<MaintTimeStamp>> maint = new HashMap<String, List<MaintTimeStamp>>();
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		Map<String, Facilities> facilityDir = new HashMap<String, Facilities>();
		FacilityTracker fact = new FacilityTracker(scheduleMan ,usageMan, maintMan);
		fact.addFacility(fac);
		assertEquals(fact.getAvailableCapacity("fac1"), 150);
	}
	
	@Test
	void testFacilityProblemRate() {
		Calendar startCal = Calendar.getInstance();
		startCal.set(Calendar.MONTH, 3);
		startCal.set(Calendar.DATE, 15);
		startCal.set(Calendar.HOUR, 4);
		startCal.set(Calendar.MINUTE, 0);
		Date start = startCal.getTime();
		List<String> problems = new ArrayList<String>();
		Facility fac = new Facility(null, problems, null, 0, start, "fac1");
		String prob = "This is a problem";
		fac.addProblem(prob); 
		fac.addProblem(prob);
		Map<String, List<TimeStamp>> schedule = new HashMap<String, List<TimeStamp>>();
		ScheduleManager scheduleMan = new ScheduleManager(null, null, null);
		Map<String, List<UsageTimeStamp>> usage = new HashMap<String, List<UsageTimeStamp>>();
		UsageManager usageMan = new UsageManager(null, null);
		Map<String, List<MaintTimeStamp>> maint = new HashMap<String, List<MaintTimeStamp>>();
		MaintenanceManager maintMan = new MaintenanceManager(null, null);
		Map<String, Facilities> facilityDir = new HashMap<String, Facilities>();
		FacilityTracker fact = new FacilityTracker(scheduleMan ,usageMan, maintMan);
		fact.addFacility(fac);
		assertEquals(fact.calcProblemRateForFacility("fac1"), 2);
	}
}
