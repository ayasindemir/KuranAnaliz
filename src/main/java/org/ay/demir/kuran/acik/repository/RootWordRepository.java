package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.RootWord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootWordRepository extends CrudRepository<RootWord, Long> {

}
