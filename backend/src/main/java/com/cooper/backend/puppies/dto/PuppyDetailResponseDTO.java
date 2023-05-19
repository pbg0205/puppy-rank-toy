package com.cooper.backend.puppies.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PuppyDetailResponseDTO {

    private static final String SLASH = "/";

    private final Long puppyId;
    private final String name;
    private final String puppyPictureUrl;
    private final String simpleDescription;
    private final String detailDescription;

    @QueryProjection

    public PuppyDetailResponseDTO(Long puppyId,
                                  String name,
                                  String puppyPictureName,
                                  String simpleDescription,
                                  String detailDescription,
                                  String imageStorageServerName
                                  ) {
        this.puppyId = puppyId;
        this.name = name;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        this.puppyPictureUrl = getPuppyPictureUrl(puppyId, puppyPictureName, imageStorageServerName);
    }

    private String getPuppyPictureUrl(Long puppyId, String puppyPictureName, String imageStorageServerName) {
        return new StringBuilder(imageStorageServerName)
                .append(SLASH)
                .append(puppyId)
                .append(SLASH)
                .append(puppyPictureName)
                .toString();
    }

}
