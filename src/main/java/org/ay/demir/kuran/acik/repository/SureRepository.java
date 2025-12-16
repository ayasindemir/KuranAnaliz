package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Sure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SureRepository extends CrudRepository<Sure, Long> {

}
