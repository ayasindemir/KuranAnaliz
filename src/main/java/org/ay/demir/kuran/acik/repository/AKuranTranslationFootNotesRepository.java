package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranTranlationFootNotes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranTranslationFootNotesRepository extends CrudRepository<AKuranTranlationFootNotes, Long> {

}
