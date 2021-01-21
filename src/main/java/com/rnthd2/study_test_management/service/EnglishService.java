package com.rnthd2.study_test_management.service;

import com.rnthd2.study_test_management.domain.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

@Service
public class EnglishService {
    //todo class로 따로 관리
    final String ENG_FILE_PATH_V1 = "file/english.xlsx";

    private InputStream is = InputStream.nullInputStream();

    private void setResourceAsStream(String path) {
        is = this.getClass().getClassLoader().getResourceAsStream(path);
    }

    public NoteBookResponse noteBookResponse(List<? extends Note> list){
        return NoteBookResponse.ByReadEnglishNoteResponseBuilder()
                .id(1L)
                .list(list)
                .build();
    }

    /**
     * 영어 노트 가져오기
     * <p>
     * /src/main/resources/file/english.xlsx
     *
     * @return
     */
    public List<EnglishNote> findEngNote() {
        try {
            Workbook workbook = getFile(ENG_FILE_PATH_V1);
            //1번 시트 가져오기
            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowItr = sheet.iterator();
            //2번째 행부터 읽어오기
            rowItr.next();
            List<EnglishNote> list = new ArrayList<>();
            while (rowItr.hasNext()) {
                Row cells = rowItr.next();
                EnglishNote englishNote = setEnglishNote(cells);
                list.add(englishNote);
            }
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    private EnglishNote setEnglishNote(Row cells) {
        cells.forEach(cell -> {
            if (cell.getCellType() != 1) {
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
        });

        EnglishNote englishNote = EnglishNote.ByReadEnglishNoteBuilder()
                .id(Long.valueOf(cells.getCell(0).getStringCellValue()))
                .studyTime(LocalDate.parse(cells.getCell(1).getStringCellValue(), DateTimeFormatter.ISO_LOCAL_DATE))
                .original(cells.getCell(2).getStringCellValue())
                .translate(cells.getCell(3).getStringCellValue())
                .explanation(cells.getCell(4).getStringCellValue())
                .build();
        return englishNote;
    }

    public TestPaperResponse findTestPaperBlankOriginal() {
        List<Test> tests = new ArrayList<>();
        List<EnglishNote> note = findEngNote();
        Collections.shuffle(note);
        note.forEach(row -> {
            Test test = Test.ByWriteTestBuilder()
                    .id(row.getId())
                    .question(row.getTranslate()).build();
            tests.add(test);
        });

        //todo id 저장용 db 필요...
        TestPaper testPaper = TestPaper.ByWriteTestPaperBuilder()
                .id(1L)
                .testTime(LocalDateTime.now())
                .tests(tests)
                .build();

        TestPaperResponse testPaperResponse = TestPaperResponse.ByTestPaperResponseBuilder()
                .id(1L)
                .tests(tests)
                .build();

        return testPaperResponse;
    }

    private Workbook getFile(String path) throws IOException, InvalidFormatException {
        setResourceAsStream(path);
        Workbook workbook = WorkbookFactory.create(is);
        return workbook;
    }


    public ResponseEntity<Resource> downloadFile(String fileName, Resource resource) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "")
                .body(resource);
    }

    public TestPaper test() {

        Workbook xlsxWb = new XSSFWorkbook(); // Excel 2007 이상

        // *** Sheet-------------------------------------------------
        // Sheet 생성
        Sheet sheet1 = xlsxWb.createSheet("firstSheet");

        // 컬럼 너비 설정
        sheet1.setColumnWidth(0, 10000);
        sheet1.setColumnWidth(9, 10000);
        // ----------------------------------------------------------

        // *** Style--------------------------------------------------
        // Cell 스타일 생성
        CellStyle cellStyle = xlsxWb.createCellStyle();

        // 줄 바꿈
        cellStyle.setWrapText(true);

        // Cell 색깔, 무늬 채우기
        cellStyle.setFillForegroundColor(HSSFColor.LIME.index);
        cellStyle.setFillPattern(CellStyle.BIG_SPOTS);

        Row row = null;
        Cell cell = null;
        //----------------------------------------------------------

        // 첫 번째 줄
        row = sheet1.createRow(0);

        // 첫 번째 줄에 Cell 설정하기-------------
        cell = row.createCell(0);
        cell.setCellValue("1-1");
        cell.setCellStyle(cellStyle); // 셀 스타일 적용

        cell = row.createCell(1);
        cell.setCellValue("1-2");

        cell = row.createCell(2);
        cell.setCellValue("1-3 abccdefghijklmnopqrstuvwxyz");
        cell.setCellStyle(cellStyle); // 셀 스타일 적용
        //---------------------------------

        // 두 번째 줄
        row = sheet1.createRow(1);

        // 두 번째 줄에 Cell 설정하기-------------
        cell = row.createCell(0);
        cell.setCellValue("2-1");

        cell = row.createCell(1);
        cell.setCellValue("2-2");

        cell = row.createCell(2);
        cell.setCellValue("2-3");
        cell.setCellStyle(cellStyle); // 셀 스타일 적용
        //---------------------------------

        // excel 파일 저장
        try {
            File xlsFile = new File("D:/testExcel.xls");
            FileOutputStream fileOut = new FileOutputStream(xlsFile);
            xlsxWb.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
