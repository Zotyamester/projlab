package lab.proj.model;

import java.util.Collections;


/**
 * An abstract class representing a room effect in the game environment.
 * Room effects extend the functionality of basic entities and are associated with a specific room.
 */
public abstract class RoomEffect implements Entity {

    /**
     * The location (room) associated with the room effect.
     */
    protected Room location;

    /**
     * Sets the location (room) associated with the room effect.
     *
     * @param r The room to set as the location for the room effect.
     */
    public void SetLocation(Room r) {
        Logger.invokeMethod(this, Collections.singletonList(r));

        if (location != null)
            location.RemoveEffect(this);

        location = r;

        if (location != null)
            location.AddEffect(this);

        Logger.returnVoid();
    }

    /**
     * Visits the room effect.
     *
     * @param rev The visitor to visit the room effect.
     */
    public abstract void VisitRoomEffect(RoomEffectVisitor rev);
}
