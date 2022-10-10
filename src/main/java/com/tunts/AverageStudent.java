package com.tunts;

import lombok.Cleanup;
import org.apache.commons.collections4.IteratorUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AverageStudent {

    private final int TOTAL_CLASSES = 60;

    public List<Student> validAvarage() throws IOException {
        List<Student> students = new ArrayList<>();

        @Cleanup
        FileInputStream file = new FileInputStream("src/main/resources/Engenharia de Software - Desafio Paulo César de Souza Filho.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        List<Row> rows = (List<Row>) toList(sheet.iterator());

        rows.forEach(row -> {
            if (row.getRowNum() >= 3) {
                List<Cell> cells = (List<Cell>) toList(row.cellIterator());
                Student student = Student.builder()
                        .matriculation((int) cells.get(0).getNumericCellValue())
                        .name(cells.get(1).getStringCellValue())
                        .absences((int) cells.get(2).getNumericCellValue())
                        .p1((int) cells.get(3).getNumericCellValue())
                        .p2((int) cells.get(4).getNumericCellValue())
                        .p3((int) cells.get(5).getNumericCellValue())
                        .build();

                final int avarage = (student.getP1() + student.getP2() + student.getP3()) / 3;

                if (student.getAbsences() < (TOTAL_CLASSES * 25) / 100) {
                    student.setSituation("Reprovado por Falta");
                    student.setNaf(0);
                } else if (avarage < 50) {
                    student.setSituation("Reprovado por Nota");
                    student.setNaf(0);
                } else if (avarage >= 50 && avarage < 70) {
                    student.setSituation("Exame Final");
                } else {
                    student.setSituation("Aprovado");
                    student.setNaf(0);
                }
                students.add(student);
            }
        });
        return students;
    }

    private List<?> toList(Iterator<?> iteretor) {
        return IteratorUtils.toList(iteretor);
    }

    public void print(List<Student> students) {
        students.forEach(System.out::println);
    }
}
