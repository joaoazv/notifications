package pt.itsector.notification.mappers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class BaseMapper {
    public static String getString(CharSequence cs) {
        return cs == null ? null : cs.toString();
    }

    public static OffsetDateTime parseDate(CharSequence cs) {
        return cs == null ? null : OffsetDateTime.parse(cs, DateTimeFormatter.ISO_OFFSET_DATE_TIME);
    }
}
