package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Car;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.ResponseObject;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.CarRepository;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

@RestController
@RequestMapping("/api/v1/cars")
public class CarController {
//    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);
//    @Autowired
//    private CarService carService;
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @GetMapping("")
//
//        //This request is: http://localhost:8080/api/v1/car
//
//    List<Car> getAll(){
//        //dung kieu cu
////        return List.of(
////                new product(1L, "MacbookPro 2021",2020,2400.0,""),
////                new product(2L,"MacbookAir 2013",2013,1500.0,"")
////        );
//        //dung repository
//        return carRepository.findAll();
//
//    }
//
//    //Format return Object
//    @GetMapping("/{id}")
//    ResponseEntity<ResponseObject> findById(@PathVariable Long id){
//        Optional<Car> foundObject= carRepository.findById(id);
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
//    ResponseEntity<ResponseObject> insertCar(@RequestBody Car newCar){
//        //Two car must have two maXe
//        List<Car> foundCars= carRepository.findBymaXe(newCar.getMaXe().trim());
//
//        return foundCars.size() > 0
//                ?
//                ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
//
//                        new ResponseObject("ok","Maxe already taken","")
//                )
//                :
//                ResponseEntity.status(HttpStatus.OK).body(
//
//                        new ResponseObject("ok","Insert car succesfully",carRepository.save(newCar))
//                );
//
//
//    }
//
//
//
//    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
//            produces={MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody
//    CompletableFuture<ResponseEntity> getAllCars() {
//        return carService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
//                .exceptionally(handleGetCarFailure);
//    }
//
//    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
//        LOGGER.error("Failed to read records: {}", throwable);
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    };


    //CODE DIFFRENCE
    private static final Logger LOGGER = LoggerFactory.getLogger(CarController.class);

    @Autowired
    private CarService carService;

//    @RequestMapping (method = RequestMethod.POST, consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces={MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody ResponseEntity uploadFile(
//            @RequestParam (value = "files") MultipartFile[] files) {
//        try {
//            for(final MultipartFile file: files) {
//                carService.saveCars(file.getInputStream());
//            }
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } catch(final Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//
//        }
//
//    }

//    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
//            produces={MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody CompletableFuture<ResponseEntity> getAllCars() {
//        return carService.getAllCars().<ResponseEntity>thenApply(ResponseEntity::ok)
//                .exceptionally(handleGetCarFailure);
//    }

    @PostMapping(value= "/uploadCar" , consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody
    ResponseEntity uploadFile(
            @RequestParam("file") MultipartFile[] files) {
        try {
            for(final MultipartFile file: files) {
                carService.saveCars(file);
            }
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
            produces={MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity getAllCars() {
        try {
            CompletableFuture<List<Car>> cars1=carService.getAllCars();
            CompletableFuture<List<Car>> cars2=carService.getAllCars();
            CompletableFuture<List<Car>> cars3=carService.getAllCars();
            CompletableFuture.allOf(cars1, cars2, cars3).join();
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch(final Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //Ham nay se duoc goi khi CompletableFuture hoan thanh ngoai le
    //Neu hoan thanh binh thuong thi no se tra ve danh sach o to
    private static Function<Throwable, ResponseEntity<? extends List<Car>>> handleGetCarFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

}
