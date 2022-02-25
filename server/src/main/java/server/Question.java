package server;

import server.database.ActivityRepository;
import java.util.ArrayList;
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
    //4 random activities from the database
    private List<Activity> activities;
    //the database
    private ActivityRepository dt;
    //Correct answer
    private String answer;

    public Question(ActivityRepository dt) {
        this.dt = dt;
        //type = (new Random()).nextInt(3);
        type = 2;
        List<Activity> allActivities = dt.getAllActivities();
        Collections.shuffle(allActivities);
        activities = new ArrayList<>();

        answers = new ArrayList<>();

        for(int i = 0; i < 4; i++) {
            activities.add(allActivities.get(i));
        }

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

    public Question() {
        type = (new Random()).nextInt(3);
    }

    /** Generates a question in the following format: Open-ended questions (estimating the answer) that are randomised.
     *
     */
    private void generateTypeOne() {
        Activity act = activities.get(0);

        question = String.format("How much energy does %s use", act.getName());

        answers.add(act.getEnergy()+"");
        answer = act.getEnergy()+"";

        List<Integer> deviations = List.of(1, -1, 2, -2, 3, -3, 4, -4, 5, -5);
        Collections.shuffle(deviations);

        for(int i = 0; i < 3; i++) {
            int randomlyGeneratedEnergy = act.getEnergy() + deviations.get(i);
            answers.add(randomlyGeneratedEnergy+"");
        }
        Collections.shuffle(answers);
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
        Collections.sort(activities);
        question = String.format("Instead of %s you could use:", activities.get(1).getName());

        answer = activities.get(0).getName();

        for(int i = 0; i < 4; i++) {
            if(i!=1)
                answers.add(activities.get(i).getName()+"");
        }
        Collections.shuffle(answers);
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public int getType() {
        return type;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", type=" + type +
                ", activities=" + activities +
                ", dt=" + dt +
                ", answer='" + answer + '\'' +
                '}';
    }
}
