package org.ay.demir.kuran.meal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

	List<Meal> findBySureNoAndAyetNo(Integer p_iSureNo, Integer p_iAyetNo);

}
