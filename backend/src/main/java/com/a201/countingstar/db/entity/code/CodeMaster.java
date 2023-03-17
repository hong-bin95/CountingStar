package com.a201.countingstar.db.entity.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="code_master")
@Getter
@Setter
@NoArgsConstructor
public class CodeMaster {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="code_master_id")
    private int codeMasterId;
    private String name;
//    @Column(columnDefinition = "char", length = 2)
    @Column(length = 2)
    private String code;
}
