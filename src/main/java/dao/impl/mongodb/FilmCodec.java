package dao.impl.mongodb;

import entity.Film;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.time.Duration;
import java.time.Year;

/**
 * Created on 09-May-16.
 *
 * @author Nazar Dub
 */
public class FilmCodec implements Codec<Film> {

    private static final String ID = "id";
    private static final String TITLE = "title";
    private static final String DURATION = "duration";
    private static final String YEAR = "year";
    private static final String DESCRIPTION = "description";
    private static final String DIRECTOR = "director";

    @Override
    public Film decode(BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        reader.readObjectId();
        Film f = new Film(reader.readInt64(ID));
        f.setTitle(reader.readString(TITLE));
        f.setDuration(Duration.ofSeconds(reader.readInt64(DURATION)));
        f.setYear(Year.of(reader.readInt32(YEAR)));
        f.setDescription(reader.readString(DESCRIPTION));
        reader.readName(DIRECTOR);
        BsonType type = reader.getCurrentBsonType();
        if (type.equals(BsonType.NULL))
            reader.readNull();
        else
            reader.readInt64(); //TODO: lose director id;

        reader.readEndDocument();
        return f;
    }

    @Override
    public void encode(BsonWriter writer, Film f, EncoderContext encoderContext) {
        writer.writeStartDocument();
        writer.writeInt64(ID, f.getId());
        writer.writeString(TITLE, f.getTitle());
        writer.writeInt64(DURATION, f.getDuration().getSeconds());
        writer.writeInt32(YEAR, f.getYear().getValue());
        writer.writeString(DESCRIPTION, f.getDescription());
        if (f.getDirector() != null)
            writer.writeInt64(DIRECTOR, f.getDirector().getId());
        else
            writer.writeNull(DIRECTOR);
        writer.writeEndDocument();
    }

    @Override
    public Class<Film> getEncoderClass() {
        return Film.class;
    }
}
