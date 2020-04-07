package responses;

import lombok.*;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/22 11:23
 * @Description:
 */
@Getter
@Setter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class FailedResponses extends ApiResponses {

    private static final long serialVersionUID = 1L;
    /**
     * 错误状态码
     */
    private int errcode;
    /**
     * 错误描述
     */
    private String errmsg;
    /**
     * 异常信息
     */
    private String exception;
}
