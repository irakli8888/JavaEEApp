package com.example.EEApp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "helloServlet", value = "/")
public class Task1Servlet extends HttpServlet {

    private final String [] cyrillic = {"А","Б","В","Г","Д","Е","Ё","Ж","З","И","Й","К","Л","М","Н","О","П","Р","С",
            "Т","У","Ф","Х","Ц","Ч","Ш","Щ","Ъ","Ы","Ь","Э","Ю","Я"};

    private final String [] latin = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S",
            "T","U","V","W","X","Y","Z"};

    private final String FILE_PATH = "C:\\Users\\1\\IdeaProjects\\EEApp\\src\\main\\resources\\task1.txt";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.setContentType("text/html");
        String text = request.getParameter("area");
        System.out.println(text);
        request.setAttribute("message", getMessage(text));
        request.getRequestDispatcher("/task1.jsp").forward(request, response);
        writingToAFile(text);
    }

    public void writingToAFile(String text) throws FileNotFoundException {
        File file = new File(FILE_PATH);
        PrintWriter printWriter = new PrintWriter(file);
        printWriter.write(getMessage(text));
        printWriter.flush();
        printWriter.close();
    }

    //формируем ответ
    public String getMessage(String textArea){
        StringBuilder text = new StringBuilder();
        StringBuilder numbersStr = new StringBuilder();
        String paragraph [] = textArea.split("\n");
        for(int i = 0; i < paragraph.length; i++){
            text.append("<p></p>");
            for (int j = 0; j < paragraph[i].length(); j++){
                String str = paragraph[i].substring(j, j + 1);//получаем символ
                text.append(str + "  ");//отступ 2 пробела
                String strAlphabetNum = getNum(str.toUpperCase());//получаем порядковый номер
                numbersStr.append (strAlphabetNum + (strAlphabetNum.length() == 2 ? " ":"  "));//добавляем в StringBuilder
            }                                                                                 // для номеров(если номер двузначный, то делаем 1 пробел)
            text.append("<p></p>"+numbersStr+"");
            numbersStr.setLength(0);
        }
        return text.toString();
    }

    //определяем номер буквы по алфавиту
    public String getNum(String ch){
        String symbol = getCharType(ch.toCharArray());
        switch (symbol) {
            case ("C"):
                for(int i = 0; i < cyrillic.length; i++){
                    if(cyrillic[i].equals(ch))
                        return String.valueOf(i + 1);
                }
                break;
            case ("L"):
                for(int i = 0; i < latin.length; i++){
                    if(latin[i].equals(ch))
                        return String.valueOf(i + 1);
                }
                break;
            default:
                return ch;
        }return null;
    }

    //определяем кириллический или латиский символ
    public static String getCharType(char [] ch) {
        if (Character.isDigit(ch[0])) {
            return "D";
        } else if (Character.isAlphabetic(ch[0])) {
            if (Character.UnicodeBlock.of(ch[0]).equals(Character.UnicodeBlock.CYRILLIC)) {
                return "C";
            } else if (Character.UnicodeBlock.of(ch[0]).equals(Character.UnicodeBlock.BASIC_LATIN)){
                return "L";
            }
        }
        return "U";
    }

}
