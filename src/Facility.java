import java.util.*;

public abstract class Facility{
	private List<String> details = new ArrayList<String>();
	private List<String> problemHistory = new ArrayList<String>();
	private List<UsageTimeStamp> useHistory = new ArrayList<UsageTimeStamp>();
	private List<MaintTimeStamp> maintHistory = new ArrayList<MaintTimeStamp>();
	private List<TimeStamp> futureSchedule = new ArrayList<TimeStamp>();
	private List<String> Inspections = new ArrayList<String>();
	private int capacity;
	private Date startDate = new Date();
	private Date currentDate = new Date();
	
	public int getProblemRate() { // This will calculate the rate of problems based on the timeframe of the facility
		int numberOfProblems = problemHistory.size(); 
		int timeFrame = startDate.compareTo(currentDate);
		int problemRate = numberOfProblems / timeFrame;
		return problemRate;
		
	}

	public int getDownTime() { //prints out the amount of time the building was not used while it was open
		int timeSinceBuildingOpen = startDate.compareTo(currentDate);
		int totalDownTime = 0;
		for(int i = 0; i < futureSchedule.size(); i++) {
			int DownTime = futureSchedule.get(i).startTime.compareTo(futureSchedule.get(i).endTime);
			totalDownTime += DownTime;
		}
		int DownTime = timeSinceBuildingOpen - totalDownTime;
		return DownTime;
	}

	public double getMaintCost() { //returns the total maintenance cost in USD of the facility over time
	    double cost = 0;
		for(MaintTimeStamp maint : maintHistory){
		    cost += maint.getCostUSD();
        }

	    return cost;
	}

	public List<String> getProblems(){ //returns the problem history list
		return problemHistory; 
	}

	public void update() { //Looks through the future schedule. If any of the events has already happened it removes it and adds it to the history
		for(int i = 0; i < futureSchedule.size(); i++) {
			if (futureSchedule.get(i).endTime.before(currentDate) == true) {
				TimeStamp pastDateHistory = futureSchedule.get(i);
				futureSchedule.remove(i);
				if(pastDateHistory instanceof UsageTimeStamp){
				    useHistory.add((UsageTimeStamp) pastDateHistory);
                } else if(pastDateHistory instanceof MaintTimeStamp){
                    maintHistory.add((MaintTimeStamp) pastDateHistory);
                } else {
				    System.out.print("TimeStamp Type not recognized.");
                }
			}
		}
	}

	public boolean addUsage(TimeStamp activity) { //adds a TimeStamp to the futureSchudule
	    for(TimeStamp stamp : futureSchedule){
		    if(stamp.doWeOverlap(activity)){
		        return false;
            }
        }
        futureSchedule.add(activity);
		return true;
	}

	public void vacateTime(TimeStamp clearingTime) { // removes an event from futureSchedule if the event is during clearingTime
		for(int i = 0; i < futureSchedule.size(); i++) {
			if (clearingTime.getStartTime().compareTo(startDate) >= 0) {
				if (futureSchedule.get(i).endTime.compareTo(clearingTime.endTime)<= 0) {
					futureSchedule.remove(i);
				}
			}
		}
	}

	public int getUsageRate() { // finds the rate someone has used the facility compared to the time since its initial opening
		int timeSinceBuildingOpen = startDate.compareTo(currentDate);
		int totalUsageTime = 0;
		for(UsageTimeStamp use : useHistory) {
			int UsageTime = use.getStartTime().compareTo(use.getEndTime());
			totalUsageTime += UsageTime;
		}
		int usageRate = totalUsageTime / timeSinceBuildingOpen;
		return usageRate;
	}

	public List<TimeStamp> getSchedule(){ //returns futureSchedule
		return futureSchedule;
	}

	public List<String> getInspections(){// returns Inspections
		return Inspections;
	}

	public List<MaintTimeStamp> getMaintHistory(){ //returns maintHistory
		return maintHistory;
	}

	public void addMaint(MaintTimeStamp maint) { //adds an TimeStamp to maintHistory
		maintHistory.add(maint);
		vacateTime(maint);
	}

	public List<String> getDetails(){
	    return details;
    }

	public int getCapacity(){ return capacity; }
}
