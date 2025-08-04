import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SecurityToolSimulator {

    // API Specification
    public interface Sensor {
        void triggerAlert();
    }

    public interface AlertSystem {
        void sendAlert(String message);
    }

    public class RealTimeSecuritySystem {
        private List<Sensor> sensors;
        private AlertSystem alertSystem;

        public RealTimeSecuritySystem(AlertSystem alertSystem) {
            this.alertSystem = alertSystem;
            this.sensors = new ArrayList<>();
        }

        public void addSensor(Sensor sensor) {
            sensors.add(sensor);
        }

        public void monitorSensors() {
            for (Sensor sensor : sensors) {
                // Simulate sensor reading and trigger alert if necessary
                if (Math.random() < 0.5) {
                    sensor.triggerAlert();
                }
            }
        }
    }

    public class CCTV implements Sensor {
        private String location;

        public CCTV(String location) {
            this.location = location;
        }

        @Override
        public void triggerAlert() {
            System.out.println("Motion detected at " + location);
        }
    }

    public class MotionDetector implements Sensor {
        private String location;

        public MotionDetector(String location) {
            this.location = location;
        }

        @Override
        public void triggerAlert() {
            System.out.println("Motion detected at " + location);
        }
    }

    public class EmailAlertSystem implements AlertSystem {
        private String email;

        public EmailAlertSystem(String email) {
            this.email = email;
        }

        @Override
        public void sendAlert(String message) {
            System.out.println("Sending alert to " + email + ": " + message);
        }
    }

    public static void main(String[] args) {
        EmailAlertSystem alertSystem = new EmailAlertSystem("alert@example.com");
        RealTimeSecuritySystem securitySystem = new RealTimeSecuritySystem(alertSystem);

        CCTV cctv = new CCTV("Entrance");
        MotionDetector motionDetector = new MotionDetector("Corridor");

        securitySystem.addSensor(cctv);
        securitySystem.addSensor(motionDetector);

        while (true) {
            securitySystem.monitorSensors();
            try {
                Thread.sleep(1000); // Monitor sensors every 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}