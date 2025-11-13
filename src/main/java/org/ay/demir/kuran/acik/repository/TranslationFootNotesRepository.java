package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.TranslationFootNote;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TranslationFootNotesRepository extends CrudRepository<TranslationFootNote, Long> {

}
