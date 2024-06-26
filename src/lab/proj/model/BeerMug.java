package lab.proj.model;

import java.util.Collections;
import java.util.List;

/**
 * A class representing a beer mug item in the game environment.
 * Beer mugs extend the functionality of living items.
 */
public class BeerMug extends LivingItem {

    /**
     * Creates a new beer mug.
     */
    public BeerMug() {
        Logger.createObject(this);
    }

    /**
     * Applies charges associated with the beer mug.
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void ApplyCharges() {
        Logger.invokeMethod(this, List.of());

        if (fake || !activated || actor == null) {
            Logger.returnVoid();
            return;
        }

        DropOutProtection dop = new DropOutProtection(this, 0);
        actor.AddDropOutProtection(dop);

        Logger.returnVoid();
    }

    /**
     * Performs the action of using the beer mug.
     * This method adds drop-out protection for the associated actor.
     */
    @Override
    public void Use() {
        Logger.invokeMethod(this, List.of());

        // Under normal circumstances, it cannot cause exception since `actor` will always have at least this `BeerMug`.
        Item item = actor.GetItems().get(0);
        UseWithSpecificDropCandidate(item);

        Logger.returnVoid();
    }

    /**
     * Performs the action of using the beer mug with a specific drop candidate.
     * This method adds drop-out protection for the associated actor.
     *
     * @param dropCandidate The item to drop.
     */
    public void UseWithSpecificDropCandidate(Item dropCandidate) {
        Logger.invokeMethod(this, Collections.singletonList(dropCandidate));

        if (fake || actor == null || dropCandidate == null) {
            Logger.returnVoid();
            return;
        }

        dropCandidate.Drop();

        if (dropCandidate != this) {
            DropOutProtection dop = new DropOutProtection(this, 0);
            actor.AddDropOutProtection(dop);
        }

        Logger.returnVoid();
    }

    /**
     * Visits the item with the given visitor.
     *
     * @param iv The visitor to visit the item with.
     */
    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitBeerMug(this);
    }
}
