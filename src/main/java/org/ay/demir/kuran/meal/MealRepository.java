package org.ay.demir.kuran.meal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

	List<Meal> findBySureNoAndAyetNo(Integer sureNo, Integer ayetNo);

	List<Meal> findBySureNoAndYazar(Integer sureNo, String yazar);

	List<Meal> findByYazar(String yazar);

}
