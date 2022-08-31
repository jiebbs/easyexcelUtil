package work.twgj.easyexcel.util;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.handler.WriteHandler;
import com.alibaba.excel.write.metadata.WriteSheet;
import org.apache.commons.collections4.CollectionUtils;
import work.twgj.easyexcel.dto.ExcelFileDTO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.List;

/**
 * @author weijie.zhu
 * @date 2022/8/31
 */
public class ExcelUtil {

    private ExcelUtil(){}

    /**
     * 导出二进制文件
     * @param exportFileName 导出文件名称
     * @param sheetName 工作簿名称
     * @param modelClass 导出模型class
     * @param data 导出数据
     * @param writeHandlerList 处理器列表
     * @param <T>
     * @return
     * @throws Exception
     */
    public static <T> ExcelFileDTO export(String exportFileName,String sheetName,Class<T> modelClass,List<T> data, List<WriteHandler> writeHandlerList) throws Exception{
        InputStream is = null;
        ByteArrayOutputStream out = null;
        try{
            out = new ByteArrayOutputStream();
            ExcelWriterBuilder excelWriterBuilder = EasyExcel.write(out,modelClass);
            if (CollectionUtils.isNotEmpty(writeHandlerList)){
                for(WriteHandler writeHandler:writeHandlerList){
                    excelWriterBuilder.registerWriteHandler(writeHandler);
                }
            }
            ExcelWriter excelWriter = excelWriterBuilder.build();
            WriteSheet writeSheet = EasyExcel.writerSheet(sheetName).build();
            excelWriter.write(data,writeSheet);
            excelWriter.finish();

            is = new ByteArrayInputStream(out.toByteArray());
            byte[] bytes = new byte[is.available()];
            int len = is.read(bytes);
            if (len > 0){
                ExcelFileDTO fileDTO = new ExcelFileDTO();
                fileDTO.setName(exportFileName);
                fileDTO.setContent(new String(Base64.getEncoder().encode(bytes), Charset.defaultCharset()));
                return fileDTO;
            }else{
                return null;
            }
        }catch (Exception e){
           throw e;
        }finally {
            if (out != null) {out.close();}
            if (is != null) {is.close();}
        }
    }
}
