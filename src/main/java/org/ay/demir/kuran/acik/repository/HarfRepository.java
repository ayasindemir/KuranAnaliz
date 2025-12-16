package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Harf;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HarfRepository extends CrudRepository<Harf, Long> {

}
