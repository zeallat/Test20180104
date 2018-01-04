package com.zeallat.baseapp.util;

import com.blankj.utilcode.util.FileUtils;
import com.google.common.collect.Lists;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by HoJunLee on 2017-12-04.
 */

public class ExcelReader {

    private int indexStartRow;
    private int indexEndRow;
    private int indexStartColumn;
    private int indexEndColumn;
    private int indexSheet;

    private ExcelReader(Builder builder) {
        this.indexStartRow = builder.indexStartRow;
        this.indexEndRow = builder.indexEndRow;
        this.indexStartColumn = builder.indexStartColumn;
        this.indexEndColumn = builder.indexEndColumn;
        this.indexSheet = builder.indexSheet;
    }

    public List<List<String>> readExcel(File file) {
        String fileExtension = FileUtils.getFileExtension(file);
        try {
            if (fileExtension.toLowerCase().equals("xlsx")) {
                return readXLSXFile(file);
            } else if (fileExtension.toLowerCase().equals("xls")) {
                return readXLSFile(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private List<List<String>> readXLSFile(File file) throws IOException {
        List<List<String>> result = new ArrayList<>();
        InputStream ExcelFileToRead = new FileInputStream(file);
        HSSFWorkbook workbook = new HSSFWorkbook(ExcelFileToRead);

        HSSFSheet sheet = workbook.getSheetAt(indexSheet);
        HSSFRow row;
        HSSFCell cell;

        ArrayList<Row> rows = Lists.newArrayList(sheet.rowIterator());
        int limitOfRow = indexEndRow >= 0 ? indexEndRow : rows.size();
        for (int indexOfRow = indexStartRow; indexOfRow < limitOfRow; indexOfRow++) {
            List<String> columnContents = new ArrayList<>();
            row = (HSSFRow) rows.get(indexOfRow);
            ArrayList<Cell> cells = Lists.newArrayList(row.cellIterator());
            int limitOfCell = indexEndColumn >= 0 ? indexEndColumn : cells.size();
            for (int indexOfCell = indexStartColumn; indexOfCell < limitOfCell; indexOfCell++) {
                cell = (HSSFCell) cells.get(indexOfCell);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    columnContents.add(cell.getStringCellValue());
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    columnContents.add(String.valueOf(cell.getNumericCellValue()));
                } else {
                    columnContents.add("");
                }
            }
            result.add(columnContents);
        }
        return result;
    }

    private List<List<String>> readXLSXFile(File file) throws IOException {
        List<List<String>> result = new ArrayList<>();
        InputStream ExcelFileToRead = new FileInputStream(file);
        XSSFWorkbook workbook = new XSSFWorkbook(ExcelFileToRead);

        XSSFSheet sheet = workbook.getSheetAt(indexSheet);
        XSSFRow row;
        XSSFCell cell;

        ArrayList<Row> rows = Lists.newArrayList(sheet.rowIterator());
        int limitOfRow = indexEndRow >= 0 ? indexEndRow : rows.size();
        for (int indexOfRow = indexStartRow; indexOfRow < limitOfRow; indexOfRow++) {
            List<String> columnContents = new ArrayList<>();
            row = (XSSFRow) rows.get(indexOfRow);
            int limitOfCell = indexEndColumn >= 0 ? indexEndColumn + 1 : 0;
            for (int indexOfCell = indexStartColumn; indexOfCell < limitOfCell; indexOfCell++) {
                cell = row.getCell(indexOfCell, Row.CREATE_NULL_AS_BLANK);
                if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
                    columnContents.add(cell.getStringCellValue());
                } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                    columnContents.add(String.valueOf(cell.getNumericCellValue()));
                } else {
                    columnContents.add("");
                }
            }
            result.add(columnContents);
        }
        return result;
    }

    public static class Builder {
        private int indexStartRow = 0;
        private int indexEndRow = -1;
        private int indexStartColumn = 0;
        private int indexEndColumn = -1;
        private int indexSheet = 0;

        public Builder setIndexStartRow(int indexStartRow) {
            this.indexStartRow = indexStartRow;
            return this;
        }

        public Builder setIndexEndRow(int indexEndRow) {
            this.indexEndRow = indexEndRow;
            return this;
        }

        public Builder setIndexStartColumn(int indexStartColumn) {
            this.indexStartColumn = indexStartColumn;
            return this;
        }

        public Builder setIndexEndColumn(int indexEndColumn) {
            this.indexEndColumn = indexEndColumn;
            return this;
        }

        public Builder setIndexSheet(int indexSheet) {
            this.indexSheet = indexSheet;
            return this;
        }

        public ExcelReader build() {
            return new ExcelReader(this);
        }
    }


}
