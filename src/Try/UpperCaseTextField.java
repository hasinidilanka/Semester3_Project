/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Try;

/**
 *
 * @author Hasini
 */



  import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;
public class UpperCaseTextField extends JTextField {
  public UpperCaseTextField() {
    super();
  }
  public UpperCaseTextField(Document doc, String text, int columns) {
    super(doc, text, columns);
  }
  public UpperCaseTextField(int columns) {
    super(columns);
  }
  public UpperCaseTextField(String text) {
    super(text);
  }
  public UpperCaseTextField(String text, int columns) {
    super(text, columns);
  }
  protected Document createDefaultModel() {
    return new LimitedDocument();
  }
  static class LimitedDocument extends PlainDocument {
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {
      if (str == null) {
        return;
      }
      // Set the text of this document to uppercase
      super.insertString(offs, str.toUpperCase(), a);
    }
  }


    
}
