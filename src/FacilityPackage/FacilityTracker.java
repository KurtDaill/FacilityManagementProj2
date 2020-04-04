package src.FacilityPackage;
import java.util.*;
import src.ManagerPackage.*;
import src.TimeStamps.*;
public class FacilityTracker{
	private Map<String, Facilities> facilityDirectory = new HashMap<String, Facilities>();
	private ScheduleManager scheduleModule;
	private UsageManager usageModule;
	private MaintenanceManager maintModule;

	public FacilityTracker(ScheduleManager scheduleModule, UsageManager usageModule, MaintenanceManager maintModule) {
		this.scheduleModule = scheduleModule;
		this.usageModule = usageModule;
		this.maintModule = maintModule;
	}

	public List<String> listFacilityProblems(String facName){
		return lookUp(facName).getProblems();
	}

	public List<String> listInspections(String facName){
		return lookUp(facName).getInspections();
	}

	public List<String> getFacilityInformation(String facName){
		return lookUp(facName).getDetails();
	}

	public int getAvailableCapacity(String facName){
		return lookUp(facName).getCapacity();
	}

	public float calcProblemRateForFacility(String facName){
		return lookUp(facName).getProblemRate();
	}

	public void addFacilityDetail(String facName, String newDetail){
		lookUp(facName).getDetails().add(newDetail);
	}

	public void addFacility(Facilities newFac, LinkedList<TimeStamp> newTime, LinkedList<UsageTimeStamp> newUsageTime){
		facilityDirectory.put(newFac.getName(), newFac);
		if(scheduleModule != null) scheduleModule.updateFacilities(newFac, newTime);
		if(usageModule != null) usageModule.updateFacilities(newFac, newUsageTime);
		if(maintModule != null) maintModule.updateFacilities(newFac);
	}

	public Facilities lookUp(String facName){
		return facilityDirectory.get(facName);
	}
}
