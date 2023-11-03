package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Car;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.CarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class CarService {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarService.class);

    @Autowired
    private CarRepository carRepository;

    @Async
    public CompletableFuture<List<Car>> saveCars(final MultipartFile file) throws Exception {
        final long start = System.currentTimeMillis();
        LOGGER.info("Time start the request {}",start);

        List<Car> cars = parseCSVFile(file);

        LOGGER.info("Saving a list of cars of size {} records", cars.size());

        cars = carRepository.saveAll(cars);

        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
        return CompletableFuture.completedFuture(cars);
    }

//    @Async
//    public CompletableFuture<List<Car>> saveCars(final InputStream inputStream) throws Exception {
//        final long start = System.currentTimeMillis();
//
//        List<Car> cars = parseCSVFile( inputStream);
//
//        LOGGER.info("Saving a list of cars of size {} records", cars.size());
//
//        cars = carRepository.saveAll(cars);
//
//        LOGGER.info("Elapsed time: {}", (System.currentTimeMillis() - start));
//        return CompletableFuture.completedFuture(cars);
//    }





    private List<Car> parseCSVFile(final MultipartFile file) throws Exception {
        final List<Car> cars=new ArrayList<>();
        try {
            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
                String line;
                while ((line=br.readLine()) != null) {
                    final String[] data=line.split(";");
                    final Car car=new Car();
                    car.setMaXe(data[0]);
                    car.setManufacturer(data[1]);
                    car.setModel(data[2]);
                    car.setType(data[3]);
                    cars.add(car);
                }
                return cars;
            }
        } catch(final IOException e) {
            LOGGER.error("Failed to parse CSV file {}", e);
            throw new Exception("Failed to parse CSV file {}", e);
        }
    }

//    private List<Car> parseCSVFile(final InputStream inputStream) throws Exception {
//        final List<Car> cars=new ArrayList<>();
//        try {
//            try (final BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
//                String line;
//                while ((line=br.readLine()) != null) {
//                    final String[] data=line.split(";");
//                    final Car car=new Car();
//
//                    car.setMaXe(data[0]);
//                    car.setManufacturer(data[1]);
//                    car.setModel(data[2]);
//                    car.setType(data[3]);
//                    cars.add(car);
//                }
//                return cars;
//            }
//        } catch(final IOException e) {
//            LOGGER.error("Failed to parse CSV file {}", e);
//            throw new Exception("Failed to parse CSV file {}", e);
//        }
//    }

//    @Async
//    public CompletableFuture<List<Car>> getAllCars() {
//
//        LOGGER.info("Request to get a list of cars");
//
//        final List<Car> cars = carRepository.findAll();
//        return CompletableFuture.completedFuture(cars);
//    }

    @Async
    public CompletableFuture<List<Car>> getAllCars() {
        final LocalDateTime start= LocalDateTime.now();
        LOGGER.info("Request to get a list of cars {}",start);

        final List<Car> cars = carRepository.findAll();
        return CompletableFuture.completedFuture(cars);
    }
}
