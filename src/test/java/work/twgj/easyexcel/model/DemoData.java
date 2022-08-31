package work.twgj.easyexcel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author weijie.zhu
 * @date 2022/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DemoData {

    private String string;

    private Date date;

    private Double doubleData;
}
