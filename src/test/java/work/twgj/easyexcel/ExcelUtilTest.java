package work.twgj.easyexcel;

import com.alibaba.excel.util.ListUtils;
import org.junit.Test;
import work.twgj.easyexcel.dto.ExcelFileDTO;
import work.twgj.easyexcel.model.DemoData;
import work.twgj.easyexcel.util.ExcelUtil;

import java.util.Date;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2022/8/31
 */
public class ExcelUtilTest {

    @Test
    public void export(){
        try {
            ExcelFileDTO fileDTO = ExcelUtil.export("测试导出文件","测试导出工作簿",DemoData.class,data(),null);
            System.out.println(fileDTO);
        }catch (Exception e){

        }
    }

    private static List<DemoData> data() {
        List<DemoData> list = ListUtils.newArrayList();
        for (int i = 0; i < 10; i++) {
            DemoData data = new DemoData();
            data.setString("字符串" + i);
            data.setDate(new Date());
            data.setDoubleData(0.56);
            list.add(data);
        }
        return list;
    }
}
