package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.BuyerForStat;
import org.example.model.Purchase;
import org.example.model.StatByPeriod;
import org.example.model.StatRequest;
import org.example.query.QueriesForStat;
import org.example.util.ExceptionWritter;
import org.example.util.PeriodWithoutWeekends;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class StatService {
    private StatByPeriod fabricStatByPeriod (String start, String end) {
        StatByPeriod statByPeriod = new StatByPeriod();
        List<BuyerForStat> buyers =  QueriesForStat.getBuyersByPeriod(start,end);
        for (BuyerForStat buyer : buyers){
            List<Purchase> purchases = QueriesForStat.getListPurchasesForBuyerByIdAndPeriod(buyer.getId(),start,end);
            for (Purchase purchase : purchases) {
                purchase.setExpense(QueriesForStat.getProductBuyByName(purchase.getName(),start,end));
            }
            buyer.setPurchases(purchases);
        }
        statByPeriod.setBuyer(buyers);
        statByPeriod.setTotalExpense(QueriesForStat.totalExpenseForPeriod(start,end));
        statByPeriod.setAverageExpense(QueriesForStat.AverageExpenseForPeriod(start,end));
        statByPeriod.setType("stat");
        statByPeriod.setTotalDays(PeriodWithoutWeekends.getWorkingDaysDifference(LocalDate.parse(start),LocalDate.parse(end)));
        return statByPeriod;
    }
    public void stat (File fileInput,File fileOutput) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            StatRequest statRequest = objectMapper.readValue(fileInput, StatRequest.class);
            StatByPeriod result = fabricStatByPeriod(statRequest.getStartDate(),statRequest.getEndDate());
            objectMapper.writeValue(fileOutput,result);
        } catch (IOException e) {
            ExceptionWritter.sendException(new File("failReport"), e.getClass().getTypeName(),
                    e.getLocalizedMessage());
            System.exit(1);
        }

    }
}
