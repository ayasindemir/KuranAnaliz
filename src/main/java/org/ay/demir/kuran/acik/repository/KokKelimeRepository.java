package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.KokKelime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KokKelimeRepository extends CrudRepository<KokKelime, Long> {

}
