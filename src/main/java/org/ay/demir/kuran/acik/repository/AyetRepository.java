package org.ay.demir.kuran.acik.repository;

import java.util.List;

import org.ay.demir.kuran.acik.model.Ayet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AyetRepository extends CrudRepository<Ayet, Long> {

	List<Ayet> findBySureId(Long sureId);

}
