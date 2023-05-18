package com.cooper.backend.puppies.business;

import com.cooper.backend.puppies.domain.PuppyRepository;
import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultPuppyListService implements PuppyListService {

    private final PuppyRepository puppyRepository;

    @Override
    public List<PuppyListResponseDTO> getPuppyList(final Pageable pageable) {
        return puppyRepository.findPuppyListDesc(pageable);
    }

}
