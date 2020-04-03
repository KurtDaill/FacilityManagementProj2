# Facility Management
The basic task of the Facility Management program is to manage and edit the wealth of data involved in managing a number of facilities over time.
## Time Stamps
One of the most basic needs of the system is to manage events that take place over time. The time stamp class is essentially a wrapper of data for how long an event lasts, and it's sub classes (Maintenance and Usage) contain specific data relevant to an event type.
## Facility
A catch-all for data that is specific to a particular building or facility, and that does not relate to a more specific purpose. This includes a name, description, and data that isn't used by larger modules.
## Facility Tracker
The Facility Tracker class is designed to be a 'parent' class that keeps the master list of all Facilities, updates other classes of changes to that list, and is the preferred interface for other classes to retrieve data held in the Facility class. 
## Schedule Manager
The Schedule Manager keeps track of when facilities are scheduled for activity, allows for checks and changes to schedules, and additionally is responsible for keeping track of time for the other manager classes.

## Usage Manager
The Usage Manager handles normal usage of facilities, and can be queried for information on how that facility has been used.

## Maintenance Manager
The Maintenance Manager handles data relevant to maintenance: requests for maintenance, facilities' maintenance histories and costs, downtime, etc.
