package com.cooper.backend.puppies.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class PuppyListResponseDTO {

    private static final String SLASH = "/";

    private final Long puppyId;
    private final String puppyName;
    private final String puppyPictureUrl;
    private final String simpleDescription;

    @QueryProjection
    public PuppyListResponseDTO(Long puppyId,
                                String puppyName,
                                String puppyPictureName,
                                String simpleDescription,
                                String imageStorageServerName) {
        this.puppyId = puppyId;
        this.puppyName = puppyName;
        this.puppyPictureUrl = getPuppyPictureUrl(puppyId, puppyPictureName, imageStorageServerName);
        this.simpleDescription = simpleDescription;
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
