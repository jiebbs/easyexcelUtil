package work.twgj.easyexcel.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author weijie.zhu
 * @date 2022/8/30
 */
@Data
@AllArgsConstructor
public class MergeCellModel {

    /**
     * sheet名称
     */
    private String sheetName;
    /**
     * 开始行号
     */
    private int startRowIndex;
    /**
     * 开始列号
     */
    private int startColumnIndex;
    /**
     * 结束行号
     */
    private int endRowIndex;
    /**
     * 结束列号
     */
    private int endColumnIndex;

    private MergeCellModel(){}

    /**
     * 生成合并列单元格信息
     *
     * @param sheetName        sheet页名称
     * @param rowIndex         行号
     * @param startColumnIndex 开始列号
     * @param endColumnIndex   结束列号
     * @return
     */
    public static MergeCellModel createMergeColumnCellModel(String sheetName, int rowIndex, int startColumnIndex
            , int endColumnIndex) {
        return createMergeCellModel(sheetName, rowIndex, rowIndex, startColumnIndex, endColumnIndex);
    }

    /**
     * 生成合并单元格信息
     *
     * @param sheetName     sheet页名称
     * @param startRowIndex 开始行号
     * @param endRowIndex   结束行号
     * @param columnIndex   列号
     * @return
     */
    public static MergeCellModel createMergeRowCellModel(String sheetName, int startRowIndex, int endRowIndex, int columnIndex) {
        return createMergeCellModel(sheetName, startRowIndex, endRowIndex, columnIndex, columnIndex);
    }

    /**
     * 生成合并单元格信息
     *
     * @param sheetName        sheet页名称
     * @param startRowIndex    开始行号
     * @param endRowIndex      结束行号
     * @param startColumnIndex 开始列号
     * @param endColumnIndex   结束列号
     * @return
     */
    public static MergeCellModel createMergeCellModel(String sheetName, int startRowIndex, int endRowIndex, int startColumnIndex
            , int endColumnIndex) {
        MergeCellModel mergeCellModel = new MergeCellModel();
        //sheet页名称
        mergeCellModel.setSheetName(sheetName);
        //开始行号
        mergeCellModel.setStartRowIndex(startRowIndex);
        //结束行号
        mergeCellModel.setEndRowIndex(endRowIndex);
        //开始列号
        mergeCellModel.setStartColumnIndex(startColumnIndex);
        //结束列号
        mergeCellModel.setEndColumnIndex(endColumnIndex);
        return mergeCellModel;
    }
}
