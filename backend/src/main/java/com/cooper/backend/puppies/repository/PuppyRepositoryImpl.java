package com.cooper.backend.puppies.repository;

import com.cooper.backend.puppies.dto.PuppyDetailHttpResponse;
import com.cooper.backend.puppies.dto.PuppyListHttpResponse;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.cooper.backend.puppies.domain.QPuppy.puppy;
import static com.cooper.backend.puppies.domain.QPuppyPicture.puppyPicture;

@Repository
@RequiredArgsConstructor
public class PuppyRepositoryImpl implements PuppyRepository {

    @Value("${image.storage.server.name}")
    private String imageStorageServerName;

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PuppyListHttpResponse> findPuppyListDesc(final Pageable pageable) {
        return jpaQueryFactory.select(Projections.constructor(PuppyListHttpResponse.class,
                        puppy.id,
                        puppy.name,
                        puppyPicture.pictureName,
                        puppy.simpleDescription,
                        Expressions.constant(imageStorageServerName)))
                .from(puppy)
                .innerJoin(puppyPicture).on(puppy.id.eq(puppyPicture.id))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    @Override
    public Optional<PuppyDetailHttpResponse> findPuppyDetailByPuppyId(Long puppyId) {
        PuppyDetailHttpResponse puppyDetailHttpResponse = jpaQueryFactory.select(Projections.constructor(PuppyDetailHttpResponse.class,
                        puppy.id,
                        puppy.name,
                        puppyPicture.pictureName,
                        puppy.simpleDescription,
                        puppy.detailDescription,
                        Expressions.constant(imageStorageServerName)))
                .from(puppy)
                .innerJoin(puppyPicture).on(puppy.id.eq(puppyPicture.id))
                .where(puppy.id.eq(puppyId))
                .fetchFirst();

        return Optional.ofNullable(puppyDetailHttpResponse);
    }

}
