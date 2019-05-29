package demo.springboot.validation.util;

import lombok.Data;

import java.io.Serializable;

/**
 * @author dean.lee
 */
@Data
public class JsonResult implements Serializable {
    private static final long serialVersionUID = -1946193220290386110L;

    public static final boolean SUCCESS = true;
    public static final boolean FAIL = false;

    private boolean status;
    private int code;
    private String msg;
    private Object data;

    public JsonResult(boolean status, int code, String msg, Object data) {
        this.status = status;
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static JsonResult success(Object data) {
        return new JsonResult(SUCCESS, 200, "ok", data);
    }

    public static JsonResult success(String msg) {
        return new JsonResult(SUCCESS, 200, msg, null);
    }

    public static JsonResult fail(String msg) {
        return fail(400, msg);
    }

    public static JsonResult fail(int code, String msg) {
        return new JsonResult(FAIL, code, msg, null);
    }
}
