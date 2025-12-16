package org.ay.demir.kuran.acik.service;

import java.util.Collections;
import java.util.List;

import org.ay.demir.kuran.acik.AcikKuranUtils;
import org.ay.demir.kuran.acik.model.Meal;
import org.ay.demir.kuran.acik.model.Sure;
import org.ay.demir.kuran.acik.model.MealDipNot;
import org.ay.demir.kuran.acik.model.Yazar;
import org.ay.demir.kuran.acik.repository.MealDipNotRepository;
import org.ay.demir.kuran.acik.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.EntityManager;

@Service
public class MealService {

	@Autowired
	private SureService sureService;

	@Autowired
	private YazarService yazarService;

	@Autowired
	private MealRepository mealRepo;

	@Autowired
	private MealDipNotRepository dipNotRepository;

	@Autowired
	private EntityManager entityManager;

	private static final String URL = "https://api.acikkuran.com/surah/";

	@Transactional
	public void indir() throws Exception {

		List<Sure> sureList = sureService.getAll();

		for (Sure sure : sureList) {

			List<Yazar> yazarList = yazarService.getAll();

			for (Yazar yazar : yazarList) {
				JsonNode root = AcikKuranUtils.downloadContent(URL + sure.id + "?author=" + yazar.id);

				JsonNode jSure = root.get("data");

				for (JsonNode jAyetler : jSure.get("verses")) {
					JsonNode jMeal = jAyetler.get("translation");
					Meal meal = new Meal();
					meal.id = jMeal.get("id").asLong();
					meal.meal = jMeal.get("text").asText();
					meal.yazarId = jMeal.get("author").get("id").asLong();
					meal.ayetNo = jAyetler.get("verse_number").asLong();
					meal.sureId = sure.id;
					entityManager.merge(meal);

					for (JsonNode jMealDipNot : jMeal.get("footnotes")) {
						MealDipNot dipNot = new MealDipNot();
						dipNot.id = jMealDipNot.get("id").asLong();
						dipNot.dipNot = jMealDipNot.get("text").asText();
						dipNot.notNo = jMealDipNot.get("number").asLong();
						dipNot.yazarId = meal.yazarId;
						dipNot.sureId = meal.sureId;
						dipNot.ayetNo = meal.ayetNo;

						if (dipNot.id != null) {
							entityManager.merge(dipNot);
						}
					}
					entityManager.flush();
					entityManager.clear();

					System.out.println("Sure: " + jSure.get("id") + " Yazar: " + yazar.id + " Ayet: " + meal.ayetNo);
				}
			}
		}
	}

	public List<Meal> getByYazarId(Long yazarId) {
		List<Meal> mealList = (List<Meal>) mealRepo.findByYazarId(yazarId);
		Collections.sort(mealList);
		return mealList;
	}

	public List<MealDipNot> getAllDipNot() {
		return (List<MealDipNot>) dipNotRepository.findAll();
	}

}
