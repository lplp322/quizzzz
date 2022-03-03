package server.database;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import server.Activity;
import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, String> {
    @Query("SELECT a FROM Activity a")
    List<Activity> getAllActivities();
}