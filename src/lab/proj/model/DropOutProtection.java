package lab.proj.model;

import java.util.List;

/**
 * A class representing a drop-out protection charge associated with a living item in the game environment.
 * Drop out protections can affect the behavior of the associated living item.
 */
public class DropOutProtection extends Charge {

    /**
     * Constructs a new drop-out protection charge with the specified creator and priority.
     *
     * @param creator  The living item that created this drop out protection.
     * @param priority The priority level of this drops out protection.
     */
    public DropOutProtection(LivingItem creator, int priority) {
        super(creator, priority);
        Logger.createObject(this);
    }

    /**
     * Affects the behavior of the associated living item.
     * This method invokes the "Use" method of the creator living item.
     */
    @Override
    public void Affect() {
        Logger.invokeMethod(this, List.of());

        creator.Use();

        Logger.returnVoid();
    }
}
