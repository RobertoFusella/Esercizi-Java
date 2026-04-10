package events;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VenueRepository extends JpaRepository<Venue, Integer> {
    Optional<Venue> findById(int id);
}
