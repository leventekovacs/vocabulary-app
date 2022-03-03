package com.vocabularyapp.model;

import lombok.*;
import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Expression {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String en;

    @Column(nullable = false)
    private String hu;

    @Column(nullable = false)
    private Integer rightAnswerCounter;

    @Column(nullable = false)
    private Integer wrongAnswerCounter;

    @Column(nullable = false)
    private Boolean learned;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "app_user_id")
    private AppUser appUser;

    public Expression(String en, String hu) {
        this.en = en;
        this.hu = hu;
        rightAnswerCounter = 0;
        wrongAnswerCounter = 0;
        learned = false;
    }
}
