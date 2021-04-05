package com.example.EEApp.servlet;

import com.example.EEApp.model.Payment;
import com.example.EEApp.model.Payments;
import com.example.EEApp.model.Result;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;

@WebServlet(name = "task2Servlet", value = "/task2")
public class Task2Servlet extends HttpServlet {

    private final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd");

    private final String FILE_PATH = "C:\\Users\\1\\IdeaProjects\\EEApp\\src\\main\\resources\\task2.txt";

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String paymentParameter = request.getParameter("payment");
        String dateParameter = request.getParameter("date");
        Date date = null;
        try {
            date = FORMAT.parse(dateParameter);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Payments payments = unmarshallPayments(paymentParameter);
        request.setAttribute("resultXML",   marshallResult(payments, date));
        request.getRequestDispatcher("/task2.jsp").forward(request, response);
    }

    //получем данные из поступившего XML
    public Payments unmarshallPayments(String paymentParameter){
        try {
            StringReader reader = new StringReader(paymentParameter);
            JAXBContext context = JAXBContext.newInstance(Payments.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Payments payments = (Payments) unmarshaller.unmarshal(reader);
            return payments;
        }catch (JAXBException e){
            e.printStackTrace();
        }return null;
    }

    //считаем результат по дате и маршаллизуем в XML
    public String marshallResult(Payments payments, Date date){
        StringWriter writer = new StringWriter();
        ArrayList<Payment> paymentArrayList = new ArrayList<>();
        paymentArrayList.addAll(payments.getPaymentList().stream().filter(el -> el.getSupplyDate().equals(date)).collect(Collectors.toList()));
        long debet = 0;
        long credit = 0;
        long forState = 0;
        for(int i = 0; i < paymentArrayList.size(); i++){

            if(paymentArrayList.get(i).getPart() == 'D'){//считаем сумму дебетовых операций
                debet += paymentArrayList.get(i).getValue();
            }else {
                credit += paymentArrayList.get(i).getValue(); //считаем сумму кредитовых операций
            }
            if(paymentArrayList.get(i).isState()){
                forState += paymentArrayList.get(i).getValue(); //считаем сумму налогов(по идее, налоги должны
            }                                                   //всегда быть как кредитовые операции, поэтому на дебет\кредит нет смысла проверять)
        }
        long resultBalance = debet - credit; //узнаем балнс
        float resultPercent = ((float) forState/resultBalance) * 100;//узнаем, какой процент составляют налоги от баланса
        Result result = new Result();
        result.setBalance(resultBalance);
        result.setPercent(resultPercent);
        try {
            JAXBContext contextForResult = JAXBContext.newInstance(Result.class);
            Marshaller m = contextForResult.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            m.marshal(result, writer);
            m.marshal(result, new File(FILE_PATH));
            return writer.toString();
        }catch (JAXBException e){
            e.printStackTrace();
        }return null;
    }

}



