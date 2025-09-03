package org.ay.demir.kuran.sure;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SureRepository extends CrudRepository<Sure, Long> {

}
