//package com.CT240.B2012202.TKVQLNhatro.controller;
//
//
//import com.CT240.B2012202.TKVQLNhatro.models.ResponseObject;
//import com.CT240.B2012202.TKVQLNhatro.models.product;
//import com.CT240.B2012202.TKVQLNhatro.repositories.ProductRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.lang.annotation.Repeatable;
//import java.util.List;
//import java.util.Optional;
//
////Now we will connect database (mysql) using JPA
///*
//docker run -d --rm --name mysql-spring-boot-tutorial \
//-e MYSQL_ROOT_PASSWORD= 123456 \
//-e MYSQL_USER= hoangnd \
//-e MYSQL_PASSWORD= 123456 \
//-e MYSQL_DATABASE= test_db \
//-p 3309:3306 \
//--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
//mysql: latest
//
//mysql -h localhost -P 3309 -protocol=tcp -u hoangnd -p
//* */
//
//@RestController
//@RequestMapping(path = "api/v1/Products")
//
//public class ProductController {
//
//    //DI = dependency Injection
//    @Autowired
//    private ProductRepository repository;
//
//    @GetMapping("")
//
//    //This request is: http://localhost:8080/api/v1/Products
//
//    List<product> getAllProduct(){
//        //dung kieu cu
////        return List.of(
////                new product(1L, "MacbookPro 2021",2020,2400.0,""),
////                new product(2L,"MacbookAir 2013",2013,1500.0,"")
////        );
//        //dung repository
//        return repository.findAll();
//
//    }
//
//    //you can also send request with Postman
//    //you must have save this to DB, now we have H2 DB ( in-memory) truy cập trực tiếp, dùng để test trong lúc demo
//    //có thể thử thật lúc đã hoàn thành test
//
//    /*Tim nhung ban ghi co chua ID, ly do bao cac doi tuong bang Optional la do ket qua tra ve co the null*/
////    @GetMapping("/{id}")
////    product findById(@PathVariable Long id){
////        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find Product with id " + id));
////        }
////    }
//
//
//    //Format return Object
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
//        Optional<product> foundObject= repository.findById(id);
//        return foundObject.isPresent()?
//                ResponseEntity.status(HttpStatus.OK).body(
//                        new ResponseObject("ok","Query product successfully",foundObject)
//                ):
//                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                        new ResponseObject("false","Cannot find product with "+ id,"")
//                );
//    }
//
//    //insert product with post method
//    //Postman: Raw, JSON
//    @PostMapping("/insert")
//    ResponseEntity<ResponseObject> insertProduct(@RequestBody product newProduct){
//        //Two product must have two name
//        List<product> foundproducts= repository.findByproductName(newProduct.getProductName().trim());
//
//        return foundproducts.size() > 0
//                ?
//                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//
//                        new ResponseObject("ok","Product name already taken","")
//                )
//                :
//                ResponseEntity.status(HttpStatus.OK).body(
//
//                new ResponseObject("ok","Insert product succesfully",repository.save(newProduct))
//        );
//
//
//    }
//
//    //update, upsert= update if found, otherwise insert
//    @PutMapping("/{id}")
//
//    ResponseEntity<ResponseObject> updateProduct(@RequestBody product newProduct , @PathVariable Long id){
//        //id se duoc truyen vao tu PutMapping cho @PathVariable
//        //Tim xem san pham nay da co trong CSDL chua
//        product updateProduct= repository.findById(id)
//                //Neu co roi thi update lai cac truong thong tin cua no
//                .map(product->{
//                    product.setProductName(newProduct.getProductName());
//                    product.setProductYear(newProduct.getproductYear());
//                    product.setPrice(newProduct.getPrice());
//                    product.setUrl(newProduct.getUrl());
//                    return repository.save(product);
//                }).orElseGet(()->{
//                    //neu chua thi insertProduct moi vao DB
//                    newProduct.setId(id);
//                    return repository.save(newProduct);
//                });
//        //Trong truong hop moi thu OK thi in ra trang thai OK
//        return ResponseEntity.status(HttpStatus.OK).body(
//                new ResponseObject("Ok","Update product successfully",updateProduct)
//        );
//    }
//
//    //Delete product => DELETE Method
//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
//        boolean exitProduct= repository.existsById(id);
//        if(exitProduct){
//            //Ham co san trong JPA
//            repository.deleteById(id);
//            return ResponseEntity.status(HttpStatus.OK).body(
//                    new ResponseObject("ok","Delete product successfully","")
//            );
//        }
//        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
//                new ResponseObject("false","Cannot delete product","")
//        );
//
//
//    }
//
//}
//

//-----------------------------------------------------DATABASE---------------------------------------------------------
package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;



import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.product;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//Now we will connect database (mysql) using JPA
/*
docker run -d --rm --name mysql-spring-boot-tutorial \
-e MYSQL_ROOT_PASSWORD= 123456 \
-e MYSQL_USER= hoangnd \
-e MYSQL_PASSWORD= 123456 \
-e MYSQL_DATABASE= test_db \
-p 3309:3306 \
--volume mysql-spring-boot-tutorial-volume:/var/lib/mysql \
mysql: latest

mysql -h localhost -P 3309 -protocol=tcp -u hoangnd -p
* */

@RestController
@RequestMapping(path = "api/v1/Products")

public class ProductController {

    //DI = dependency Injection
    @Autowired
    private ProductRepository repository;

    @GetMapping("")

        //This request is: http://localhost:8080/api/v1/Products

    List<product> getAllProduct(){
        //dung kieu cu
//        return List.of(
//                new product(1L, "MacbookPro 2021",2020,2400.0,""),
//                new product(2L,"MacbookAir 2013",2013,1500.0,"")
//        );
        //dung repository
        return repository.findAll();

    }

    //you can also send request with Postman
    //you must have save this to DB, now we have H2 DB ( in-memory) truy cập trực tiếp, dùng để test trong lúc demo
    //có thể thử thật lúc đã hoàn thành test

    /*Tim nhung ban ghi co chua ID, ly do bao cac doi tuong bang Optional la do ket qua tra ve co the null*/
//    @GetMapping("/{id}")
//    product findById(@PathVariable Long id){
//        return repository.findById(id).orElseThrow(() -> new RuntimeException("Cannot find Product with id " + id));
//        }
//    }


    //Format return Object
    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
        Optional<product> foundObject= repository.findById(id);
        return foundObject.isPresent()?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok","Query product successfully",foundObject)
                ):
                ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                        new ResponseObject("false","Cannot find product with "+ id,"")
                );
    }

    //insert product with post method
    //Postman: Raw, JSON
    @PostMapping("/insert")
    ResponseEntity<ResponseObject> insertProduct(@RequestBody product newProduct){
        //Two product must have two name
        List<product> foundproducts= repository.findByproductName(newProduct.getProductName().trim());

        return foundproducts.size() > 0
                ?
                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(

                        new ResponseObject("ok","Product name already taken","")
                )
                :
                ResponseEntity.status(HttpStatus.OK).body(

                        new ResponseObject("ok","Insert product succesfully",repository.save(newProduct))
                );


    }

    //update, upsert= update if found, otherwise insert
    @PutMapping("/{id}")

    ResponseEntity<ResponseObject> updateProduct(@RequestBody product newProduct , @PathVariable Long id){
        //id se duoc truyen vao tu PutMapping cho @PathVariable
        //Tim xem san pham nay da co trong CSDL chua
        product updateProduct= repository.findById(id)
                //Neu co roi thi update lai cac truong thong tin cua no
                .map(product->{
                    product.setProductName(newProduct.getProductName());
                    product.setProductYear(newProduct.getproductYear());
                    product.setPrice(newProduct.getPrice());
                    product.setUrl(newProduct.getUrl());
                    return repository.save(product);
                }).orElseGet(()->{
                    //neu chua thi insertProduct moi vao DB
                    newProduct.setId(id);
                    return repository.save(newProduct);
                });
        //Trong truong hop moi thu OK thi in ra trang thai OK
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("Ok","Update product successfully",updateProduct)
        );
    }

    //Delete product => DELETE Method
    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteProduct(@PathVariable Long id){
        boolean exitProduct= repository.existsById(id);
        if(exitProduct){
            //Ham co san trong JPA
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok","Delete product successfully","")
            );
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("false","Cannot delete product","")
        );


    }

}

