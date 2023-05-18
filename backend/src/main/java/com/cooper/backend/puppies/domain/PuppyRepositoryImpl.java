package com.cooper.backend.puppies.domain;

import com.cooper.backend.puppies.dto.PuppyListResponseDTO;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.cooper.backend.puppies.domain.QPuppy.puppy;
import static com.cooper.backend.puppies.domain.QPuppyPicture.puppyPicture;

@Repository
@RequiredArgsConstructor
public class PuppyRepositoryImpl implements PuppyRepositoryCustom {

    @Value("${image.storage.server.name}")
    private String imageStorageServerName;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PuppyListResponseDTO> findPuppyListDesc(final Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(PuppyListResponseDTO.class,
                        puppy.id,
                        puppy.name,
                        puppyPicture.pictureName,
                        puppy.simpleDescription,
                        puppy.detailDescription,
                        Expressions.constant(imageStorageServerName)))
                .from(puppy)
                .innerJoin(puppyPicture).on(puppy.id.eq(puppyPicture.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

}
