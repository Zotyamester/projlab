package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CleaningLady extends Actor {

    private final PurifierVisitor rev;

    public CleaningLady() {
        Logger.createObject(this);
        rev = new PurifierVisitor();
    }

    @Override
    public void VisitActor(ActorVisitor v) {
        Logger.invokeMethod(this, List.of());
        v.VisitCleaningLady(this);
        Logger.returnVoid();
    }

    @Override
    public void Shock() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void DropOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        location.VisitEffects(rev);
        location.CleanRoom();

        Logger.returnVoid();
    }

    @Override
    public void GetOut() {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }

    @Override
    public boolean UseDoor(Door d) {
        Logger.invokeMethod(this, Collections.singletonList(d));

        boolean wasSuccessful = super.UseDoor(d);
        if (wasSuccessful)
            new CopyOnWriteArrayList<>(location.GetActors()).forEach(Actor::GetOut);

        Logger.returnValue(wasSuccessful);
        return wasSuccessful;
    }

    @Override
    public void NotifyStudentWin(Item item) {
        Logger.invokeMethod(this, List.of());

        // Empty.

        Logger.returnVoid();
    }
}
