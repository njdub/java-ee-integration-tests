package dao.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Date;
import java.time.LocalDate;


/**
 * Created on 10-May-16.
 *
 * @author Nazar Dub
 */
@Converter(autoApply = true)
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date> {
    @Override
    public java.sql.Date convertToDatabaseColumn(LocalDate entityValue) {
        return java.sql.Date.valueOf(entityValue);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date databaseValue) {
        return databaseValue.toLocalDate();
    }

}

