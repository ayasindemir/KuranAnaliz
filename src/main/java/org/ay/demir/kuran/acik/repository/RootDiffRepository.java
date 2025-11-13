package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.RootDiff;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RootDiffRepository extends CrudRepository<RootDiff, Long> {

}
