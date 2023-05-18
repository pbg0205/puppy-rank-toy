package com.cooper.backend.puppies.presentation;

import com.cooper.backend.common.response.ApiResult;
import com.cooper.backend.puppies.business.PuppyListService;
import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/puppies")
@RequiredArgsConstructor
public class PuppyController {

    private final PuppyListService puppyListService;

    @GetMapping
    public ApiResult<List<PuppyListResponseDTO>> getPuppyList(@PageableDefault(size = 6) final Pageable pageable) {
        List<PuppyListResponseDTO> puppyList = puppyListService.getPuppyList(pageable);
        return ApiResult.success(puppyList);
    }

}
