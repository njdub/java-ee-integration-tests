package dao.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Year;

/**
 * Created on 10-May-16.
 *
 * @author Nazar Dub
 */
@Converter(autoApply = true)
public class YearPersistenceConverter implements AttributeConverter<Year, Integer> {
    @Override
    public Integer convertToDatabaseColumn(Year attribute) {
        return attribute.getValue();
    }

    @Override
    public Year convertToEntityAttribute(Integer dbData) {
        return Year.of(dbData);
    }
}
