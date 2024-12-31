import java.awt.Graphics;

public class Body {
    private Vector2D pos;
    private Vector2D vel;
    private Vector2D acc;
    private double mass;

    public Body(Vector2D pos, Vector2D vel, Vector2D acc, double mass){
        this.pos = pos;
        this.vel = vel;
        this.acc = acc;
        this.mass = mass;
    }

    public Vector2D getPos(){
        return pos;
    }

    public double getMass(){
        return mass;
    }

    public void addAcc(Vector2D change){
        acc.add(change);
    }

    public void update(double time){
        this.pos.add(this.vel.getMultiplied(time));
        this.vel.add(this.acc.getMultiplied(time));
        this.acc.set(0,0);
    }

    public void draw(Graphics g){
        int x = (int)this.pos.getComponents()[0];
        int y = (int)this.pos.getComponents()[1];
        g.fillOval(x,y,10,10);
    }
}
