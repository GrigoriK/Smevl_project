package smevel.beans.outputBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OutputProjectBean {
    private String projectId;
    private String projectName;
    private String projectCode;
    private String projectLeadId;
}
