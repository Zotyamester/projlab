package lab.proj.model;//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Door.java
//  @ Date : 2024. 03. 21.
//  @ Author : 
//
//


import lab.proj.utils.AskTheUser;
import lab.proj.utils.IndentedDebugPrinter;

import java.util.List;
import java.util.Optional;

public class Door implements Entity {
    private boolean hidden;
    private Room directedTowards;
    private Room source;

    public void Hide() {
    }

    public void Show() {
    }

    public boolean GoThrough(Room r, Actor a) {
        boolean doorCanBeSeen = AskTheUser.decision("Can the door be seen?");

        if (!doorCanBeSeen)
            return false;

        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, r, "StepIn", List.of(a));
        boolean wasSuccessful = r.StepIn(a);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, r, "StepIn", Optional.of(wasSuccessful));
        return wasSuccessful;
    }

    public void SetRooms(Room r1, Room r2) {
        source = r1;
        directedTowards = r2;
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, r1, "AddDoor", List.of(this));
        r1.AddDoor(this);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, r1, "AddDoor", Optional.empty());
        IndentedDebugPrinter.getInstance().invokeObjectMethod(this, r2, "AddDoor", List.of(this));
        r2.AddDoor(this);
        IndentedDebugPrinter.getInstance().returnFromMethod(this, r2, "AddDoor", Optional.empty());
    }

    public Room[] GetRooms() {
        return new Room[]{source, directedTowards};
    }

    public void ChangeRoom(Room r1, Room r2) {
    }

    @Override
    public void TimePassed() {

    }
}
