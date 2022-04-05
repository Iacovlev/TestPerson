package das.eu.internship.Test.repository;

import das.eu.internship.Test.entities.PersonProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonProfileRepository extends JpaRepository<PersonProfile, Integer> {
}
