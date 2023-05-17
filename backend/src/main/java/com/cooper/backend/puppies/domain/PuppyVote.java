package com.cooper.backend.puppies.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PuppyVote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long puppyId;

    @Column(nullable = false)
    private String ipAddress;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private VoteStatus voteStatus;

    public void cancel() {
        this.voteStatus = VoteStatus.CANCEL;
    }

}
