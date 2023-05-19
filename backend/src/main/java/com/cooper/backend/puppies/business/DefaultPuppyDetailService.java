package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.domain.PuppyRepository;
import com.cooper.backend.puppies.dto.PuppyDetailResponseDTO;
import com.cooper.backend.puppies.exception.PuppyDetailNotFoundException;
import com.cooper.backend.puppies.exception.PuppyExceptionMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.cooper.backend.puppies.exception.PuppyExceptionMessages.PUPPY_NOT_FOUND_BY_ID_MESSAGE;

@Service
@RequiredArgsConstructor
public class DefaultPuppyDetailService implements PuppyDetailService {

    private final PuppyRepository puppyRepository;

    @Override
    public PuppyDetailResponseDTO getPuppyDetail(final Long puppyId) {
        return puppyRepository.findPuppyDetailByPuppyId(puppyId)
                .orElseThrow(() -> new PuppyDetailNotFoundException(PUPPY_NOT_FOUND_BY_ID_MESSAGE));
    }

}
