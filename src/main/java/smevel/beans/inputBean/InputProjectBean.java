package smevel.beans.inputBean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class InputProjectBean {
    private String projectName;
    private String projectCode;
    private String projectLeadId;
}
