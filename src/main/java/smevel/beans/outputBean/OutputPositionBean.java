package smevel.beans.outputBean;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OutputPositionBean {
    private String positionId;
    private String positionName;

}
