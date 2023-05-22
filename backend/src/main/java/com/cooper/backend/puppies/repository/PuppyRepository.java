package com.cooper.backend.puppies.repository;

import com.cooper.backend.puppies.dto.PuppyDetailResponseDTO;
import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PuppyRepository {

    List<PuppyListResponseDTO> findPuppyListDesc(final Pageable pageable);

    Optional<PuppyDetailResponseDTO> findPuppyDetailByPuppyId(final Long puppyId);

}
