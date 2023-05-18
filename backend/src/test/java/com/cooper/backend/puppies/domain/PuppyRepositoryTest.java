package com.cooper.backend.puppies.domain;


import com.cooper.backend.common.config.QueryDslConfig;
import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.PageRequest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Import(QueryDslConfig.class)
@DataJpaTest(
        includeFilters = @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE, classes = PuppyRepository.class
        )
)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
class PuppyRepositoryTest {

    @Autowired
    private PuppyRepository puppyRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Value("${image.storage.server.name}")
    private String imageStorageServerName;

    @Test
    @DisplayName("강아지 목록을 조회한다")
    void findPuppyListDesc() {
        //given
        Puppy puppy1 = Puppy.create("강아지 이름1", "간단한 설명1", "구체적인 설명1", "puppy01.jpg");
        Puppy puppy2 = Puppy.create("강아지 이름2", "간단한 설명2", "구체적인 설명2", "puppy02.jpg");
        Puppy puppy3 = Puppy.create("강아지 이름3", "간단한 설명3", "구체적인 설명3", "puppy03.jpg");
        Puppy puppy4 = Puppy.create("강아지 이름4", "간단한 설명4", "구체적인 설명4", "puppy04.jpg");
        Puppy puppy5 = Puppy.create("강아지 이름5", "간단한 설명5", "구체적인 설명5", "puppy05.jpg");
        Puppy puppy6 = Puppy.create("강아지 이름6", "간단한 설명6", "구체적인 설명6", "puppy06.jpg");
        Puppy puppy7 = Puppy.create("강아지 이름7", "간단한 설명7", "구체적인 설명7", "puppy07.jpg");

        testEntityManager.persist(puppy1);
        testEntityManager.persist(puppy2);
        testEntityManager.persist(puppy3);
        testEntityManager.persist(puppy4);
        testEntityManager.persist(puppy5);
        testEntityManager.persist(puppy6);
        testEntityManager.persist(puppy7);

        //when
        List<PuppyListResponseDTO> puppies = puppyRepository.findPuppyListDesc(PageRequest.of(0, 6));

        //then
        Assertions.assertAll(
                () -> assertThat(puppies).hasSize(6),
                () -> assertThat(puppies.get(0).getPuppyPictureUrl()).startsWith(imageStorageServerName)
        );
    }

}
