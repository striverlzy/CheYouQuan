package responses;

import lombok.*;

/**
 * @Author：liuzhongyu
 * @Date: 2020/1/22 11:23
 * @Description:
 */
@Getter
@ToString
@Builder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class SuccessResponses<T> extends ApiResponses<T> {

    private static final long serialVersionUID = 1L;
    /**
     * 错误码
     */
    private Integer errcode;
    /**
     * 结果集返回
     */
    private T result;
}
