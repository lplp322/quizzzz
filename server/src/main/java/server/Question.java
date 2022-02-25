package server;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Question {
    // the actual question
    private String question;
    //
    private List<String> answers;
    //each question is one of three types
    private int type;



    public Question() {

        //generate a random type
        type = (new Random()).nextInt(3);

        switch (type) {
            case 0:
                generateTypeOne();
                break;
            case 1:
                generateTypeTwo();
                break;
            case 2:
                generateTypeThree();
                break;
        }
    }

    /** Generates a question in the following format: Open-ended questions (estimating the answer) that are randomised.
     *
     */
    private void generateTypeOne() {
        Activity act = new Activity("DAS", 2);

        question = String.format("How much energy does %s use", act.getName());

        answers.add(act.getEnergy()+"");

        List<Integer> deviations = List.of(1, -1, 2, -2, 3, -3, 4, -4, 5, -5);
        Collections.shuffle(deviations);

        for(int i = 0; i < 3; i++) {
            int randomlyGeneratedEnergy = act.getEnergy() + deviations.get(i);
            answers.add(randomlyGeneratedEnergy+"");
        }
    }

    /** Generates a question in the following format: Comparing the energy usage of three activities.
     *
     */
    private void generateTypeTwo() {
        //Activity act = dt.getActivities(1);

    }

    /** Generates a question in the following format: Comparing the energy usage of one activity to three other activities:
     *
     */
    private void generateTypeThree() {
        /*List<Activity> activities = dt.getActivities(4);

        Collections.shuffle(activities);
        question = String.format("Instead of %s you could use:", activities.get(0).getName());

        for(int i = 1; i < 4; i++) {
            answers.add(activities.get(i).getName()+"");
        }*/
    }
}
