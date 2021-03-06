package com.example.phonebookweb.repo;

import com.example.phonebookweb.models.PhoneNumber;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

public interface PhoneRepository extends CrudRepository<PhoneNumber, Long> {
 Iterable<PhoneNumber> findAll(Sort sort);
}
