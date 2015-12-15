package studijosKTU;

/**
 * Interfeisu aprašomas Aibės ADT.
 *
 * @param <E> Aibės elemento duomenų tipas
 */
public interface SetADT<E> extends Iterable<E> {

    /**
     * Patikrinama ar aibė tuščia.
     *
     * @return Grąžinama true, jei aibė tuščia.
     */
    boolean isEmpty();

    /**
     * Grąžinamas aibėje esančių elementų kiekis.
     *
     * @return Grąžinamas aibėje esančių elementų kiekis.
     */
    int size();

    /**
     * Išvaloma aibė.
     */
    void clear();

    /**
     * Aibė papildoma nauju elementu.
     *
     * @param element - Aibės elementas.
     */
    void add(E element);

    /**
     * Pašalinamas elementas iš aibės.
     *
     * @param element - Aibės elementas.
     */
    void remove(E element);

    /**
     * Patikrinama ar elementas egzistuoja aibėje.
     *
     * @param element - Aibės elementas.
     * @return Grąžinama true, jei elementas egzistuoja aibėje.
     */
    boolean contains(E element);

    /**
     * Grąžinamas aibės elementų masyvas.
     *
     * @return Grąžinamas aibės elementų masyvas.
     */
    Object[] toArray();
}
