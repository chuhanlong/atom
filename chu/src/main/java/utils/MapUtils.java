package utils;

/**
 * 地图坐标转换工具类
 * 
 * @author chuhanlong
 *
 */
public class MapUtils {
	
	final static double pi = 3.14159265358979324;
	final static double a = 6378245.0;
	final static double ee = 0.00669342162296594323;
	final static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
	
	/**
	 * 火星坐标系 (GCJ-02) 转百度坐标系 (BD-09) 高德地图使用火星坐标系
	 * 
	 * @param gg_lat
	 *            纬度
	 * @param gg_lon
	 *            经度
	 * @return 0位置为纬度，1位置为经度
	 */
	public static double[] baiduEncrypt(double gg_lat, double gg_lon)
			throws Exception {
		double x = gg_lon, y = gg_lat;
		double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
		double bd_lon = z * Math.cos(theta) + 0.0065;
		double bd_lat = z * Math.sin(theta) + 0.006;
		double[] baiduCoodinate = new double[2];
		baiduCoodinate[0] = bd_lat;
		baiduCoodinate[1] = bd_lon;
		return baiduCoodinate;
	}

	/**
	 * 百度坐标系 (BD-09) 转火星坐标系 (GCJ-02)
	 * 
	 * @param bd_lat
	 *            纬度
	 * @param bd_lon
	 *            经度
	 * @return
	 */
	public static double[] baiduDecrypt(double bd_lat, double bd_lon) {
		double x = bd_lon - 0.0065, y = bd_lat - 0.006;
		double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
		double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
		double gg_lon = z * Math.cos(theta);
		double gg_lat = z * Math.sin(theta);
		double[] marsCoodinate = new double[2];
		marsCoodinate[0] = gg_lat;
		marsCoodinate[1] = gg_lon;
		return marsCoodinate;
	}
}
