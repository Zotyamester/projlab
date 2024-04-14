package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a gas poisoning room effect in the game environment.
 * Gas poisoning effects extend the functionality of room effects.
 */
public class GasPoisoning extends RoomEffect {

    /**
     * The remaining lifetime of the gas poisoning effect.
     */
    private int lifetime;

    public GasPoisoning() {
        Logger.createObject(this);
    }

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

        var gv = new GasVisitor(); // TODO: make it a field
        location.VisitActors(gv);

        Logger.returnVoid();
    }
}
