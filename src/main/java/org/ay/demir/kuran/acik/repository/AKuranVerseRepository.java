package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranVerses;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranVerseRepository extends CrudRepository<AKuranVerses, Long> {

}
