package responses;

import java.io.Serializable;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/22 11:22
 * @Description:
 */
public class ApiResponses<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 不需要返回结果
     *
     * @param errcode
     */
    public static ApiResponses<Void> success(int errcode) {
        return SuccessResponses.<Void>builder().errcode(errcode).build();
    }

    /**
     * 成功返回 errcode 默认为0
     *
     * @param object
     */
    public static <T> ApiResponses<T> success(T object) {
        return success(0,object);

    }

    /**
     * 成功返回
     *
     * @param errcode
     * @param object
     */
    public static <T> ApiResponses<T> success(int errcode, T object) {
        return SuccessResponses.<T>builder().errcode(errcode).result(object).build();

    }

//    /**
//     * 失败返回
//     *
//     * @param errorCode
//     * @param exception
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> ApiResponses<T> failure(ErrorCode errorCode, Exception exception) {
//        return ResponseUtils.exceptionMsg(FailedResponse.builder().errmsg(errorCode.getErrmsg()), exception)
//                .errcode(errorCode.getErrcode())
//                .build();
//    }
}
