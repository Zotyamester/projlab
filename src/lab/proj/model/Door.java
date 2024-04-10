package lab.proj.model;

import lab.proj.utils.SequenceDiagramPrinter;

import java.util.List;

/**
 * A class representing a door in the game environment.
 * Doors connect two rooms and can be hidden or shown.
 */
public class Door implements Entity {

    /**
     * A logger for debugging purposes.
     */
    private static final SequenceDiagramPrinter Logger = SequenceDiagramPrinter.getInstance();

    /**
     * Indicates whether the door is hidden or not.
     */
    private boolean hidden;

    /**
     * The first room connected by the door.
     */
    private Room r1;

    /**
     * The second room connected by the door.
     */
    private Room r2;

    boolean unidirect;

    public Door() {
        Logger.createObject(this);
    }

    /**
     * Hides the door, making it invisible.
     */
    public void Hide() {
        Logger.invokeMethod(this, List.of());

        hidden = true;

        Logger.returnVoid();
    }

    /**
     * Shows the door, making it visible.
     */
    public void Show() {
        Logger.invokeMethod(this, List.of());

        hidden = false;

        Logger.returnVoid();
    }

    /**
     * Attempts to go through the door from the specified room with the given actor.
     *
     * @param r The room from which the actor attempts to go through the door.
     * @param a The actor attempting to go through the door.
     * @return true if the actor successfully goes through the door, false otherwise.
     */
    public boolean GoThrough(Room r, Actor a) {
        Logger.invokeMethod(this, List.of(r, a));

        if (hidden) {
            Logger.returnValue(false);
            return false;
        }

        // Find the other room connected by the door
        Room o = GetRooms().stream()
                .filter(candidate -> candidate != r)
                .findAny()
                .orElseThrow(() -> new IllegalStateException("Door connects to the same room on both sides!"));
        boolean wasSuccessful = o.StepIn(a);

        Logger.returnValue(wasSuccessful);
        return wasSuccessful;
    }

    /**
     * Sets the rooms connected by the door.
     *
     * @param r1 The first room connected by the door.
     * @param r2 The second room connected by the door.
     */
    public void SetRooms(Room r1, Room r2) {
        Logger.invokeMethod(this, List.of(r1, r2));

        this.r1 = r1;
        this.r2 = r2;
        r1.AddDoor(this);
        r2.AddDoor(this);

        Logger.returnVoid();
    }

    /**
     * Retrieves the rooms connected by the door.
     *
     * @return A list containing the connected rooms.
     */
    public List<Room> GetRooms() {
        Logger.invokeMethod(this, List.of());
        Logger.returnValue(List.of(r1, r2));
        return List.of(r1, r2);
    }

    /**
     * Changes the rooms connected by the door.
     *
     * @param oldRoom The new first room connected by the door.
     * @param newRoom The new second room connected by the door.
     */
    public void ChangeRoom(Room oldRoom, Room newRoom) {
        Logger.invokeMethod(this, List.of(oldRoom, newRoom));

        if (this.r1 == oldRoom)
            this.r1 = newRoom;
        else if (this.r2 == oldRoom)
            this.r2 = newRoom;
        else
            throw new IllegalStateException("Old room is not a side of door");

        oldRoom.RemoveDoor(this);
        newRoom.AddDoor(this);

        Logger.returnVoid();
    }

    /**
     * Performs actions associated with the passage of time.
     * This method is currently empty for doors.
     */
    @Override
    public void TimePassed() {
        Logger.invokeMethod(this, List.of());

        // No actions for doors on time passage

        Logger.returnVoid();
    }
}
