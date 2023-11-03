package B2012202.CT240.webppdemo.SbQuanlynhatro.services;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.Car;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.User;
import B2012202.CT240.webppdemo.SbQuanlynhatro.Repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    Logger logger = LoggerFactory.getLogger(UserService.class);

//    @Async
//    public CompletableFuture<List<User>> saveUsers(MultipartFile file) throws Exception {
//
//        final long start = System.currentTimeMillis();
//        logger.info("Time start the request {}",start);
//
//        List< User > users = parseCSVFile(file);
//
//        logger.info("Saving a list of cars of size {} records", users.size());
//
//        users=userRepository.saveAll(users);
//
//        logger.info("Elapsed time: {}", (System.currentTimeMillis() - start));
//        return CompletableFuture.completedFuture(users);
//    }

    @Async
    public CompletableFuture<List<User>> findAllUsers() {
        logger.info("get list of user by {}", Thread.currentThread().getName());
        final LocalDateTime start = LocalDateTime.now();
        logger.info("Request to get a list of user {}", start);
        List<User> users = userRepository.findAll();
        return CompletableFuture.completedFuture(users);
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User findUserByIdUser(Long idUser) {
        return userRepository.findByIdUser(idUser);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public String getPasswordByEmail(String email) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user.getPassword();
        }
        return null;//Thông báo lỗi nếu không tìm thấy email
    }
//    private List< User > parseCSVFile( final MultipartFile file) throws Exception {
//        final List<User> users=new ArrayList<>();
//        try {
//            try (final BufferedReader br = new BufferedReader(new InputStreamReader(file.getInputStream()))) {
//                String line;
//                while ((line=br.readLine()) != null) {
//                    final String[] data=line.split(";");
//                    final User user=new User();
//                    user.setCccd(data[0]);
//                    user.setName(data[1]);
//                    user.setGender(data[2]);
//                    user.setEmail(data[3]);
//                    user.setAddress(data[4]);
//                    users.add(user);
//                }
//                return users;
//            }
//        } catch(final IOException e) {
//            logger.error("Failed to parse CSV file {}", e);
//            throw new Exception("Failed to parse CSV file {}", e);
//        }
//    }

}
