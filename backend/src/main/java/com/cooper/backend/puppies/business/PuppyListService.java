package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.dto.PuppyListHttpResponse;
import org.springframework.data.domain.Pageable;

import java.util.List;

@FunctionalInterface
public interface PuppyListService {

    List<PuppyListHttpResponse> getPuppyList(final Pageable pageable);

}
