package org.ay.demir.kuran.acik.repository;

import java.util.List;

import org.ay.demir.kuran.acik.model.Verse;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerseRepository extends CrudRepository<Verse, Long> {

	List<Verse> findBySN(Long id);

}
