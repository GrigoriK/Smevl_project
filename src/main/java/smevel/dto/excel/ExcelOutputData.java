package smevel.dto.excel;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ExcelOutputData {
    private String excelFileName;
    private byte[] excelContent;
}
