package server.database;

import commons.LeaderboardEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;

@Repository
public interface LeaderboardRepository extends JpaRepository<LeaderboardEntry, Long> {

    /**
     * Selects all entries ordered by score
     * @return LinkedList containing all entries in sorted order
     */
    @Query("SELECT a FROM LeaderboardEntry a ORDER BY a.score DESC")
    LinkedList<LeaderboardEntry> getAllLeaderboardEntriesOrderedByScore();

    /**
     * Deletes all entries
     */
    @Transactional
    @Modifying
    @Query("DELETE FROM LeaderboardEntry")
    void deleteAllEntries();
}