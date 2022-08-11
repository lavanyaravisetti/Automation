package com.google.automation.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
    private Cell openCell;
    private Row openRow;
    private Sheet openSheet;
    private String data;
    private Workbook openWorkbook;
    InputStream inp;
    File f;
    /**
     * Creates an Excel reader object and open the said workbook at the specified path.
     * @param excelPath - The path of the excel file
     */
    public ExcelReader(String excelPath) throws Exception {
        this(new File(excelPath));
    }

    /**
     * Creates an Excel reader object and open the said workbook at the specified path.
     * @param excelFile - File object of the excel file
     */
    public ExcelReader(File excelFile) throws Exception {
        f = excelFile;
        inp = new FileInputStream(excelFile);
        if (isXlsx(excelFile))
            openWorkbook = new XSSFWorkbook(inp);
        else
            openWorkbook = new HSSFWorkbook(inp);
    }

    /**
     * Returns true or false depending upon whether file name ends with xlsx
     * * @param fl
     * @return
     */
    private boolean isXlsx(File fl) {
        String fileName = fl.getName();
        if (fileName.endsWith("xlsx"))
            return true;
        return false;
    }

    /**
     * Open sheet with the specified name
     * @param sheetName
     */
    public void openSheet(String sheetName) {
        openSheet = openWorkbook.getSheet(sheetName);
    }

    /**
     * Gets current sheet name
     * @param sheetNo - Index of sheet
     * @return - Sheet name
     */
    public String getCurrentSheetName(int sheetNo) {
        openSheet = openWorkbook.getSheetAt(sheetNo);
        return openSheet.getSheetName();
    }

    /**
     * Gets number of sheets in the given workbook
     *
     * @return - number of sheets in the workbook
     */
    public int getNoOfSheets() {
        return openWorkbook.getNumberOfSheets();
    }

    /**
     * Gets the data from the currently opened sheet present on the specified row and column
     * @param column
     * @param row
     * @return - Respective data in string format. "" if the said row -column does not exist
     */
    public String getData(int column, int row) {
        try {

            openRow = openSheet.getRow(row);
            openCell = openRow.getCell(column);
            int cellType = openCell.getCellType();
            switch (cellType) {
                case 0:
                    if (DateUtil.isCellDateFormatted(openCell)) {
                        Date dt = openCell.getDateCellValue();
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "dd MM yyyy HH:mm:ss");
                        data = sdf.format(dt);
                    } else
                        data = Long.toString(Math.round(openCell
                                .getNumericCellValue()));
                    break;
                case 1:
                    data = openCell.getRichStringCellValue().getString();
                    break;
                case 2:
                    data = openCell.getRichStringCellValue().getString();
                    break;
                case 3:
                    data = openCell.getRichStringCellValue().getString();
                    break;
                case 4:
                    data = Boolean.toString(openCell.getBooleanCellValue());
                    break;
                case 5:
                    data = Byte.toString(openCell.getErrorCellValue());
                    break;
                default:
                    data = openCell.getRichStringCellValue().getString();
            }

            if (data == null) {
                data = "";
            }
            return data;
        } catch (Exception e) {
            if (openRow == null || openCell == null || data == null) {
                data = "";
                return data;
            } else {
                System.out.println(e);
                return "";
            }
        }

    }

    /**
     * Number of rows in the said sheet.
     * @return
     */
    public int getNoOfRows() {
        return (openSheet.getLastRowNum() + 1);
    }

    /**
     * Number of columns in the first row of the sheet
     * @return
     */
    public int getNoOfColumn() {
        Row rw = openSheet.getRow(0);
        if (rw == null)
            return 0;
        return rw.getLastCellNum();
    }

    /**
     * Number of columns of a particular row number that is specified
     * @param rowNumber
     * @return
     */
    public int getNoOfColumn(int rowNumber) {
        Row rw = openSheet.getRow(rowNumber);
        if (rw == null)
            return 0;
        return rw.getLastCellNum();
    }

    /**
     * Gets the current open sheet name
     * @return
     */
    public String getSheetName() {
        return openSheet.getSheetName();
    }

 
    /**
     * Updates current sheet with given data.
     * @param col
     * @param row
     * @param data
     */
    public void updateData(int col, int row, String data) {
        try {
            openRow = openSheet.getRow(row);
            openCell = openRow.createCell(col);
            openCell.setCellValue(data);
            inp.close();
            FileOutputStream out = new FileOutputStream(f);
            openWorkbook.write(out);
            out.close();
        } catch (Exception ex) {
        }

    }
}
