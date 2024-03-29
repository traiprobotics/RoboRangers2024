// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.Constants.PIDConstants;
import frc.robot.Constants.YawConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */


  private static CANSparkMax frontLeftDrive = new CANSparkMax(CANConstants.FRONT_LEFT_DRIVE,MotorType.kBrushless);
  private static CANSparkMax frontRightDrive = new CANSparkMax(CANConstants.FRONT_RIGHT_DRIVE,MotorType.kBrushless);
  private static CANSparkMax backLeftDrive = new CANSparkMax(CANConstants.BACK_LEFT_DRIVE,MotorType.kBrushless);
  private static CANSparkMax backRightDrive = new CANSparkMax(CANConstants.BACK_RIGHT_DRIVE,MotorType.kBrushless);

  //private RelativeEncoder frontRightEncoder = frontRightDrive.getEncoder();
  //private RelativeEncoder frontLeftEncoder = frontLeftDrive.getEncoder();
  
  // private MotorControllerGroup leftMotorControllerGroup = new MotorControllerGroup(frontLeftDrive,backLeftDrive)

  // PWMMotorController.addFollower(backLeftDrive);


  private double turnSpeedMult = DrivetrainConstants.DEFAULT_TURN_SPEED;

  DifferentialDrive differentialDrive = new DifferentialDrive(frontRightDrive, frontLeftDrive);

  private static RelativeEncoder leftDriveEncoder;
  private static SparkPIDController leftDrivePIDController;

  private static RelativeEncoder rightDriveEncoder;
  private static SparkPIDController rightDrivePIDController;

  private double leftDriveRotation;
  private double rightDriverotation;


  public DrivetrainSubsystem() {
    //frontLeftDrive = new CANSparkMax(1,MotorType.kBrushless);
    //frontRightDrive = new CANSparkMax(2,MotorType.kBrushless);
    //backLeftDrive = new CANSparkMax(3,MotorType.kBrushless);
    //backRightDrive = new CANSparkMax(4,MotorType.kBrushless);

    frontLeftDrive.setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);
    frontRightDrive.setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);
    backLeftDrive.setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);
    backRightDrive.setOpenLoopRampRate(DrivetrainConstants.RAMP_RATE);
    
    frontRightDrive.setInverted(true);
    backRightDrive.setInverted(true);

    backRightDrive.follow(frontRightDrive);
    backLeftDrive.follow(frontLeftDrive);

    leftDriveEncoder = frontLeftDrive.getEncoder();
    leftDriveEncoder.setPosition(0);

    rightDriveEncoder = frontRightDrive.getEncoder();
    rightDriveEncoder.setPosition(0);

    leftDrivePIDController = frontLeftDrive.getPIDController();
    leftDrivePIDController.setFeedbackDevice(leftDriveEncoder);

    rightDrivePIDController = frontRightDrive.getPIDController();
    rightDrivePIDController.setFeedbackDevice(rightDriveEncoder);
    
    leftDrivePIDController.setP(PIDConstants.DRIVE_P);
    leftDrivePIDController.setI(PIDConstants.DRIVE_I);
    leftDrivePIDController.setD(PIDConstants.DRIVE_D);
    leftDrivePIDController.setFF(PIDConstants.DRIVE_FF);
    leftDrivePIDController.setOutputRange(PIDConstants.DRIVE_POWER_MIN, PIDConstants.DRIVE_POWER_MAX);

    rightDrivePIDController.setP(PIDConstants.DRIVE_P);
    rightDrivePIDController.setI(PIDConstants.DRIVE_I);
    rightDrivePIDController.setD(PIDConstants.DRIVE_D);
    rightDrivePIDController.setFF(PIDConstants.DRIVE_FF);
    rightDrivePIDController.setOutputRange(PIDConstants.DRIVE_POWER_MIN, PIDConstants.DRIVE_POWER_MAX);

    leftDriveRotation = 0;
    rightDriverotation = 0;

  

    //driveTrain = new DifferentialDrive(frontLeftDrive, frontRightDrive);
  }

  public void arcadeDrive(double driveSpeed, double turnSpeed) {
    differentialDrive.arcadeDrive(driveSpeed, turnSpeed * turnSpeedMult);

  }

  // public void autoDrive(double desiredDistance) {

  //   //convert desired distance to rotations
  //   double desiredRotations = desiredDistance / DrivetrainConstants.WHEEL_CIRCUMFERENCE;
  //   rightDrivePIDController.setReference(desiredRotations, ControlType.kPosition);
  //   leftDrivePIDController.setReference(desiredRotations, ControlType.kPosition);
    
  // }

  public double leftAutoReference = 0;
  public double rightAutoReference = 0;

  public void autoDrive(double desiredDistance, double angle) {
    //get distances to reach angle (from garbage trig)
    double leftExtraDistance = DrivetrainConstants.WHEEL_DIAMETER * Math.sin(angle);
    double rightExtraDistance = -(DrivetrainConstants.WHEEL_DIAMETER * Math.sin(angle));

    //convert to rotations
    double leftDesiredRotations = leftExtraDistance / DrivetrainConstants.WHEEL_CIRCUMFERENCE;
    double rightDesiredRotations = rightExtraDistance / DrivetrainConstants.WHEEL_CIRCUMFERENCE;
    double desiredRotations = desiredDistance / DrivetrainConstants.WHEEL_CIRCUMFERENCE;

    rightDrivePIDController.setReference(desiredRotations + rightDesiredRotations, ControlType.kPosition);
    leftDrivePIDController.setReference(desiredRotations + leftDesiredRotations, ControlType.kPosition);
  }

  public double getLeftEncoder() {
    return leftDriveEncoder.getPosition();
  }

  public double getRightEncoder() {
    return rightDriveEncoder.getPosition();
  }

  public void stop() {
    differentialDrive.stopMotor();
  }

  public void driveAuto(double leftDrive, double rightDrive) {
    frontLeftDrive.set(leftDrive);
    backLeftDrive.set(leftDrive);
    frontRightDrive.set(rightDrive);
    backRightDrive.set(rightDrive);
  }

  /**
   * Example command factory method.
   *
   * @return a command
   */
  public Command exampleMethodCommand() {
    // Inline construction of command goes here.
    // Subsystem::RunOnce implicitly requires `this` subsystem.
    return runOnce(
        () -> {
          /* one-time action goes here */
        });
  }

  /**
   * An example method querying a boolean state of the subsystem (for example, a digital sensor).
   *
   * @return value of some boolean subsystem state, such as a digital sensor.
   */
  public boolean exampleCondition() {
    // Query some boolean state, such as a digital sensor.
    return false;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

}
