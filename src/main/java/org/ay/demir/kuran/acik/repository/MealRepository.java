package org.ay.demir.kuran.acik.repository;

import java.util.List;

import org.ay.demir.kuran.acik.model.Meal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealRepository extends CrudRepository<Meal, Long> {

	public List<Meal> findByYazarId(Long yazarId);

}
