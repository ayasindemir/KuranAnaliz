package org.ay.demir.kuran.acik.repository;

import java.util.List;

import org.ay.demir.kuran.acik.model.AKuranTranslation;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranTranslationRepository extends CrudRepository<AKuranTranslation, Long> {

	public List<AKuranTranslation> findByAuthorId(Long authorId);

}
