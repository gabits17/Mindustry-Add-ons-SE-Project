package mindustry.game;

import arc.struct.Seq;
import arc.struct.StringMap;
import java.time.LocalDate;
import java.time.LocalTime;

public class Copy extends Schematic{

    private final LocalDate date;
    private final LocalTime time;

    public Copy(Seq<Stile> tiles, StringMap tags, int width, int height){
        super(tiles, tags, width, height);
        this.date = LocalDate.now();
        this.time = LocalTime.now();
    }

    public boolean compare(Copy copy){
        return date.equals(copy.date) && time.equals(copy.time);
    }
}
