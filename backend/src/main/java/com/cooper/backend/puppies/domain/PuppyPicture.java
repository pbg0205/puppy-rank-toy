package com.cooper.backend.puppies.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PuppyPicture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String pictureName;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "puppy_id")
    private Puppy puppy;

    private PuppyPicture(String pictureName) {
        this.pictureName = pictureName;
    }

    public static PuppyPicture create(String pictureName) {
        return new PuppyPicture(pictureName);
    }

    public void setPuppy(Puppy puppy) {
        this.puppy = puppy;
    }

}
