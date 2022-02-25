package server;

import javax.persistence.*;

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

    public Activity(String name, int energy) {
        this.name = name;
        this.energy = energy;
    }

    public Activity() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public String getName() {
        return name;
    }

    public int getEnergy() {
        return energy;
    }

    @Override
    public int compareTo(Object o) {
        Activity otherActivity = (Activity)o;
        return (getEnergy() < otherActivity.getEnergy()?-1:1);
    }
}
