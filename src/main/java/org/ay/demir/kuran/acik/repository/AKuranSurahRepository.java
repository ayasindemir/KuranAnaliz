package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranSurah;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranSurahRepository extends CrudRepository<AKuranSurah, Long> {

}
