package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

// import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;;



public class Manipulator {
    
    private static CANSparkMax frontIntake;
    private static CANSparkMax backIntake;
    //private static CANSparkMax feeder;
    private static CANSparkMax shooterLeft;
    private static CANSparkMax shooterRight;
    


    public static final int AMPLIMIT = 60;
    private static final double INTAKE_SPEED = 0.75;


public Manipulator(){

        //SPARK MAXs
        frontIntake = new CANSparkMax(5, com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
        backIntake = new CANSparkMax(6, com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);
        //feeder = new CANSparkMax(7, com.revrobotics.CANSparkLowLevel.MotorType.kBrushed);

        // shooterLeft = new CANSparkMax(8, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
        // shooterRight = new CANSparkMax(5, com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);

        frontIntake.setInverted(false);
        backIntake.setInverted(false);
        //feeder.setInverted(false);
        //shooterRight.setInverted(true);
        //shooterLeft.setInverted(false);


        //Set smart amp limit LOWER
        frontIntake.setSmartCurrentLimit(AMPLIMIT);
        backIntake.setSmartCurrentLimit(AMPLIMIT);
        //feeder.setSmartCurrentLimit(AMPLIMIT);
    }

    // public static void intakeOut(){
    //     intake.set(1.0f);
    // }

    public static void frontIntakeIn(){
        frontIntake.set(INTAKE_SPEED);   
    }

    public static void backIntakeIn(){
        backIntake.set(-INTAKE_SPEED);
    }

    // public static void indexIn() {
    //     feeder.set(1.0f);  
    // }

    // public static void indexOut() {
    //     feeder.set(-0.5);
    //     System.out.println("AAAAAA");
    // }

    public static void shoot(double leftSpeed,double rightSpeed) {
        //shooterLeft.set(leftSpeed);
        //shooterRight.set(rightSpeed);
        // Robot.counter = 0;
        // if (Robot.counter > 100) {
        //     feeder.set(1.0f);
        // }
        System.out.println("FIRE IN THE HOLE");
        
    }

    public static void intakeStop(){
        frontIntake.stopMotor();
        backIntake.stopMotor();
        //feeder.stopMotor();
    }

    public static void shooterStop(){
        //shooterLeft.stopMotor();
        //shooterRight.stopMotor();
    }


}

