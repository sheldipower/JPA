package dto;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {
    private int id;
    private String report;
    private String path;
}