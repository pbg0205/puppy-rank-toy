package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.repository.PuppyRepository;
import com.cooper.backend.puppies.dto.PuppyListHttpResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPuppyListService implements PuppyListService {

    private final PuppyRepository puppyRepository;

    @Override
    public List<PuppyListHttpResponse> getPuppyList(final Pageable pageable) {
        return puppyRepository.findPuppyListDesc(pageable);
    }

}
