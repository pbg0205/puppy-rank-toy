package com.cooper.backend.puppies.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Puppy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String simpleDescription;

    @Column
    @Lob
    private String detailDescription;

    @OneToOne(mappedBy = "puppy", cascade = CascadeType.ALL)
    private PuppyPicture puppyPicture;

    private Puppy(String name, String simpleDescription, String detailDescription, String pictureName) {
        this.name = name;
        this.simpleDescription = simpleDescription;
        this.detailDescription = detailDescription;
        addPuppyPicture(pictureName);
    }

    public static Puppy create(String name, String simpleDescription, String detailDescription, String pictureName) {
        return new Puppy(name, simpleDescription, detailDescription, pictureName);
    }

    private void addPuppyPicture(String pictureName) {
        PuppyPicture generatedPuppyPicture = PuppyPicture.create(pictureName);
        this.puppyPicture = generatedPuppyPicture;
        generatedPuppyPicture.setPuppy(this);
    }

}
