import org.omg.CosNaming.NamingContextPackage.NotFound;
import sun.jvm.hotspot.debugger.linux.LinuxDebugger;

public class LinkedList<T> implements List {
    Item head;
    Item last;
    int size;

    private class Item {
        Item previous;
        Item next;
        CelestialBody body;

        public Item(Item previous, Item next, CelestialBody body) {
            this.previous = previous;
            this.next = next;
            this.body = body;
        }
    }

    public LinkedList(){
        head = new Item(null, null, null);;
        last = new Item(null, null, null);;
        size = 0;
    }

    public void add(CelestialBody new_item) { //add celestial body
        if (size == 0) {
            Item i = new Item(head, null, new_item);
            head.next = i;
            last = i;
            size ++;
        } else {
            size++;
            // create a new item with head as its previous, next as null
            Item i = new Item(last, null, new_item);
            last.next = i;
            last = i;
        }
    }

    public void remove(CelestialBody item_to_remove) { //remove celestialbody.
        if (item_to_remove == last.body) {
            last = last.previous;
            last.next.previous = null;
            last.next = null;
        }
        Item pointer = head;
        while(pointer.next != null && pointer.body != item_to_remove) {
            pointer = pointer.next;
        }
        pointer.previous.next = pointer.next;
        pointer.next.previous = pointer.previous;
        pointer.previous = null;
        pointer.next = null;
        size --;
    }

    public CelestialBody get(int index) {
        if (size == 0 || index > size) {
            return null;
        }
        int i = 0;
        Item pointer = head.next;
        while(i < index) {
            pointer = pointer.next;
            i++;
        }
        return pointer.body;
    }

    public int length() {
        return size;
    }
}
