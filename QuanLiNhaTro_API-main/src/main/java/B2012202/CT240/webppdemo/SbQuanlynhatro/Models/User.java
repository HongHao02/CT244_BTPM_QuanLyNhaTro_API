//Checked
package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;


@Entity
@Table(name = "NguoiDung")

public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1 //increment by 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long idUser;


    private Long cccd;
    private String hoTen;
    private LocalDateTime ngaySinh;
    private String gioiTinh;
    private Long sdt;

    private String email;
    private LocalDateTime tgThamGia;
    private String password;
    private String avtURL;

    public User() {
    }

    public User(Long cccd, String hoTen, LocalDateTime ngaySinh, String gioiTinh, Long sdt, String email, LocalDateTime tgThamGia, String password, String avtURL) {
        this.cccd = cccd;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.sdt = sdt;
        this.email = email;
        this.tgThamGia = tgThamGia;
        this.password = password;
        this.avtURL = avtURL;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    //getter
    public Long getIdUser() {
        return idUser;
    }

    public Long getCccd() {
        return cccd;
    }

    public String getHoTen() {
        return hoTen;
    }

    public LocalDateTime getNgaySinh() {
        return ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public Long getSdt() {
        return sdt;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getTgThamgGia() {
        return tgThamGia;
    }

    public String getPassword() {
        return password;
    }

    //setter
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public void setCccd(Long cccd) {
        this.cccd = cccd;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public void setNgaySinh(LocalDateTime ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public void setSdt(Long sdt) {
        this.sdt = sdt;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTgThamgGia(LocalDateTime tgThamgGia) {
        this.tgThamGia = tgThamgGia;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getTgThamGia() {
        return tgThamGia;
    }

    public void setTgThamGia(LocalDateTime tgThamGia) {
        this.tgThamGia = tgThamGia;
    }

    public String getAvtURL() {
        return avtURL;
    }

    public void setAvtURL(String avtURL) {
        this.avtURL = avtURL;
    }
}
