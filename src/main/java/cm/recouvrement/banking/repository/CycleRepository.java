package cm.recouvrement.banking.repository;

import cm.recouvrement.banking.entity.Cycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CycleRepository extends JpaRepository<Cycle, Long> {

    public Cycle findCycleByLevel(Integer level);
}
