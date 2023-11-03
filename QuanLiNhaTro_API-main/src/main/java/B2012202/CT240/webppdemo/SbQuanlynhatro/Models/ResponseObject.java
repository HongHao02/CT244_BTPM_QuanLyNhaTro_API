//Checked
package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

public class ResponseObject {
    private String status;
    private String message;
    //Ly do data co kieu la Object do du lieu tra ve co nhieu dang, ta chua biet duoc dang nao
    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }


}
