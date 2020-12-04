package com.example.demo.util;

/**
 * 常量
 */
public class Constant {
    /** 超级管理员ID */
    public static final int SUPER_ADMIN = 1;
    /*初始化密码*/
    public static final String password= "000000";
    /*文件访问路径*/
    public static final String getfile="/source";
    /** 用户启用 */
    public static final Integer userStatus_0=0;
    /** 用户禁用 */
    public static final Integer userStatus_1=1;
    /**
     * 菜单类型
     */
    public enum MenuType {
        /**
         * 分类
         */
        CLASSIFY(1),
        /**
         * 目录
         */
        CATALOG(2),
        /**
         * 菜单
         */
        MENU(3),
        /**
         * 按钮
         */
        BUTTON(4);

        private int value;

        MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }


    }
    /**
     * 正常状态
     */
    public static final Integer STATUS_NORMAL = 0;

    /**
     * 停止状态
     */
    public static final Integer STATUS_DISABLE = -1;

    /**
     * 删除标志
     */
    public static final Integer DEL_FLAG_1 = 1;

    /**
     * 未删除
     */
    public static final Integer DEL_FLAG_0 = 0;
}
