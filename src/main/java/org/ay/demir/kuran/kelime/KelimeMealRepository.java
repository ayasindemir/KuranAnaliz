package org.ay.demir.kuran.kelime;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KelimeMealRepository extends CrudRepository<KelimeMeal, Long> {

	List<KelimeMeal> findBySureNo(String p_strSureNo);

	List<KelimeMeal> findByHarflerArapca(String p_strSureNo);

	List<KelimeMeal> findBySureNoAndAyetNo(String p_strSureNo, String p_strAyetNo);

	List<KelimeMeal> findByLatinKok(String p_strKok);

}
