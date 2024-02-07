package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.math.MathUtil;
// import edu.wpi.first.wpilibj.AnalogInput;
// import edu.wpi.first.wpilibj.DigitalInput;;

public class Manipulator {
    
    private static CANSparkMax intake;
    private static CANSparkMax feeder;

    public static final int AMPLIMIT = 60;


public Manipulator(){

        //SPARK MAXs
        intake = new CANSparkMax(6, MotorType.kBrushed);
        feeder = new CANSparkMax(7, MotorType.kBrushed);

        intake.setInverted(false);
        feeder.setInverted(true);

        //Set smart amp limit LOWER
        intake.setSmartCurrentLimit(AMPLIMIT);
        feeder.setSmartCurrentLimit(AMPLIMIT);
    }

    public static void intakeOut(){
        intake.set(1.0f);
        feeder.set(-1.0f);
    }

    public static void intakeIn(){
        intake.set(-1.0f);
        feeder.set(1.0f);
     
    }

    public static void intakeStop(){
        intake.stopMotor();
        feeder.stopMotor();
        System.out.println("STOP!!!");
    }
}

