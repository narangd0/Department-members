package com.nhnacademy.springboot.test.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Table(name = "work")
@Entity
public class Work {
    @EmbeddedId
    private Pk pk;

    @MapsId("departmentId")
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @MapsId("memberId")
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @NoArgsConstructor
    @EqualsAndHashCode
    @Getter
    @Setter
    @Embeddable
    @AllArgsConstructor
    public static class Pk implements Serializable {
        @Column(name = "department_id")
        private String departmentId;

        @Column(name = "member_id")
        private String memberId;
    }
}
