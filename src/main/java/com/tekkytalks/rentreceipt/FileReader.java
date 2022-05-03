package com.tekkytalks.rentreceipt;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class FileReader {

    private String name;
    private String addr1;
    private String addr2;
    private int year;
    private int rent;
    private String landlordName;
    private String landlordPan;

    private String pincode;

    public String getPincode() {
        return pincode;
    }

    public String getName() {
        return name;
    }

    public String getAddr1() {
        return addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public int getYear() {
        return year;
    }

    public int getRent() {
        return rent;
    }

    public String getLandlordName() {
        return landlordName;
    }

    public String getLandlordPan() {
        return landlordPan;
    }

    FileReader(){
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream("InputFolder/input.ini")) {
            props.load(in);
            this.name=props.getProperty("name");
            this.addr1=props.getProperty("address1");
            this.addr2=props.getProperty("address2");
            this.landlordName=props.getProperty("landlordname");
            this.landlordPan=props.getProperty("landlordpan");
            this.rent=Integer.parseInt(props.getProperty("rent"));
            this.year=Integer.parseInt(props.getProperty("year"));
            this.pincode=props.getProperty("pincode");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
