package premier20180102;

import java.util.ArrayList;
import java.util.List;

public class Student {
  private String lastName;
  private String firstName;
  private List<Score> scores;
  
  Student() {
    this.scores = new ArrayList<Score>();
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public List<Score> getScores() {
    return scores;
  }

  public void setScores(List<Score> scores) {
    this.scores = scores;
  }
  
  public void addScore(Score score) {
    this.scores.add(score);
  }
  
  public int getTotalScore() {
    return scores.stream().mapToInt(Score::getValue).sum();
  }
}
