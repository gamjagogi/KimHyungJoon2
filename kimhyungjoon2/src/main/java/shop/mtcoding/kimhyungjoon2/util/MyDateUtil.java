package shop.mtcoding.kimhyungjoon2.util;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//시간 날짜 형식화
public class MyDateUtil {
    public static String toStringFormat(LocalDateTime localDateTime){
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-NN-dd HH:mm:ss"));
    }
}
