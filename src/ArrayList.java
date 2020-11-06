import org.omg.CosNaming.NamingContextPackage.NotFound;
import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

public class ArrayList<T> implements List {
    int length = 0;
    CelestialBody[] array;
    private static int DEFAULT_SIZE = 8;

    public ArrayList(){
        array = new CelestialBody[DEFAULT_SIZE];
        length = 0;

    }
    public int length() {
        return length;
    }
    public CelestialBody get(int index){
        if(index>length){
            throw new IndexOutOfBoundsException(); //if body is out of index.
        }
        return array[index];

    }
    public void remove(CelestialBody item_to_remove){
        int i = 0;
        while(i < length() && array[i] != item_to_remove){
            i++;
        }
        if(i == length){
            throw new IllegalArgumentException(); //when trying to remove something that's not there.
        }
        for(int j = i; j < length-1; j++){
            array[j] = array[j+1];
        }
        length--;
    }
    public void add(CelestialBody new_item){
        if(length>=array.length){
            CelestialBody[] newArray = new CelestialBody[array.length*2];
            for(int i = 0; i<array.length; i++){
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[length++] = new_item;
    }



}
