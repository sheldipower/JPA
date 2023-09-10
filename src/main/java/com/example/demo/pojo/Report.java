package com.example.demo.pojo;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;

import javax.persistence.*;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Entity
@Table(name = "report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reportId;
    @Lob
    @Column(name = "data", columnDefinition = "text")
    private String data;

    @CreationTimestamp(source = SourceType.DB)
    @Column(updatable = false,nullable = false)
    private Instant createOn;



    public Integer getReportId() {
        return reportId;
    }

    public void setReportId(Integer reportId) {
        this.reportId = reportId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Instant getCreateOn() {
        return createOn;
    }
    public void setCreateOn(Instant createOn) {
        this.createOn = createOn;
    }

    @Override
    public String toString() {
        return "Report{" +
                "reportId=" + reportId +
                ", data='" + data + '\'' +
                ", createOn=" + createOn +
                '}';
    }
}
