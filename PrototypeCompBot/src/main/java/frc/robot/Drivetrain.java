package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Drivetrain {
    
    private static DifferentialDrive driveTrain;
    
    private static CANSparkMax frontRightDrive;
    private static CANSparkMax frontLeftDrive;
    private static CANSparkMax backRightDrive;
    private static CANSparkMax backLeftDrive;

    public static float speed;
    public static float turnSpeed;
    private static final float DEFAULT_SPEED = 0.6f;
    private static final float SLOW_SPEED = 0.3f;
    private static final float SLOW_SPEED_MULTIPLIER = 1.6f;
    private static final float HIGH_SPEED_MULTIPLIER = 0.85f;
    private static final float SPRINT_SPEED = 1.8f;

    private static final double RAMP_RATE = 1;

    private static boolean toggleSlowed = true;

//   static SlewRateLimiter filter = new SlewRateLimiter(0.5);
//   static SlewRateLimiter filter2 = new SlewRateLimiter(0.5);

public Drivetrain() {

    frontRightDrive = new CANSparkMax(2,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    frontLeftDrive = new CANSparkMax(1,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    backRightDrive = new CANSparkMax(4,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    backLeftDrive = new CANSparkMax(3,com.revrobotics.CANSparkLowLevel.MotorType.kBrushless);
    frontRightDrive.setOpenLoopRampRate(RAMP_RATE);
    frontLeftDrive.setOpenLoopRampRate(RAMP_RATE);
    backRightDrive.setOpenLoopRampRate(RAMP_RATE);
    backLeftDrive.setOpenLoopRampRate(RAMP_RATE);

    frontRightDrive.setInverted(false);
    frontLeftDrive.setInverted(false);
    backRightDrive.setInverted(false);
    backLeftDrive.setInverted(false);

    backRightDrive.follow(frontRightDrive);
    backLeftDrive.follow(frontLeftDrive);

    driveTrain = new DifferentialDrive(frontLeftDrive, frontRightDrive);

    speed = DEFAULT_SPEED;
    turnSpeed = DEFAULT_SPEED;

    }

    public static void driveArcade(float pSpeed, float pRotation) {
        driveTrain.arcadeDrive(pSpeed, pRotation);
    }


    public static void sprintMode(Boolean sprint){
        if(sprint){
            speed = SPRINT_SPEED;
        }
        else{
            speed = DEFAULT_SPEED;
        }
    }

   public static void driveArcadeWithJoystick() {
        float forward;
        float slider = (float) MathUtil.clamp(-IO.driveJoystick.getRawAxis(IO.SLIDER), 0.5, 1);
        forward = (float) (IO.driveJoystick.getRawAxis(IO.Y_STICK_AXIS)* speed *(slider));
        
        float turn;
        turn = (float) (IO.driveJoystick.getRawAxis(IO.X_STICK_AXIS)* turnSpeed);
        driveArcade(turn, forward);
    }

    public static void stop() {
        frontLeftDrive.stopMotor();
        frontRightDrive.stopMotor();
        backLeftDrive.stopMotor();
        backRightDrive.stopMotor();
    }

    // public static void rightSpin() {

    // }      

    // public static void leftSpin() {

    
    // }
}