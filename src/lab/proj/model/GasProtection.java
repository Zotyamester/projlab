package lab.proj.model;

import java.util.List;

/**
 * A class representing a gas protection charge associated with a living item in the game environment.
 * Gas protections can affect the behavior of the associated living item.
 */
public class GasProtection extends Charge {

    /**
     * Constructs a new gas protection charge with the specified creator and priority.
     *
     * @param creator  The living item that created this gas protection.
     * @param priority The priority level of this gas protection.
     */
    public GasProtection(LivingItem creator, int priority) {
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
