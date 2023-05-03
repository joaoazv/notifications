package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BaseMapper {

    private static final String AVRO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss,SSSxxx";
    private static final String AVRO_DATE_FORMAT_2 = "yyyy-MM-dd'T'HH:mm:ss,SSS+01:00";

    public static String getString(CharSequence cs) {
        return cs == null ? null : cs.toString();
    }

    public static OffsetDateTime parseDate(CharSequence cs) {
        return cs == null ? null : OffsetDateTime.parse(cs, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
