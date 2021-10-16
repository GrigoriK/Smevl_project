package smevel.beans;

import lombok.Builder;
import lombok.Data;
import smevel.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class ProjectBean implements BaseBean {
    private UUID projectId;
    private String projectName;
    private String projectCode;
    private UUID projectLeadId;
}
