package com.cooper.backend.puppies.presentation;

import com.cooper.backend.puppies.business.PuppyListService;
import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.operation.preprocess.OperationResponsePreprocessor;
import org.springframework.restdocs.snippet.Snippet;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs
class PuppyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PuppyListService puppyListService;

    @Value("${image.storage.server.name}")
    private String imageStorageServerName;

    @Test
    @DisplayName("강아지 목록 조회 API")
    void getPuppyList() throws Exception {
        //given
        List<PuppyListResponseDTO> puppyList = List.of(
                new PuppyListResponseDTO(1L, "puppyName1", "puppyPicture1", "simpleDescription1", "detailDescription1", imageStorageServerName),
                new PuppyListResponseDTO(2L, "puppyName2", "puppyPicture2", "simpleDescription2", "detailDescription2", imageStorageServerName),
                new PuppyListResponseDTO(3L, "puppyName3", "puppyPicture3", "simpleDescription3", "detailDescription3", imageStorageServerName),
                new PuppyListResponseDTO(4L, "puppyName4", "puppyPicture4", "simpleDescription4", "detailDescription4", imageStorageServerName),
                new PuppyListResponseDTO(5L, "puppyName5", "puppyPicture5", "simpleDescription5", "detailDescription5", imageStorageServerName),
                new PuppyListResponseDTO(6L, "puppyName6", "puppyPicture6", "simpleDescription6", "detailDescription6", imageStorageServerName)
        );

        given(puppyListService.getPuppyList(any())).willReturn(puppyList);

        //when
        mockMvc.perform(get("/api/v1/puppies?page={page}&size={size}", 0, 6)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("puppies/getList",
                        preprocessResponse(prettyPrint()),
                        findSpecificSampleRequestParams()));
    }

    private Snippet findSpecificSampleRequestParams() {
        return requestParameters(
                parameterWithName("page").optional().description("강아지 목록 오프셋"),
                parameterWithName("size").optional().description("강아지 목록 사이즈")
        );
    }

}
