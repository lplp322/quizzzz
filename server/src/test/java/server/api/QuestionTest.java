package server.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import server.Activity;
import server.Question;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionTest {
    private Activity a, b, c, d;
    @BeforeEach
    public void init() {
        a = new Activity("A", 100);
        b = new Activity("B", 99);
        c = new Activity("C", 103);
        d = new Activity("D", 104);
    }
    @Test
    public void testTypeOne() {
        Question q = new Question(List.of(a, b, c, d), 0);
        assertEquals(q.getAnswer(), 100+"");
    }
    @Test
    public void testTypeTwo() {

    }
    @Test
    public void testTypeThree() {
        List<Activity> ll = new ArrayList<>();
        ll.add(a);
        ll.add(b);
        ll.add(c);
        ll.add(d);
        Question q = new Question(ll, 2);
        assertEquals(q.getAnswer(), "B");
    }
}
