package softee5.demo.utils;

public class Compute {
    // Haversine 공식을 사용하여 두 지점 간의 거리를 계산하는 함수
    public static double haversine(double lat1, double lon1, double lat2, double lon2) {
        // 지구 반지름 (단위: km)
        final double R = 6371.0;

        // 위도 및 경도를 라디안으로 변환
        lat1 = Math.toRadians(lat1);
        lon1 = Math.toRadians(lon1);
        lat2 = Math.toRadians(lat2);
        lon2 = Math.toRadians(lon2);

        // Haversine 공식 계산
        double dlat = lat2 - lat1;
        double dlon = lon2 - lon1;

        double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }
}
