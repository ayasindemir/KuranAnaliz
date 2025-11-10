package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranRootWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranRootWordsRepository extends CrudRepository<AKuranRootWord, Long> {

}
