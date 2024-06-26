package lab.proj.testUseCases;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A class for running test use cases.
 */
public class TestUseCaseRunner {
    /**
     * A wrapper class for test use cases.
     */
    private static final List<TestUseCaseWrapper> useCases = new ArrayList<>();

    static {
        // Add test use cases
        useCases.add(new TestUseCaseWrapper("Hallgató átlépni próbál a másik szobába", StudentSwitchesRooms.class));
        useCases.add(new TestUseCaseWrapper("Hallgató átlép, van tanár, nincs nála aktivált eszköz, nem védekezik", StudentSwitchesRoomsNoProtection.class));
        useCases.add(new TestUseCaseWrapper("Hallgató átlép, van tanár, TVSZ-el próbál védeni", StudentDefendWithCSE.class));
        useCases.add(new TestUseCaseWrapper("Hallgató átlép, van tanár, sörrel próbál védeni", StudentDefendsWithBeer.class));
        useCases.add(new TestUseCaseWrapper("Hallgató átlép, van tanár, sör és TVSZ is aktív, a sörrel próbál védeni", StudentDefendsWithBeerAndCSE.class));
        useCases.add(new TestUseCaseWrapper("Hallgató átlép, van tanár, törlőronggyal lebénítja", StudentStunsTeacher.class));
        useCases.add(new TestUseCaseWrapper("Hallgató gázos szobába lép, masszkal próbál védekezni ellene, van tanár", Gas.class));
        useCases.add(new TestUseCaseWrapper("Gázos szoba mérgez", GasPoisTeacher.class));
        useCases.add(new TestUseCaseWrapper("Hallgató Camembert-et használ", CamembertUsage.class));
        useCases.add(new TestUseCaseWrapper("Szobaátok érvényesül", CursedRoom.class));
        useCases.add(new TestUseCaseWrapper("Szoba osztódik", RoomSplit.class));
        useCases.add(new TestUseCaseWrapper("Szomszédos szobák egyesülnek", RoomMerge.class));
        useCases.add(new TestUseCaseWrapper("Tranzisztorok párosítása", TransistorPairing.class));
        useCases.add(new TestUseCaseWrapper("Tranzisztor használata", UseTransistors.class));
        useCases.add(new TestUseCaseWrapper("Hallgató felvenni próbál egy sört", StudentTriesToPickUpABeer.class));
        useCases.add(new TestUseCaseWrapper("Hallgató ledob egy sört", StudentDropsABeer.class));

        useCases.sort(Comparator.comparing(TestUseCaseWrapper::title));
    }

    /**
     * Runs a test use case.
     *
     * @param id The id of the test use case.
     */
    public static void runTest(int id) {
        try {
            TestUseCase useCase = useCases.get(id).testUseCaseClass().newInstance();
            useCase.runUseCase();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the titles of the available test use cases.
     *
     * @return The titles of the available test use cases.
     */
    public static List<String> getAvailableUseCases() {
        return useCases.stream().map(TestUseCaseWrapper::title).sorted().toList();
    }
}
