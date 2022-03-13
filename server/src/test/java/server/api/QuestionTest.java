package server.api;
//CHECKSTYLE:OFF
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Activity;
import server.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    private Activity a, b, c, d;
    private TestActivityRepository dtBase;
    @BeforeEach
    public void init() {
        a = new Activity("A", 100);
        b = new Activity("B", 99);
        c = new Activity("C", 103);
        d = new Activity("D", 104);

    }
    @Test
    public void testTypeOne() {
        List<Activity> activities = new ArrayList<>();
        activities.add(a);
        activities.add(a);
        activities.add(a);
        activities.add(a);
        dtBase = new TestActivityRepository(activities);

        Question q = new Question(dtBase, 0);
        assertEquals(100+"", q.getAnswer());
    }
    @Test
    public void testTypeTwo() {

    }
    @Test
    public void testTypeThree() {
        List<Activity> activities = new ArrayList<>();
        activities.add(a);
        activities.add(b);
        activities.add(c);
        activities.add(d);
        dtBase = new TestActivityRepository(activities);

        Question q = new Question(dtBase, 2);
        assertEquals("B", q.getAnswer());
    }
}
