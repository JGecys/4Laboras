package studijosKTU;

public interface SortedSetADTx<E> extends SortedSetADT<E> {

    void add(String dataString);

    void load(String fName);

    String toVisualizedString(String treeDrawType, String dataCodeDelimiter);

    Object clone() throws CloneNotSupportedException;
}