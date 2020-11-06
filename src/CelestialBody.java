public class CelestialBody {
    String name; //create all data members.
    float mass;
    int x_coordinate;
    int y_coordinate;
    float x_velocity;
    float y_velocity;
    int size_pixel;
    static final double G = 6.67 * Math.pow(10, -11);

    public static float TIME = 5000;

    public CelestialBody(String name, float mass, int x_coordinate, int y_coordinate, float x_velocity, float y_velocity,int size_pixel){ //pass in all data
        this.name = name;
        this.mass = mass;
        this.x_coordinate = x_coordinate;
        this.y_coordinate = y_coordinate;
        this.x_velocity = x_velocity;
        this.y_velocity = y_velocity;
        this.size_pixel = size_pixel;

    }

    public void update_velocity(float x_acceleration, float y_acceleration){  //formulas to update planet velocity
        x_velocity += x_velocity + (TIME * (x_acceleration/mass));
        y_velocity += y_velocity * (TIME * (y_acceleration/mass));;

    }
    public void update_position(float x_acceleration, float y_acceleration){ //formulas to update planet position
        x_coordinate = (int) (x_coordinate + (TIME* x_velocity));
        y_coordinate = (int) (y_coordinate + (TIME* y_velocity));

    }

    public float compute_xacceleration(List<CelestialBody> bodies) { //formula to compute  acceleration of bodies
        float total = 0;
        // for each celestial body, we calculate the next force all the other bodies have on it
        for (int i = 0; i < bodies.length(); i++) {
            if (!bodies.get(i).equals(this)) {
                // calculate euclidean distance
                double distance = Math.sqrt(Math.pow(x_coordinate - bodies.get(i).x_coordinate, 2) + Math.pow(y_coordinate - bodies.get(i).y_coordinate, 2));
                // formula: F = G (m​ × m​ ) / r​^2
                double accel = (G * mass * bodies.get(i).mass) / (distance * distance);
                //total force exerted by other bodies on this object
                total += accel;
            }
        }
        return total;

    }

    public float compute_yacceleration(List<CelestialBody> bodies) {
        float total = 0;
        for (int i = 0; i < bodies.length(); i++) {
            if (!bodies.get(i).equals(this)) {
                double distance = Math.sqrt(Math.pow(x_coordinate - bodies.get(i).x_coordinate, 2) + Math.pow(y_coordinate - bodies.get(i).y_coordinate, 2));
                double accel = (G * mass * bodies.get(i).mass) / (distance * distance);
                total += accel;
            }
        }
        return total;
    }

}
