package premier20180102;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellRangeAddressList;

public class ExcelReader {
  private File file;
  private Workbook workbook;
  private Sheet worksheet;
  
  public ExcelReader(String excelFilePath) throws Exception {
    try {
      this.file = FileUtil.getResourceFile(excelFilePath);
      this.workbook = WorkbookFactory.create(file);
    } catch (EncryptedDocumentException | InvalidFormatException
        | IOException e) {
      throw new Exception("Error: reading Excel file");
    } catch(Exception e2) {
      throw new Exception("Error: excel file is not found");
    }
  }
  
  public void findSheet(String sheetName) {
    this.worksheet = workbook.getSheet(sheetName);
  }
  
  public List<Student> getStudentList(String rangeValue) {
    CellRangeAddressList list = new CellRangeAddressList();
    list.addCellRangeAddress(CellRangeAddress.valueOf(rangeValue));
      
    CellRangeAddress range = list.getCellRangeAddress(0);
    
    List<Student> results = new ArrayList<>();
    for (int r = range.getFirstRow(); r <= range.getLastRow(); r++) {
      Student student= new Student();
      student.setLastName(getCell(this.worksheet, r, 1).getStringCellValue());
      student.setFirstName(getCell(this.worksheet, r, 2).getStringCellValue());
      for(int c = 3; c <= 7; c++) {
        int value = (int)getCell(this.worksheet, r, c).getNumericCellValue();
        student.addScore(new Score(value));
      }
      results.add(student);
    }
    return results;
  }
  
  private static Cell getCell(Sheet sheet, int rowIndex, int columnIndex) {
    Row row = sheet.getRow(rowIndex);
    if (row != null) {
      Cell cell = row.getCell(columnIndex);
      return cell;
    }
    return null;
  }
}
