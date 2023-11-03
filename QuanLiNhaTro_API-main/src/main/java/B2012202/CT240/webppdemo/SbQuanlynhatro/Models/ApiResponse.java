package B2012202.CT240.webppdemo.SbQuanlynhatro.Models;

//Class tổng quát dùng cho thông báo kết quả trả về của các request
public class ApiResponse<type> {
    private String title;
    private type data;

    public ApiResponse() {
    }

    public ApiResponse(String title, type data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public type getData() {
        return data;
    }

    public void setData(type data) {
        this.data = data;
    }
}
