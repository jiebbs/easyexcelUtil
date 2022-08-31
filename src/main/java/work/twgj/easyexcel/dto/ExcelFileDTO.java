package work.twgj.easyexcel.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author weijie.zhu
 * @date 2022/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExcelFileDTO {

    private String name;

    private String content;
}
