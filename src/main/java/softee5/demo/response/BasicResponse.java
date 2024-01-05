package softee5.demo.response;

public abstract class BasicResponse {
    private String status;

    public BasicResponse(String status) {
        this.status = status;
    }
}
