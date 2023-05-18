package com.cooper.backend.puppies.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PuppyRepository extends JpaRepository<Puppy, Long>, PuppyRepositoryCustom {

}
