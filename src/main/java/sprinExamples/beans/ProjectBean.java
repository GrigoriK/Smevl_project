package sprinExamples.beans;

import lombok.Builder;
import lombok.Data;
import sprinExamples.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class ProjectBean implements BaseBean {
    private UUID projectId;
    private String projectName;
    private String projectCode;
    private UUID projectLeadId;
}
