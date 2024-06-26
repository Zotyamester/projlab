package lab.proj.testUseCases;

import lab.proj.model.*;
import lab.proj.utils.AskTheUser;

/**
 * A class representing a specific use case with a student, two teachers, a door, and a CSE item.
 */
public class StudentDefendWithCSE implements TestUseCase {

    /**
     * Runs the use case, creating the student, teachers, door, and CSE item and setting the student in one room and the teachers in another.
     */
    @Override
    public void runUseCase() {
        Room r1 = new Room();
        Room r2 = new Room();
        Door d = new Door();
        d.SetRooms(r1, r2);

        Student s = new Student();
        s.SetLocation(r1);

        CSE cse = new CSE();
        boolean csePickedUp = cse.PickUp(s);
        if (!csePickedUp) {
            return;
        }

        int answer = AskTheUser.number("Hány élete maradt a TVSZ-nek? (1 vagy 2)");
        if (answer == 2) {
            cse.SetLifeTime(2);
        } else if (answer == 1) {
            cse.SetLifeTime(1);
        }
        cse.Activate();

        Teacher t1 = new Teacher();
        t1.SetLocation(r2);

        Teacher t2 = new Teacher();
        t2.SetLocation(r2);

        boolean success = s.UseDoor(d);
        s.TimePassed();

        t1.DropOutAll();
        t2.DropOutAll();
    }
}