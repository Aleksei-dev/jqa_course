package task2.ru.stqa.pft.sandbox;

import java.awt.event.*;
import java.awt.*;

public class Class extends Frame implements ActionListener {

  public static void main(String[] args) {
    new Class();
  }

  Frame f = new Frame("MSISDN to MSISDN_HEX Converter");
  Frame popup = new Frame("Information");
  Font myFont = new Font("Verdana", Font.PLAIN, 14);
  TextArea inputTa = new TextArea("37254962826,\r\n37281956123", 10, 0, TextArea.SCROLLBARS_NONE);
  TextArea outputTa = new TextArea("", 10, 0, TextArea.SCROLLBARS_NONE);
  TextArea popupTa;
  Button b1 = new Button("Convert");
  Button b2 = new Button("Info");
  Label l1, l2;

  Class() {
    //popup.setSize(300, 300);
    popup.setBounds(450, 300, 350, 300);
    inputTa.setBounds(10, 50, 260, 200);
    outputTa.setBounds(380, 50, 260, 200);
    b1.setBounds(285, 100, 80, 50);
    b2.setBounds(285, 160, 80, 50);
    b1.addActionListener(this);
    b2.addActionListener(this);
    f.add(inputTa);
    f.add(outputTa);
    f.add(b1);
    f.add(b2);
    f.setBounds(300, 300, 650, 300);
    f.setLayout(null);
    f.setVisible(true);
    f.setResizable(false);
    outputTa.setEditable(false);
    l1 = new Label("Input for MSISDNs (Comma Separated)");
    l1.setBounds(10, 260, 300, 20);
    l2 = new Label("Output for MSISDN_HEX");
    l2.setBounds(430, 260, 190, 20);
    l2.setFont(myFont);
    l1.setFont(myFont);
    f.add(l1);
    f.add(l2);
    f.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
    });
    popupTa = new TextArea("                      Описание конвертации:\n\n" +
            "MSISDN_HEX - это значение MSISDN в формате:\n" +
            "Length of BCD number (1 byte)\n" +
            "TON and NPI (1 byte)\n" +
            "Dialling Number (10 bytes)\n" + "\n" +
            "Например, номер +123456789 кодируется так: 069121436587F9FFFFFFFFFF\n\n" +
            "Здесь 06 - длина значения \"9121436587F9\", в байтах (символ '+' тоже считается как байт)\n" +
            "91 - TON/NPI - номер в международном формате (т.е. с плюсом в начале)\n" +
            "и добавляются FFF..FF в конце, чтобы конечное значение равнялось 12 байтам.", 3, 100, TextArea.SCROLLBARS_NONE);
    popupTa.setBounds(20, 20, 20, 20);
    popupTa.setFont(myFont);
    popupTa.setEditable(false);
    popup.add(popupTa);
    popup.setResizable(false);
    popup.addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent e) {
        popup.setVisible(false);
      }
    });
  }

  public void actionPerformed(ActionEvent e) {
    String s1 = inputTa.getText();
    if (e.getSource() == b1) {
      String result = swapPairs(s1);
      outputTa.setText(result);
    } else if (e.getSource() == b2) {
      f.getLocation();
      popup.setLocationRelativeTo(f);
      popup.setVisible(true);
    }
  }

  public static String swapPairs(String inputString) {
    char endOfStringR = '\r';
    char endOfStringN = '\n';
    char lastDigit = 'F';
    String swappedNumbers = "";
    //Valid
    if (inputString.equals("")) {
      swappedNumbers = "Please, provide at least single MSISDN!";
    } else if (inputString.contains(",\r")) {
      if (inputString.endsWith(",")) {
        inputString = inputString + endOfStringR + endOfStringN;
      }
      String[] arrayOfNumbers = inputString.split(",\r\n");
      for (String str : arrayOfNumbers) {
        str = str + lastDigit;
        char[] arr = str.toCharArray();
        if (arr.length == 12) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
        } else if (arr.length == 16) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
        }
      }
      return swappedNumbers.trim().replaceAll(",$", "");
    } else if (!inputString.contains(",")) {
      String[] arrayOfNumbers = inputString.split(",");
      for (String str : arrayOfNumbers) {
        str = str + lastDigit;
        char[] arr = str.toCharArray();
        if (arr.length == 12) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
        } else if (arr.length == 16) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
        }
      }
      return swappedNumbers.trim().replaceAll(",$", "");
    } else if (!inputString.endsWith(",")) {
      String[] arrayOfNumbers = inputString.split(",");
      for (String str : arrayOfNumbers) {
        str = str + lastDigit;
        char[] arr = str.toCharArray();
        if (arr.length == 12) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
        } else if (arr.length == 16) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
        }
      }
      return swappedNumbers.trim().replaceAll(",$", "");
    } else if (inputString.endsWith(",")) {
      String[] arrayOfNumbers = inputString.split(",");
      for (String str : arrayOfNumbers) {
        str = str + lastDigit;
        char[] arr = str.toCharArray();
        if (arr.length == 12) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
        } else if (arr.length == 16) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
        }
      }
      return swappedNumbers.trim().replaceAll(",$", "");
    }
    else if (inputString.endsWith(",")) {
      String[] arrayOfNumbers = inputString.split(",");
      for (String str : arrayOfNumbers) {
        str = str + lastDigit;
        char[] arr = str.toCharArray();
        if (arr.length == 12) {
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
        }
        else if (arr.length == 16){
          for (int i = 1; i < arr.length; i += 2) {
            if (arr[i] == ',') {
              i += 2;
            }
            char swap = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = swap;
          }
          swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
        }
      }
      return swappedNumbers.trim().replaceAll(",$", "");
    }
    else if (inputString.contains(",")) {
      String[] arrayOfNumbers = inputString.split(",");
      if (!inputString.equals("")) {
        for (String str : arrayOfNumbers) {
          str = str + lastDigit;
          char[] arr = str.toCharArray();
          if (arr.length == 12) {
            for (int i = 1; i < arr.length; i += 2) {
              if (arr[i] == ',') {
                i += 2;
              }
              char swap = arr[i];
              arr[i] = arr[i - 1];
              arr[i - 1] = swap;
            }
            swappedNumbers = swappedNumbers + "0791" + new String(arr) + "FFFFFFFF \n";
          }
          else if (arr.length == 16){
            for (int i = 1; i < arr.length; i += 2) {
              if (arr[i] == ',') {
                i += 2;
              }
              char swap = arr[i];
              arr[i] = arr[i - 1];
              arr[i - 1] = swap;
            }
            swappedNumbers = swappedNumbers + "0991" + new String(arr) + "FFFF \n";
          }
        }
        return swappedNumbers.trim().replaceAll(",$", "");
      } else if (inputString.equals("")) {
        swappedNumbers = "Please, provide at least single MSISDN!";
      }
    }
    return swappedNumbers;
  }
}