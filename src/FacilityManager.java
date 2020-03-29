import java.util.*;


public abstract class FacilityManager extends Facility{
	private Map<String, Facility> directory = new HashMap<String, Facility>();
	
	public Facility lookUp(String facName) { // returns the facility located at the key "facName"
		Facility FacilityTest = directory.get(facName);
		return FacilityTest;
	}

	public void addFacility(String facName, Facility newFac) { // adds a facility to the directory
		directory.put(facName,newFac);
	}

	public boolean removeFacility(String facName, Facility newFac) { //removes a facility from the directory if it exists
		if( directory.remove(facName, newFac)) {
			return true;
		}
		else {
			System.out.println("Nothing to remove");
			return false;
		}
	}

	public List<Facility> listFacilities(){ // returns all facilities located in the directory
		List<Facility> facilityList = new LinkedList<Facility>(directory.values()); //directory.keySet() if not working
		return facilityList;
	}

	public void addFacilityDetail(String facName, String detail) { //adds detail to a facility
		Facility FacilityName = directory.get(facName);
		FacilityName.getDetails().add(detail);
	}
	
}

