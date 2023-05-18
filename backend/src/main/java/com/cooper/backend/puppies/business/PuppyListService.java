package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

@FunctionalInterface
public interface PuppyListService {

    List<PuppyListResponseDTO> getPuppyList(final Pageable pageable);

}
