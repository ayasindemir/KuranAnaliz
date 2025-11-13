package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Surah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurahRepository extends CrudRepository<Surah, Long> {

}
