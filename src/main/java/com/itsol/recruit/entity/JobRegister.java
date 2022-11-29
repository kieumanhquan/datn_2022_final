package com.itsol.recruit.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "job_register")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JobRegister{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GEN_JOR_ID")
    @SequenceGenerator(name = "GEN_JOR_ID", sequenceName = "JOBREG_SEQ", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "job_id")
    private Job job;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "date_register")
    private Date dateRegister;

    @Column(name = "date_interview")
    private Date dateInterview;

    @Column(name = "method_interview")
    private String methodInterview;

    @Column(name = "address_interview")
    private String addressInterview;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusJobRegister statusJobRegister;

    @Column(name = "cv_file")
    private String cv;

    @Column(name = "media_type")
    private String mediaType;

    @Column(name = "reason")
    private String reason;

    @Column(name = "is_delete")
    @Type(type = "org.hibernate.type.NumericBooleanType")
    private Boolean isDelete;
}

