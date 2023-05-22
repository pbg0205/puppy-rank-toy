package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.dto.PuppyDetailHttpResponse;

@FunctionalInterface
public interface PuppyDetailService {

    PuppyDetailHttpResponse getPuppyDetail(final Long puppyId);

}
