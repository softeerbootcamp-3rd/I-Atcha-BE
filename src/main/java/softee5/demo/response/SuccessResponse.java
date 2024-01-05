package softee5.demo.response;

public class SuccessResponse extends BasicResponse{

    private final static String SUCCESS_STATUS = "OK";
    public SuccessResponse() {
        super(SUCCESS_STATUS);
    }
}
