package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.AKuranRootDiffs;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AKuranRootDiffsRepository extends CrudRepository<AKuranRootDiffs, Long> {

}
