package utils;

import java.lang.reflect.Method;

import org.apache.commons.lang.StringUtils;
import org.springframework.util.ReflectionUtils;

/**
 * 将page属性set到example里
 * 
 * @author chuhanlong
 *
 */
public class PageUtils {

	public static <T> void setPageToExample(Page<T> page, Object obj) {
		if (page == null && obj == null) {
			return;
		}
		try {
			Method setLimitStart = obj.getClass().getMethod("setLimitStart", int.class);
			ReflectionUtils.invokeMethod(setLimitStart, obj, ((page.getPageNo()-1)*page.getPageSize()+1));
			Method setLimitEnd = obj.getClass().getMethod("setLimitEnd", int.class);
			ReflectionUtils.invokeMethod(setLimitEnd, obj, (page.getPageNo()*page.getPageSize()));
			if (StringUtils.isNotBlank(page.getOrderBy()) && page.getOrderBy().length() < 20) {
				Method setOrderByClause = obj.getClass().getMethod("setOrderByClause", String.class);
				ReflectionUtils.invokeMethod(setOrderByClause, obj, page.getOrderBy());
			}
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
