package work.twgj.easyexcel.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import work.twgj.easyexcel.model.MergeCellModel;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 合并单元格
 * @author weijie.zhu
 * @date 2022/8/30
 */
public class CustomMergeCellHandler implements SheetWriteHandler {

    /**
     * 合并单元格信息
     */
    private List<MergeCellModel> mergeCellList;
    /**
     * sheet页名称列表
     */
    private List<String> sheetNameList;

    public CustomMergeCellHandler(List<MergeCellModel> mergeCellList) {
        if (CollUtil.isEmpty(mergeCellList)) {
            return;
        }
        this.mergeCellList = mergeCellList.stream().filter(x ->
                StrUtil.isNotBlank(x.getSheetName()) && x.getStartRowIndex() >= 0 && x.getEndRowIndex() >= 0
                        && x.getStartColumnIndex() >= 0 && x.getEndColumnIndex() >= 0).collect(Collectors.toList());
        List<String> sheetNameList = this.mergeCellList.stream().map(x -> x.getSheetName()).distinct().collect(Collectors.toList());
        if (CollUtil.isEmpty(sheetNameList)) {
            return;
        }
        this.sheetNameList = sheetNameList;
    }


    @Override
    public void beforeSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {

    }

    /**
     * sheet页创建之后调用
     *
     * @param writeWorkbookHolder
     * @param writeSheetHolder
     */
    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        Sheet sheet = writeSheetHolder.getSheet();
        //不需要合并单元格信息，或者当前sheet页不需要合并单元格信息
        if (CollUtil.isEmpty(mergeCellList) || sheetNameList.contains(sheet.getSheetName()) == false) {
            return;
        }
        List<MergeCellModel> sheetMergeCellList = mergeCellList.stream().filter(x ->
                StrUtil.equals(x.getSheetName(), sheet.getSheetName())).collect(Collectors.toList());
        for (MergeCellModel mergeCellModel : sheetMergeCellList) {
            //开始行号
            int startRowIndex = mergeCellModel.getStartRowIndex();
            //结束行号
            int endRowIndex = mergeCellModel.getEndRowIndex();
            //开始列号
            int startColumnIndex = mergeCellModel.getStartColumnIndex();
            //结束列号
            int endColumnIndex = mergeCellModel.getEndColumnIndex();
            //行号和列号非法(<0)
            if (startColumnIndex < 0 || endColumnIndex < 0 || startRowIndex < 0 || endRowIndex < 0) {
                continue;
            }
            //合并单元格区域只有一个单元格时，不合并
            if (endRowIndex == startRowIndex && endColumnIndex == startColumnIndex) {
                continue;
            }
            //开始行号大于结束行号，或者开始列号大于结束列号
            if (startColumnIndex > endColumnIndex || startRowIndex > endRowIndex) {
                continue;
            }
            //添加合并单元格区域
            CellRangeAddress cellRangeAddress = new CellRangeAddress(startRowIndex, endRowIndex, startColumnIndex, endColumnIndex);
            sheet.addMergedRegionUnsafe(cellRangeAddress);
        }
        //删除合并单元格信息
        mergeCellList.removeAll(sheetMergeCellList);
        sheetNameList = mergeCellList.stream().map(x -> x.getSheetName()).distinct().collect(Collectors.toList());
    }
}
