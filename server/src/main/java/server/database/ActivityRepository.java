package server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import server.Activity;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    //public List<Activity> getActivities(int x);
}
