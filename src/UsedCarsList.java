public class UsedCarsList <T> implements LıstInterface<T> {
    private T[] carlist;
    private int numberOfEntries;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;
    public UsedCarsList()
    {
        this(DEFAULT_CAPACITY);
    }
    public UsedCarsList(int initialCapacity){
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;

        @SuppressWarnings("unchecked")
        T[] templist = (T[]) new Object[initialCapacity+1];
        carlist = templist;
        numberOfEntries = 0;}
    public void add(T newEntry){
        carlist[numberOfEntries+1] = newEntry;
        numberOfEntries++;
    }
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];
        for (int index=0;index<numberOfEntries;index++){
            result[index]=carlist[index+1];
        }
        return result;
    }
    public int getLength() {
        return numberOfEntries;
    }
    public boolean isEmpty(){
        return numberOfEntries == 0;
    }
    public void add(int newPosition,T newEntry){
        if((newPosition>=1)&&(newPosition<=numberOfEntries+1)){
            if(newPosition<=numberOfEntries)
                makeRoom(newPosition);
            carlist[newPosition] = newEntry;
            numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException("Given position of adds new entry is out of bounds ");
    }
    private void makeRoom(int newPosition){
        assert (newPosition>=1)&&(newPosition<=numberOfEntries+1);
        int newIndex = newPosition;
        int lastIndex = numberOfEntries;
        for (int index = lastIndex; index>=newIndex;index--)
            carlist[index+1] = carlist[index];
    }
    public T remove(int givenPosition){
        if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            T result = carlist[givenPosition];
            if (givenPosition<numberOfEntries)
                removeGap(givenPosition);
            numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    }
    private void removeGap(int givenPosition){
        assert (givenPosition>=1)&&(givenPosition<numberOfEntries);
        int removedIndex = givenPosition;
        int lastIndex = numberOfEntries;
        for (int index = removedIndex; index<lastIndex;index++)
            carlist[index] = carlist[index+1];
    }
    public T replace(int givenPosition, T newEntry){
        if((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            T originalEntry = carlist[givenPosition];
            carlist[givenPosition] = newEntry;
            return originalEntry;
        }
        else throw new IndexOutOfBoundsException("Illegal position given to replace operation");
    }
    public T getEntry(int givenPosition){
        if ((givenPosition>=1)&&(givenPosition<=numberOfEntries)){
            assert !isEmpty();
            return carlist[givenPosition];
        }
        else throw new IndexOutOfBoundsException("Illegal position given to replace operation");
    }
    public boolean contains(T anEntry){
        boolean found =false;
        int index=1;
        while (!found&&(index<=numberOfEntries)){
            if (anEntry.equals(carlist[index]))
                found = true;
            index++;
        }
        return found;
    }
    public void clear(){
        while (!isEmpty()){
            remove(0);
        }
    }
}
