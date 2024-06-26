package lab.proj.model;

import java.util.List;

/**
 * A class representing a mask item in the game environment.
 * Masks extend the functionality of living items.
 */
public class Mask extends LivingItem {

    /**
     * Creates a new mask item.
     */
    public Mask() {
        Logger.createObject(this);
    }

    /**
     * Performs the action of using the mask.
     * This method is currently empty for masks.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        try{
        var gp = new GasProtection(this, 0);
        actor.AddGasProtection(gp);}
        catch (Exception e){
        }

        Logger.returnVoid();
    }

    /**
     * Applies the charges associated with the mask.
     * This method adds a gas protection charge to the actor using the mask.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (!activated || fake) {
            Logger.returnVoid();
            return;
        }

        var gp = new GasProtection(this, 0);
        actor.AddGasProtection(gp);

        Logger.returnVoid();
    }

    /**
     * Visits the mask item.
     *
     * @param iv The visitor to visit the item.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitMask(this);
    }
}
