package com.cooper.backend.puppies.repository;

import com.cooper.backend.puppies.dto.PuppyDetailHttpResponse;
import com.cooper.backend.puppies.dto.PuppyListHttpResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PuppyRepository {

    List<PuppyListHttpResponse> findPuppyListDesc(final Pageable pageable);

    Optional<PuppyDetailHttpResponse> findPuppyDetailByPuppyId(final Long puppyId);

}
