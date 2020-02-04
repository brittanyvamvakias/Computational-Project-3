
import java.awt.Shape;
import javax.swing.JFrame;
import org.opensourcephysics.frames.PlotFrame;

public class KeplerMovePlotApp4 {
  public static void main(final String[] args) {

        final double x0 = 10.;
        final double vx0 = 0.;
        final double y0 = 0.;
        final double vy0 = 0.1;
        double t = 0.;
        double dt = 0.0759;
        double E0, Es0, P, E;
        double Et, Est, B;

        final MovingObject planet = new MovingObject();

        planet.dt = dt;
        planet.x = x0;
        planet.vx = vx0;
        planet.y = y0;
        planet.vy = vy0;



        final MovingObject asteroid = new MovingObject();

        asteroid.dt = dt;
        asteroid.x = x0;
        asteroid.vx = vx0;
        asteroid.y = y0;
        asteroid.vy = vy0;

        planet.energy();
	    E0=planet.E;
	    asteroid.energy();
	    Es0=asteroid.E;


        // double x;
        // double dx=0.2;

        final PlotFrame frame = new PlotFrame("position", "amplitude", "Plot");
        final PlotFrame eframe = new PlotFrame("t/P", "Energy Error", "Plot");

    frame.setSize(600,600);
    frame.setConnected(true);
    frame.setMarkerShape(0,1);
    frame.setMarkerShape(1,1);
    // frame.setMarkerShape(2,1);
    // frame.setMarkerShape(3,1);
    frame.setMarkerSize(0,1);
    frame.setMarkerSize(1,1);
    // frame.setMarkerSize(2,3);
    // frame.setMarkerSize(3,3);

    eframe.setSize(600,600);
    eframe.setConnected(true);
    eframe.setMarkerShape(0,1);
    eframe.setMarkerShape(1,1);
    eframe.setMarkerSize(0,1);
    eframe.setMarkerSize(1,1);


  	  for(int n = 0;n<1000;n++) {
        // planet.sym2bstep(1.0);
		// asteroid.RK2step();
        planet.RKN4(1.0);
		asteroid.FR4(1.0); // something is wrong with this FR4
        P = 75.853;
        planet.energy();
		Et=planet.E;
        asteroid.energy();
        Est = asteroid.E;
		t=t+dt;
        // frame.append(0, planet.x, planet.y);
        // frame.append(1, asteroid.x, asteroid.y);
        eframe.append(0, t/P, (Et-E0)/(-(E0)));
        eframe.append(1, t/P, (Est-Es0)/(-(Es0)));
        // frame.append(2, x, Math.exp(-0.5*x*x));
        // frame.append(3, x, Math.exp(-0.5*x)*x);
        }// end of the for-loop
    // frame.setVisible(true);
    // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    eframe.setVisible(true);
    eframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }

}

