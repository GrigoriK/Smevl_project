package sprinExamples.beans;

import lombok.Builder;
import lombok.Data;
import sprinExamples.beans.baseBeansStructure.BaseBean;

import java.util.UUID;

@Data
@Builder
public class PositionBean implements BaseBean {

    private UUID positionId;

    private String positionName;

}
