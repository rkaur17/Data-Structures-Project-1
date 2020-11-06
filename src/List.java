public interface List<T> {
    public void add(CelestialBody new_item);
    public void remove(CelestialBody item_to_remove);

    public CelestialBody get(int index);
    public int length();
}
