package org.ay.demir.kuran.acik.repository;

import java.util.List;

import org.ay.demir.kuran.acik.model.Kelime;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelimeRepository extends CrudRepository<Kelime, Long> {

	public List<Kelime> findBySureId(Long sureId);
	
	public List<Kelime> findBySureIdAndAyetNo(Long sureId, Long ayetNo);

}
