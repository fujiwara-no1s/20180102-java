package premier20180102;

import java.util.List;

public class Main {
  
  public static String EXCEL_FILE_PATH;
  public static String WORKSHEET_NAME;
  public static String DATA_RANGE;

  public static void main(String[] args) {
    // setting properties
    setProperty();
    // main process start
    try {
      // read excel file
      ExcelReader excelReader = new ExcelReader(EXCEL_FILE_PATH);
      // choose work sheet
      excelReader.findSheet(WORKSHEET_NAME);
      // get students list
      List<Student> students = excelReader.getStudentList(DATA_RANGE);
      // show list of students
      showData(students);
    } catch(Exception e) {
      System.out.println(e.getMessage());
    }
  }
  
  private static void setProperty() {
    // Propertyファイルから設定を反映
    EXCEL_FILE_PATH = PropertyUtil.getProperty("EXCEL_FILE_PATH");
    WORKSHEET_NAME = PropertyUtil.getProperty("WORKSHEET_NAME");
    DATA_RANGE = PropertyUtil.getProperty("DATA_RANGE");
  }
  
  private static void showData(List<Student> students) {
    System.out.println("名前\t\t国語\t数学\t英語\t社会\t理科\t合計点");
    for(Student student: students) {
      System.out.print(student.getLastName() + "\t");
      System.out.print(student.getFirstName() + "\t");
      for(Score score : student.getScores()) {
        System.out.print(score.getValue() + "\t");
      }
      System.out.print(student.getTotalScore() + "\n");
    }
  }
}
