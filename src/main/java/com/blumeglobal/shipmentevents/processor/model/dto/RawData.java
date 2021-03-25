package com.blumeglobal.shipmentevents.processor.model.dto;

import com.blumeglobal.shipmentevents.processor.enums.StateEnum;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Version;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "RawData")
@Table(name = "RawData", indexes = @Index(columnList = "STATE"))
public class RawData implements Serializable {

    @Id
    @Column(name = "Id", unique = true, updatable = false, nullable = false, insertable = true)
    private Long id;

    @Lob
    @Column(name = "DATA")
    private String data;

    @CreatedDate
    @Column(name = "CREATED_DATE")
    private Long createdDate;

    @Column(name = "CREATED_DATE_STR")
    private String createdDateInStr;

    @Column(name = "PROCESS_START_DATE")
    private Long processStartDate;

    @Column(name = "PROCESS_START_DATE_STR")
    private String processStartDateInStr;

    @Column(name = "PROCESSED_DATE")
    private Long processedDate;

    @Column(name = "PROCESSED_DATE_STR")
    private String processedDateInStr;

    @Column(name = "STATE")
    private StateEnum state;

    @Version
    @Column(name = "VERSION")
    private Integer version;


}
