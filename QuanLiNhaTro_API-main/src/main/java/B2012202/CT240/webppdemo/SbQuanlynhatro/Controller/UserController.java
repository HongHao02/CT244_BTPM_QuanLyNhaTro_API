//Checked
package B2012202.CT240.webppdemo.SbQuanlynhatro.Controller;

import B2012202.CT240.webppdemo.SbQuanlynhatro.Models.*;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.ImageStorageService;
import B2012202.CT240.webppdemo.SbQuanlynhatro.services.UserService;
import org.hibernate.sql.exec.ExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Stream;


@RestController
@RequestMapping("/api/auth")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private ImageStorageService imageStorageService;
    //CODE DIFFRENCE
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
//Lam theo kieu ket noi tu cong 9191
//    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE}, produces="application/json")
//    public ResponseEntity saveUsers( @RequestParam("file")MultipartFile[] files ) throws Exception {
//        //Tim trong danh sach cac MultipartFile[]
//        for (MultipartFile file:files){
//            userService.saveUsers(file);
//        }
//
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @GetMapping(value = "/users", produces = "application/json")
//    public CompletableFuture<ResponseEntity>  findAllUsers(){
//         return userService.findAllUsers().thenApply(ResponseEntity::ok);
//    }

//    @PostMapping(value = "/users", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
//    public ResponseEntity saveUsers( @RequestParam("file")MultipartFile[] files ) throws Exception {
//        //Tim trong danh sach cac MultipartFile[]
//        for (MultipartFile file:files){
//            userService.saveUsers(file);
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
//    @GetMapping(value = "/allusers")
//    public CompletableFuture<ResponseEntity>  findAllUsers(){
//        return userService.findAllUsers().thenApply(ResponseEntity::ok);
//    }

    //    @PostMapping(value= "/users" , consumes={MediaType.MULTIPART_FORM_DATA_VALUE},
//            produces={MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody
//    ResponseEntity uploadUser(
//            @RequestParam("file") MultipartFile[] files) {
//        try {
//            for(final MultipartFile file: files) {
//                userService.saveUsers(file);
//            }
//            return ResponseEntity.status(HttpStatus.CREATED).build();
//        } catch(final Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @RequestMapping (method = RequestMethod.GET, consumes={MediaType.APPLICATION_JSON_VALUE},
//            produces={MediaType.APPLICATION_JSON_VALUE})
//    public @ResponseBody ResponseEntity getAllCars() {
//        try {
//            CompletableFuture< List< User > > user1=userService.findAllUsers();
//            CompletableFuture<List<User>> user2=userService.findAllUsers();
//            CompletableFuture<List<User>> user3=userService.findAllUsers();
//            CompletableFuture.allOf(user1,user2,user3).join();
//            return ResponseEntity.status(HttpStatus.OK).build();
//        } catch(final Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
    @GetMapping("/users")
    public ResponseEntity<ResponseObject> getAllUsers() {
        ApiResponse<List<User>> response = new ApiResponse<>();
        try {
            CompletableFuture<List<User>> futureUsers = userService.findAllUsers();
            List<User> users = futureUsers.get(); // Đợi kết quả trả về
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ResponseObject("ok", "Lấy danh sách người dùng thành công.", users)
            );
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách!.", ""));
        } catch (java.util.concurrent.ExecutionException e) {
//            throw new RuntimeException(e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                    new ResponseObject("error", "Có lỗi trong quá trình lấy danh sách!.", ""));

        }
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseObject> login(@RequestBody UserRegistrationDTO userDTO) {
        if (userDTO != null) {
            try {
                User user = new User();
                String userTypePass = new String(userDTO.getPassword());
                String hashedPass = userService.getPasswordByEmail(userDTO.getEmail());

                if (checkPassword(userTypePass, hashedPass)) {
                    user = userService.findUserByEmail(userDTO.getEmail());
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("ok", "Login successfully", user)
                    );
                } else {
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                            new ResponseObject("Login fail", "Please Check your email or password!", null)
                    );
                }

            } catch (Exception e) {

                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("ok", "Login User fail", null)
                );
            }
        } else {
            return ResponseEntity.badRequest().body(new ResponseObject("", "", ""));
        }
    }
//    @GetMapping("/login")
//    public ResponseEntity<String> login(@RequestBody UserRegistrationDTO userDTO) {
//        if (userDTO != null) {
//            try {
//                User user = new User();
//                String userTypePass = new String(userDTO.getPassword());
//                String hashedPass = userService.getPasswordByEmail(userDTO.getEmail());
//
//                if (checkPassword(userTypePass, hashedPass)) {
//                    user = userService.findUserByEmail(userDTO.getEmail());
//                    return ResponseEntity.status(HttpStatus.OK).body(
//                            "Đăng nhập thành công!"
//                    );
//                } else {
//                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
//                            "Đăng nhập thất bại!"
//                    );
//                }
//
//            } catch (Exception e) {
//                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
//                        "Lỗi! Đăng nhập thất bại"
//                );
//            }
//        } else {
//            return ResponseEntity.badRequest().body("Lỗi!");
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<ResponseObject> registerUser(@RequestBody UserRegistrationDTO userDTO) {
        LOGGER.info("userDTO ", userDTO.toString());
        User existUser = userService.findUserByEmail(userDTO.getEmail());
        if (userDTO != null && existUser == null) {
            try {
                LOGGER.info("email ", userDTO.getEmail());
                User registerUser = new User();
                registerUser.setEmail(userDTO.getEmail());

                final LocalDateTime time = LocalDateTime.now();
                registerUser.setTgThamgGia(time);

                // Hash mật khẩu trước khi lưu vào cơ sở dữ liệu (điều này quan trọng cho bảo mật)
                String hashedPassword = hashPassword(userDTO.getPassword());
                registerUser.setPassword(hashedPassword);
//                registerUser.setPassword(userDTO.getPassword());
                registerUser.setCccd(userDTO.getCccd());

                // Gọi UserService để lưu người dùng
                User savedUser = userService.saveUser(registerUser);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Create User successfully", savedUser)
                );
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("ok", "Create User successfully", null)
                );
            }
        } else {
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Cccd or email invalid", ""));
        }
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseObject> updateUser(@RequestParam("idUser") Long idUser,
                                                     @RequestParam("hoTen") String hoTen,
                                                     @RequestParam("ngaySinh") String ngaySinh,
                                                     @RequestParam("gioiTinh") String gioiTinh,
                                                     @RequestParam("sdt") Long sdt,
                                                     @RequestParam("avtURL") MultipartFile avtURL) {
        User existUser = userService.findUserByIdUser(idUser);
        String errorMessage = "";
        if (existUser != null) {
            try {
                if (hoTen.length() <= 50) {
                    existUser.setHoTen(hoTen);
                } else {
                    return ResponseEntity.status(HttpStatus.OK).body(
                            new ResponseObject("error", "Họ và tên không hợp lệ.", "")
                    );
                }

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime localDateTime = LocalDate.parse(ngaySinh, formatter).atStartOfDay();
                existUser.setNgaySinh(localDateTime);

                if (gioiTinh.equalsIgnoreCase("true")) {
                    existUser.setGioiTinh("true");
                }

                if (gioiTinh.equalsIgnoreCase("false")) {
                    existUser.setGioiTinh("false");
                }

                existUser.setSdt(sdt);

                if(!avtURL.isEmpty()){
                    String avtName = imageStorageService.storeFile(avtURL);
                    Path[] paths = imageStorageService.loadFile(avtName).toArray(Path[]::new);
//                Stream<Path> pathStream = imageStorageService.loadFile(avtName);
                    //Kiểm tra xem file có tồn tại không
                    if (paths.length > 0) {
                        Path filePath = paths[0];
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(UserController.class,
                                "readDetailFile", filePath.getFileName().toString()).build().toUri().toString();
//                    UrlResource resource = new UrlResource(filePath.toUri());
//                    System.out.println("URL " + resource);
                        System.out.println("urlAVT " + urlPath);
                        existUser.setAvtURL(urlPath);
                    }
                }
                // Gọi UserService để lưu người dùng
                User updateUser = userService.saveUser(existUser);

                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "Update User successfully", updateUser)
                );
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                        new ResponseObject("ok", "Eror when update user", null)
                );
            }
        } else {
            return ResponseEntity.badRequest().body(new ResponseObject("error", "Input information invalid", ""));
        }
    }

    // Phương thức để hash mật khẩu (bạn cần triển khai)
    private String hashPassword(String password) {
        String salt = BCrypt.gensalt(); // Tạo một salt ngẫu nhiên
        String hashedPassword = BCrypt.hashpw(password, salt); // Hash mật khẩu với salt
        return hashedPassword;
    }

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        return BCrypt.checkpw(plainPassword, hashedPassword);
    }

    //Ham nay se duoc goi khi CompletableFuture hoan thanh ngoai le
    //Neu hoan thanh binh thuong thi no se tra ve danh sach o to
    private static Function<Throwable, ResponseEntity<? extends List<User>>> handleGetUserFailure = throwable -> {
        LOGGER.error("Failed to read records: {}", throwable);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    };

    @GetMapping("/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = imageStorageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);//dung thay cho HttpStatus.OK

        } catch (Exception e) {
            return ResponseEntity.noContent().build();
        }
    }


}
