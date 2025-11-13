package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Audio;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AudioRepository extends CrudRepository<Audio, Long> {

}
