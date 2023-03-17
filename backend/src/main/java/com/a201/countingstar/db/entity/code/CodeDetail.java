package com.a201.countingstar.db.entity.code;

import javax.persistence.*;

@Entity
@Table(name="code_detail")
public class CodeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // sql에 시키기
    @Column(name="code_detail_id")
    private int codeDetailId;
    private String name;
    @Column(length = 2)
    private String code;


    @ManyToOne
    @JoinColumn(name = "code_master_id")
    private CodeMaster master;
}
