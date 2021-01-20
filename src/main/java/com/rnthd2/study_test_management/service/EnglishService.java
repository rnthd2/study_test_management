package com.rnthd2.study_test_management.service;

import com.rnthd2.study_test_management.domain.EnglishNote;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.springframework.core.io.Resource;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class EnglishService {
    //todo class로 따로 관리
    final String ENG_FILE_PATH_V1 = "file/english.xlsx";

    private InputStream is = InputStream.nullInputStream();
    private void setResourceAsStream(String path) {
        is = this.getClass().getClassLoader().getResourceAsStream(path);
    }

    /**
     * 영어 노트 가져오기
     *
     * /src/main/resources/file/english.xlsx
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
            while(rowItr.hasNext() ){
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
        } catch (IllegalStateException e){
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
            }
        }
        return null;
    }

    private Workbook getFile(String path) throws IOException, InvalidFormatException {
        setResourceAsStream(path);
        Workbook workbook = WorkbookFactory.create(is);
        return workbook;
    }

    private EnglishNote setEnglishNote(Row cells) {
        cells.forEach(cell -> {
            if(cell.getCellType() != 1){
                cell.setCellType(Cell.CELL_TYPE_STRING);
            }
        });

        EnglishNote englishNote = EnglishNote.ByReadEnglishNoteBuilder()
                .id(Long.valueOf(cells.getCell(0).getStringCellValue()))
                .studyTime(LocalDate.parse(cells.getCell(1).getStringCellValue(),  DateTimeFormatter.ISO_LOCAL_DATE))
                .original(cells.getCell(2).getStringCellValue())
                .translate(cells.getCell(3).getStringCellValue())
                .explanation(cells.getCell(4).getStringCellValue())
                .build();
        return englishNote;
    }

    public ResponseEntity<Resource> downloadFile(String fileName, Resource resource) {
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .cacheControl(CacheControl.noCache())
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName + "")
                .body(resource);
    }
}
