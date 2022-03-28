package das.eu.internship.Test.repository;

import das.eu.internship.Test.entities.TechPassport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TechPassportRepository extends JpaRepository<TechPassport, Long> {
}
