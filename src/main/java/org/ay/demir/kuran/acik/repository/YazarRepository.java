package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Yazar;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YazarRepository extends CrudRepository<Yazar, Long> {

}
