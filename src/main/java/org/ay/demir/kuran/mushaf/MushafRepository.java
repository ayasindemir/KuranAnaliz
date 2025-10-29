package org.ay.demir.kuran.mushaf;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MushafRepository extends CrudRepository<Mushaf, Long> {

	List<Mushaf> findBySureNo(int sureNo);

	List<Mushaf> findBySureNoAndAyetNo(int sureNo, int ayetNo);

}
