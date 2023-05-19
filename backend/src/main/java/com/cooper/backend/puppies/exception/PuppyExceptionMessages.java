package com.cooper.backend.puppies.exception;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PuppyExceptionMessages {

    public static final String PUPPY_NOT_FOUND_BY_ID_MESSAGE = "해당 puppy id 의 강아지는 존재하지 않습니다.";

}
