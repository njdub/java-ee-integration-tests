package dao.impl.mongodb;

import entity.Director;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public class DirectorCodec implements Codec<Director> {

    private static final String ID = "id";
    private static final String LAST_NAME = "last_name";
    private static final String FIRST_NAME = "first_name";
    private static final String BIRTH_DATE = "birth_date";

    @Override
    public Director decode(BsonReader reader, DecoderContext decoderContext) {
        Director d;
        reader.readStartDocument();
        reader.readObjectId();
        d = new Director(reader.readInt64());
        d.setLastName(reader.readString(LAST_NAME));
        d.setFirstName(reader.readString(FIRST_NAME));
        d.setBirthDate(new Date(reader.readDateTime(BIRTH_DATE)).toLocalDate());
        reader.readEndDocument();
        return d;
    }

    @Override
    public void encode(BsonWriter writer, Director d, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeInt64(ID, d.getId());
        writer.writeString(LAST_NAME, d.getLastName());
        writer.writeString(FIRST_NAME, d.getFirstName());
        writer.writeDateTime(BIRTH_DATE, Date.valueOf(d.getBirthDate()).getTime());
        writer.writeEndDocument();
    }

    @Override
    public Class<Director> getEncoderClass() {
        return Director.class;
    }
}
