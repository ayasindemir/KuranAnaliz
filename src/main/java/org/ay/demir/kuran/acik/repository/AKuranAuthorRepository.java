package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranAuthor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranAuthorRepository extends CrudRepository<AKuranAuthor, Long> {

}
