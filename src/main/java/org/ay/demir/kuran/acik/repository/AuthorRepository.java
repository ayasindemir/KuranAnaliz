package org.ay.demir.kuran.acik.repository;

import org.ay.demir.kuran.acik.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {

}
