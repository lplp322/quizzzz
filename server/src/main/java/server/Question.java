package server;

import server.database.ActivityRepository;

import java.util.*;

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
        type = (new Random()).nextInt(3);
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

    public Question(ActivityRepository dt, int type) {
        this.dt = dt;
        this.type = type;
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

    /** Generates a question in the following format: Open-ended questions (estimating the answer) that are randomised.
     *
     */
    private void generateTypeOne() {
        Activity act = activities.get(0);

        question = String.format("How much energy does %s use", act.getName());

        answers.add(act.getEnergy()+"");
        answer = act.getEnergy()+"";

        Integer[] types = {1, -1, 2, -2, 3, -3, 4, -4, 5, -5};
        List<Integer> deviations = Arrays.asList(types);
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
        //TO BE IMPLEMENTED
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

    /**
     * returns the answer
     * @return
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * returns the question
     * @return
     */
    public String getQuestion() {
        return question;
    }

    /**
     * returns all the possible answers
     * @return
     */
    public List<String> getAnswers() {
        return answers;
    }

    /**
     * returns the type of the question(0, 1, 2)
     * @return
     */
    public int getType() {
        return type;
    }

    /**
     * returns the 4 activities we fetched from the database
     * @return
     */
    public List<Activity> getActivities() {
        return activities;
    }

    /**
     * returns the object as a string
     * @return
     */
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
