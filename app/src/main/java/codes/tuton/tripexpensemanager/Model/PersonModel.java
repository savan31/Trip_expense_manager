package codes.tuton.tripexpensemanager.Model;

import java.util.List;

public class PersonModel {
    public PersonModel() {
    }

    int personId,amountDebit,amountCredit;
    String name,timeStampTrip;



    public PersonModel(int personId, int amountDebit, int amountCredit, String name, String timeStampTrip) {
        this.personId = personId;
        this.amountDebit = amountDebit;
        this.amountCredit = amountCredit;
        this.name = name;
        this.timeStampTrip = timeStampTrip;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getAmountDebit() {
        return amountDebit;
    }

    public void setAmountDebit(int amountDebit) {
        this.amountDebit = amountDebit;
    }

    public int getAmountCredit() {
        return amountCredit;
    }

    public void setAmountCredit(int amountCredit) {
        this.amountCredit = amountCredit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStampTrip() {
        return timeStampTrip;
    }

    public void setTimeStampTrip(String timeStampTrip) {
        this.timeStampTrip = timeStampTrip;
    }
}
