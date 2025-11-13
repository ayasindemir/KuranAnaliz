package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepository extends CrudRepository<Word, Long> {

}
