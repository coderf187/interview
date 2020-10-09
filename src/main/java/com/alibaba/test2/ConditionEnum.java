package com.alibaba.test2;

import java.util.HashMap;
import java.util.Map;

/**
 * @author fengjian
 * @since 2020-10-09
 */
public enum ConditionEnum {
    /**
     * 条件and
     */
    AND("&&"),
    /**
     * 条件or
     */
    OR("||"),
    ;

    /**
     * 编码
     */
    private String code;

    ConditionEnum(String code) {
        this.code = code;
    }

    private static final Map<String, ConditionEnum> CACHE = new HashMap<>();

    static {
        for (ConditionEnum enu : ConditionEnum.values()) {
            CACHE.put(enu.getCode(), enu);
        }
    }

    /**
     * 编码转枚举
     *
     * @param code
     * @return 枚举，没有对应值返回null
     */
    public static ConditionEnum toEnum(String code) {
        if (null == code || "".equals(code)) {
            return null;
        }
        ConditionEnum enu = CACHE.get(code);
        return enu;
    }

    public String getCode() {
        return code;
    }
}
