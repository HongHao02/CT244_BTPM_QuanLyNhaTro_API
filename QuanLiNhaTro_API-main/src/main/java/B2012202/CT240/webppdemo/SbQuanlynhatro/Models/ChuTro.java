package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "ChuTro")
//@IdClass(ChuTroID.class)
public class ChuTro {
    @EmbeddedId
    private ChuTroID id;

    //Nếu ta chỉ để quan hệ ở ChuTro thì chỉ có ChuTro moi truy xuat duoc den User ngược lại thì khong
    @MapsId("idUser")
    @OneToOne
    @JoinColumn(name = "idUser")
    private User user;

    //Tạo mã cho ChuTro: khởi chạy trước khi chủ trọ mới được thêm vào CSDL
//    @PrePersist
//    public void prePersist() {
//        // Kiểm tra xem trường user có tồn tại
//        if (user != null) {
//            // Tạo mã chủ trọ dựa trên id của user
//            this.maChuTro = "CT" + user.getIdUser();
//        } else {
//            // Xử lý trường hợp user không tồn tại (tùy ý)
//            // Ví dụ: Ném một ngoại lệ để báo lỗi
//            throw new RuntimeException("Lỗi!User của ChuTro không tồn tại.");
//        }
//    }

    @OneToMany(mappedBy = "chuTro", cascade = CascadeType.ALL)
    private List<NhaTro> nhaTros;

    public ChuTro() {
        this.id = new ChuTroID();
    }

    @PrePersist
    public void prePersist() {
        // Kiểm tra xem trường user có tồn tại
        if (user.getIdUser() != null) {
            // Tạo mã chủ trọ dựa trên id của user
            this.id.setMaChuTro("CT" + user.getIdUser());
            this.id.setUser_id(user.getIdUser());
        } else {
            // Xử lý trường hợp user không tồn tại (tùy ý)
            // Ví dụ: Ném một ngoại lệ để báo lỗi
            throw new RuntimeException("User của ChuTro không tồn tại.");
        }
    }

    public ChuTro(ChuTroID id, User user) {
        this.id = id;
        this.user = user;
    }

    public ChuTroID getId() {
        return id;
    }

    public void setId(ChuTroID id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
