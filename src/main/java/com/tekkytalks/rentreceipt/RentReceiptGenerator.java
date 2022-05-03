package com.tekkytalks.rentreceipt;

import com.spire.doc.Document;
import com.spire.doc.FileFormat;


import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.Month;
import java.util.HashMap;
import java.util.Map;

import static java.time.temporal.TemporalAdjusters.lastDayOfMonth;

public class RentReceiptGenerator {


    static void replacePlaceHolders(Map<String, String> map, Document document) {

        for (Map.Entry<String, String> entry : map.entrySet()) {

            document.replace(entry.getKey(), entry.getValue(), false, true);

        }

    }

    public static void main(String args[]) throws FileNotFoundException {

        System.out.println("*****************Receipt Generation Starts**************");
        FileReader obj = new FileReader();
        String userDirectory = System.getProperty("user.dir");


        for (int i = 1; i <= 12; i++) {
            Map<String, String> map = new HashMap<String, String>();
            Document document = new Document(userDirectory + "\\src\\main\\Resources\\RentTemplate.docx");

            LocalDate initial = LocalDate.of(obj.getYear(), i, 01);
            Month month = Month.of(i);
            LocalDate start = initial.withDayOfMonth(1);
            LocalDate end = initial.with(lastDayOfMonth());

            map.put("_MONTH_", String.valueOf(month) + " " + obj.getYear());
            map.put("_REC_NO_", "RR_" + i);
            map.put("_AMNT_", Integer.toString(obj.getRent()));
            map.put("_NAME_", obj.getName());
            map.put("_ADDR1_", obj.getAddr1());
            map.put("_ADDR2_", obj.getAddr2());
            map.put("_PINCODE_", obj.getPincode());
            map.put("_START_DATE_", String.valueOf(start));
            map.put("_END_DATE_", String.valueOf(end));
            map.put("_LAND_LORD_NME_", obj.getLandlordName());

            if (!obj.getLandlordPan().equals("") || !obj.getLandlordPan().equals(null)) {
                map.put("_LAND_LORD_PAN_", obj.getLandlordPan());
            } else {
                map.put("_LAND_LORD_PAN_", "");
            }

            replacePlaceHolders(map, document);

            document.saveToFile(userDirectory + "\\GeneratedReceipts\\" + month + "_RentReceipt.pdf", FileFormat.PDF);
            map.clear();
            System.out.println("*****************Receipt Generation Success for the Month -->"+month+"**************");
        }

        System.out.println("*****************Receipt Generation Completed**************");
        System.out.println("*****************Will See You Next Year**************");
    }
}
