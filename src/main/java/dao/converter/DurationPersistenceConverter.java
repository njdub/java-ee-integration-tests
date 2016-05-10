package dao.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Duration;

/**
 * Created on 10-May-16.
 *
 * @author Nazar Dub
 */
@Converter(autoApply = true)
public class DurationPersistenceConverter implements AttributeConverter<Duration, Long> {
    @Override
    public Long convertToDatabaseColumn(Duration attribute) {
        return attribute.getSeconds();
    }

    @Override
    public Duration convertToEntityAttribute(Long dbData) {
        return Duration.ofSeconds(dbData);
    }
}
