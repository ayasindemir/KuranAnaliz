package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.MealDipNot;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MealDipNotRepository extends CrudRepository<MealDipNot, Long> {

}
