package codes.tuton.tripexpensemanager.Model;

public class TripModel {
    public TripModel() {
    }
    int primaryId;
    String tripName,description,date,timeStampTrip;

    public TripModel(int primaryId, String tripName, String description, String date, String timeStampTrip) {
        this.primaryId = primaryId;
        this.tripName = tripName;
        this.description = description;
        this.date = date;
        this.timeStampTrip = timeStampTrip;
    }

    public String getTripName() {
        return tripName;
    }

    public void setTripName(String tripName) {
        this.tripName = tripName;
    }

    public int getPrimaryId() {
        return primaryId;
    }

    public void setPrimaryId(int primaryId) {
        this.primaryId = primaryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStampTrip() {
        return timeStampTrip;
    }

    public void setTimeStampTrip(String timeStampTrip) {
        this.timeStampTrip = timeStampTrip;
    }
}
