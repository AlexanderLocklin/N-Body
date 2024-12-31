import java.util.List;

public class Simulation {
    private List<Body> bodies;
    final double MIN = 0.0001;

    public Simulation(List<Body> bodies){
        this.bodies = bodies;
    }

    public void update(double time) {
        for (int i = 0; i < bodies.size(); i++) {
            Vector2D p1 = bodies.get(i).getPos();
            double m1 = bodies.get(i).getMass();

            for (int j = i + 1; j < bodies.size(); j++) {
                Vector2D p2 = bodies.get(j).getPos();
                double m2 = bodies.get(j).getMass();

                // Vector difference
                Vector2D r = p2.getSubtracted(p1);

                // Magnitude squared and magnitude
                double magSq = r.x * r.x + r.y * r.y;
                double mag = Math.sqrt(magSq);

                // Avoid division by zero
                double clampedMagSq;
                if(magSq > MIN){
                    clampedMagSq = magSq;
                }else{
                    clampedMagSq = MIN;
                }

                // Calculate force vector
                Vector2D tmp = r.getMultiplied(1.0 / (clampedMagSq * mag));

                // Update accelerations
                bodies.get(i).addAcc(tmp.getMultiplied(m2));
                bodies.get(j).addAcc(tmp.getMultiplied(-m1));
            }
        }

        // Update all bodies
        for (Body body : bodies) {
            body.update(time);
        }
    }
}

