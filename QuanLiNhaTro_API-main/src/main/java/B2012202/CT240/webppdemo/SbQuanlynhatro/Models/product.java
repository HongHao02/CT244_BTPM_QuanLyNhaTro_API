//package com.CT240.B2012202.TKVQLNhatro.models;
////POJO === Plan Object Java Object
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//
//
//@Entity
//public class product {
//    //This is primary key
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String productName;
//    private int productYear;
//    private Double price;
//    private String url;
//
//    //default constructor
//    public product(){}
//
//
//    //Sau nay khong can id do co cac ham tu sinh trong CSDL
//    //Dung theo kieu khong tu sinh id
////    public product(Long id, String productName, int productYear, Double price, String url) {
////        this.id = id;
////        this.productName = productName;
////        this.productYear = productYear;
////        this.price = price;
////        this.url = url;
////    }
//
////    Dung theo kieu TU SINH id
//    public product( String productName, int productYear, Double price, String url) {
//        this.productName = productName;
//        this.productYear = productYear;
//        this.price = price;
//        this.url = url;
//    }
//
//    @Override
//    public String toString() {
//        return "product{" +
//                "id=" + id +
//                ", productName='" + productName + '\'' +
//                ", name=" + productYear +
//                ", price=" + price +
//                ", url='" + url + '\'' +
//                '}';
//    }
//
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getProductName() {
//        return productName;
//    }
//
//    public void setProductName(String productName) {
//        this.productName = productName;
//    }
//
//    public int getproductYear() {
//        return this.productYear;
//    }
//
//    public void setProductYear(int productYear) {
//        this.productYear = productYear;
//    }
//
//    public Double getPrice() {
//        return price;
//    }
//
//    public void setPrice(Double price) {
//        this.price = price;
//    }
//
//    public String getUrl() {
//        return url;
//    }
//
//    public void setUrl(String url) {
//        this.url = url;
//    }
//
//
//}
//
////-----------------------------------------------------DATABASE---------------------------------------------------------
package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;
//POJO === Plan Object Java Object

import jakarta.persistence.*;

import java.util.Calendar;
import java.util.Objects;


@Entity
@Table(name="product")
public class product {
    //This is primary key
    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)

    //You can also use "sequence"--Bo quy tac cho viec tao ra id(hay bat cu thuoc tinh nao khac) khi co
    //1  doi tuong moi duoc tao ra (thay cho doan GenertationType.AUTO)
    @SequenceGenerator(
            name= "product_sequence",
            sequenceName = "product_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )

    private Long id;
    //validate= constraint
    //mot san pham chi co 1 ten, khong duoc trung nhau, do dai toi da 300 ki tu
    @Column(nullable = false,unique = true,length = 300)
    private String productName;
    private int productYear;
    private Double price;
    private String url;

    //calculated value = trasient (Khong duoc luu trong csdl)
    //Truong duoc tinh tu truong khac
    @Transient
    private int age;//age is calculated from productYear
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR)- productYear;
    }

    //Ta cung co ham equal
    //Hai doi tuong co 1 so truong chinh giong nhau thi duoc goi la giong nhau, phu hop cho viec loc du lieu

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof product)) return false;
        product product = (product) o;
        return productYear == product.productYear
                && getAge() == product.getAge()
                && Objects.equals(getId(), product.getId())
                && Objects.equals(getProductName(), product.getProductName())
                && Objects.equals(getPrice(), product.getPrice())
                && Objects.equals(getUrl(), product.getUrl());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getProductName(), productYear, getPrice(), getUrl(), getAge());
    }

    //default constructor
    public product(){}



    //Sau nay khong can id do co cac ham tu sinh trong CSDL
    //Dung theo kieu khong tu sinh id
//    public product(Long id, String productName, int productYear, Double price, String url) {
//        this.id = id;
//        this.productName = productName;
//        this.productYear = productYear;
//        this.price = price;
//        this.url = url;
//    }
    //calculated field= trasient
    //Truong duoc tinh tu truong khac, khong duoc luu trong csdl
    //    Dung theo kieu TU SINH id
    public product( String productName, int productYear, Double price, String url) {
        this.productName = productName;
        this.productYear = productYear;
        this.price = price;
        this.url = url;
    }

//    @Override
//    public String toString() {
//        return "product{" +
//                "id=" + id +
//                ", productName='" + productName + '\'' +
//                ", name=" + productYear +
//                ", price=" + price +
//                ", url='" + url + '\'' +
//                '}';
//    }


    @Override
    public String toString() {
        return "product{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", productYear=" + productYear +
                ", price=" + price +
                ", url='" + url + '\'' +
                ", age=" + this.getAge() +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getproductYear() {
        return this.productYear;
    }

    public void setProductYear(int productYear) {
        this.productYear = productYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}

//-----------------------------------------------------DATABASE---------------------------------------------------------
