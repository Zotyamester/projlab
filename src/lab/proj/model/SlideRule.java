package lab.proj.model;

import java.util.Collections;

public class SlideRule extends Item {

    public SlideRule() {
        Logger.createObject(this);
    }

    @Override
    public boolean PickUp(Actor a) {
        Logger.invokeMethod(this, Collections.singletonList(a));

        if (fake) {
            Logger.returnValue(false);
            return false;
        }

        boolean success = super.PickUp(a);
        if (success)
            a.NotifyStudentWin(this);

        Logger.returnValue(success);
        return success;
    }

    @Override
    public void VisitItem(ItemVisitor iv) {
        iv.VisitSlideRule(this);
    }
}
