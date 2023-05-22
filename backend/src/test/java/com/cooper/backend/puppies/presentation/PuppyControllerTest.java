package com.cooper.backend.puppies.presentation;

import com.cooper.backend.puppies.business.PuppyDetailService;
import com.cooper.backend.puppies.business.PuppyListService;
import com.cooper.backend.puppies.dto.PuppyDetailHttpResponse;
import com.cooper.backend.puppies.dto.PuppyListHttpResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessRequest;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.preprocessResponse;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint;
import static org.springframework.restdocs.payload.PayloadDocumentation.beneathPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
@AutoConfigureRestDocs
class PuppyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PuppyListService puppyListService;

    @MockBean
    private PuppyDetailService puppyDetailServiceService;

    @Value("${image.storage.server.name}")
    private String imageStorageServerName;

    @Test
    @DisplayName("강아지 목록 조회 API")
    void getPuppyList() throws Exception {
        //given
        List<PuppyListHttpResponse> puppyList = List.of(
                new PuppyListHttpResponse(1L, "puppyName1", "puppyPicture1", "simpleDescription1", imageStorageServerName),
                new PuppyListHttpResponse(2L, "puppyName2", "puppyPicture2", "simpleDescription2", imageStorageServerName),
                new PuppyListHttpResponse(3L, "puppyName3", "puppyPicture3", "simpleDescription3", imageStorageServerName),
                new PuppyListHttpResponse(4L, "puppyName4", "puppyPicture4", "simpleDescription4", imageStorageServerName),
                new PuppyListHttpResponse(5L, "puppyName5", "puppyPicture5", "simpleDescription5", imageStorageServerName),
                new PuppyListHttpResponse(6L, "puppyName6", "puppyPicture6", "simpleDescription6", imageStorageServerName)
        );

        given(puppyListService.getPuppyList(any())).willReturn(puppyList);

        //when
        mockMvc.perform(get("/api/v1/puppies?page={page}&size={size}", 0, 6)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("puppies/getList",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        beneathPath("data").withSubsectionId("data"),
                                        fieldWithPath("puppyId").type(JsonFieldType.NUMBER).description("강아지 아이디"),
                                        fieldWithPath("puppyName").type(JsonFieldType.STRING).description("강아지 이름"),
                                        fieldWithPath("puppyPictureUrl").type(JsonFieldType.STRING).description("강아지 사진 URL"),
                                        fieldWithPath("simpleDescription").type(JsonFieldType.STRING).description("강아지 간단 설명")
                                ),
                                requestParameters(
                                        parameterWithName("page").optional().description("강아지 목록 오프셋"),
                                        parameterWithName("size").optional().description("강아지 목록 사이즈"))
                        )
                );
    }

    @Test
    @DisplayName("강아지의 세부 정보를 조회한다")
    void getPuppyDetail() throws Exception {
        //given
        PuppyDetailHttpResponse puppyDetailHttpResponse = new PuppyDetailHttpResponse(1L, "puppyName1", "puppyPicture1", "simpleDescription1", "detailDescription1", imageStorageServerName);

        given(puppyDetailServiceService.getPuppyDetail(any())).willReturn(puppyDetailHttpResponse);

        //when
        mockMvc.perform(get("/api/v1/puppies/{puppyId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("puppies/getPuppyDetail",
                                preprocessRequest(prettyPrint()),
                                preprocessResponse(prettyPrint()),
                                responseFields(
                                        beneathPath("data").withSubsectionId("data"),
                                        fieldWithPath("puppyId").type(JsonFieldType.NUMBER).description("강아지 아이디"),
                                        fieldWithPath("name").type(JsonFieldType.STRING).description("강아지 이름"),
                                        fieldWithPath("puppyPictureUrl").type(JsonFieldType.STRING).description("강아지 사진 URL"),
                                        fieldWithPath("simpleDescription").type(JsonFieldType.STRING).description("강아지 간단 설명"),
                                        fieldWithPath("detailDescription").type(JsonFieldType.STRING).description("강아지 세부 설명")
                                ),
                                pathParameters(parameterWithName("puppyId").description("강아지 아이디"))
                        )
                );

        //then

    }

}
