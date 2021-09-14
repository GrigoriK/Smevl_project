package sprinExamples.beans.inputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InputEmployeeBean {
    private String name;

    private String female;

    private Short workExperience;

    private String positionId;

    private String projectId;
}
