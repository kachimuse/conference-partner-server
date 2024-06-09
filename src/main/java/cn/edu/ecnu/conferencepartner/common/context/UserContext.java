package cn.edu.ecnu.conferencepartner.common.context;

/**
 * 用户上下文
 * @author 龚奕玮
 * @since 2024-06-09
 */
public class UserContext {

    private static final ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    public static void set(Long id) {
        threadLocal.set(id);
    }

    public static Long get() {
        return threadLocal.get();
    }

    public static void remove() {
        threadLocal.remove();
    }
}
