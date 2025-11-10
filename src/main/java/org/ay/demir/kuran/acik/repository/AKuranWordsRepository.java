package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranWords;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranWordsRepository extends CrudRepository<AKuranWords, Long> {

}
