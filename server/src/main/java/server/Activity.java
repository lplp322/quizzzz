package server;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.SequenceGenerator;
import javax.persistence.Id;

@Entity
public class Activity implements Comparable {
    @Id
    @SequenceGenerator(
            name = "activity_sequence",
            sequenceName = "activity_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "activity_sequence"
    )
    private Long Id;
    private String name;
    private int energy;

    /**
     * Constructor for activity
     * @param name the name of the activity
     * @param energy the energy usage of the activity
     */
    public Activity(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    /**
     * Empty constructor
     */
    public Activity() {

    }

    /**
     * sets the name of the activity
     * @param name the name of the activity
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * sets the energy of the activity
     * @param energy the energy of the activity
     */
    public void setEnergy(int energy) {
        this.energy = energy;
    }

    /**
     * returns the name of the activity
     * @return the name of the activity
     */
    public String getName() {
        return name;
    }

    /**
     * returns the energy of the activity
     * @return the evergy of the activity
     */
    public int getEnergy() {
        return energy;
    }

    /**
     * compares it to another object
     * @param o
     * @return
     */
    @Override
    public int compareTo(Object o) {
        Activity otherActivity = (Activity)o;
        return (getEnergy() < otherActivity.getEnergy()?-1:1);
    }

    /**
     * returns to string
     * @return
     */
    @Override
    public String toString() {
        return "Activity{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", energy=" + energy +
                '}';
    }
}
