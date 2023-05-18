package com.cooper.backend.puppies.domain;

import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PuppyRepositoryCustom {

    List<PuppyListResponseDTO> findPuppyListDesc(final Pageable pageable);

}
