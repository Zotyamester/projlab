package lab.proj.model;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a gas poisoning room effect in the game environment.
 * Gas poisoning effects extend the functionality of room effects.
 */
public class GasPoisoning extends RoomEffect {

    /**
     * The visitor to visit actors in the location affected by the gas poisoning.
     */
    private final GasVisitor gv = new GasVisitor();
    /**
     * The remaining lifetime of the gas poisoning effect.
     */
    private int lifetime = 3;

    /**
     * Creates a new gas poisoning effect.
     */
    public GasPoisoning() {
        Logger.createObject(this);
    }

    /**
     * Visits the gas poisoning effect.
     *
     * @param rev The visitor to visit the room effect.
     */
    @Override
    public void VisitRoomEffect(RoomEffectVisitor rev) {
        Logger.invokeMethod(this, Collections.singletonList(rev));

        rev.VisitGasPoisoning(this);

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time for the gas poisoning effect.
     * This method visits actors in the location affected by the gas poisoning.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        location.VisitActors(gv);

        if (--lifetime <= 0) {
            lifetime = 0;
            location.GetActors().forEach(a -> a.incapacitated = false);
            SetLocation(null);
        }

        Logger.returnVoid();
    }
}
