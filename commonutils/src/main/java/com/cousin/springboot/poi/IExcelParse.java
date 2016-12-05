package com.cousin.springboot.poi;

/**
 * excel文件解析接口
 * Created by cousin
 * CreatedTime 2016/12/521:17
 */
public interface IExcelParse {

    public void loadExcel(String path) throws Exception;

    public String getSheetName(int sheetNo);

    public int getSheetCount() throws Exception;

    public int getRowCount(int sheetNo);

    public int getRealRowCount(int sheetNo);

    public String readExcelByRowAndCell(int sheetNo, int rowNo, int cellNo)
            throws Exception;

    public String[] readExcelByRow(int sheetNo, int rowNo) throws Exception;

    public String[] readExcelByCell(int sheetNo, int cellNo) throws Exception;

    public void close();
}
