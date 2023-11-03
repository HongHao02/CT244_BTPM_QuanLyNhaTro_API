//Checked
package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class UserRegistrationDTO {
    private Long cccd;
    private String email;
    private String password;

    public UserRegistrationDTO() {
    }

    public UserRegistrationDTO(Long cccd, String email, String password) {
        this.cccd = cccd;
        this.email = email;
        this.password = password;
    }

    public Long getCccd() {
        return cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCccd(Long cccd) {
        this.cccd = cccd;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
