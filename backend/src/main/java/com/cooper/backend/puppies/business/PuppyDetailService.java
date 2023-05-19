package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.dto.PuppyDetailResponseDTO;

@FunctionalInterface
public interface PuppyDetailService {

    PuppyDetailResponseDTO getPuppyDetail(final Long puppyId);

}
