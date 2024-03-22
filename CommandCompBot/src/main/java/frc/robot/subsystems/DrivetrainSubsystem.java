// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.Constants.DrivetrainConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

public class DrivetrainSubsystem extends SubsystemBase {
  /** Creates a new ExampleSubsystem. */


  private static CANSparkMax frontLeftDrive = new CANSparkMax(1,MotorType.kBrushless);;
  private static CANSparkMax frontRightDrive = new CANSparkMax(2,MotorType.kBrushless);;
  private static CANSparkMax backLeftDrive = new CANSparkMax(3,MotorType.kBrushless);;
  private static CANSparkMax backRightDrive = new CANSparkMax(4,MotorType.kBrushless);;

  //private RelativeEncoder frontRightEncoder = frontRightDrive.getEncoder();
  //private RelativeEncoder frontLeftEncoder = frontLeftDrive.getEncoder();
  
  // private MotorControllerGroup leftMotorControllerGroup = new MotorControllerGroup(frontLeftDrive,backLeftDrive)

  // PWMMotorController.addFollower(backLeftDrive);

  private double driveSpeedMult = DrivetrainConstants.DEFAULT_DRIVE_SPEED; 
  private double turnSpeedMult = DrivetrainConstants.DEFAULT_TURN_SPEED;

  DifferentialDrive differentialDrive = new DifferentialDrive(frontRightDrive, frontLeftDrive);


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


    //driveTrain = new DifferentialDrive(frontLeftDrive, frontRightDrive);
  }

  public void arcadeDrive(double driveSpeed, double turnSpeed) {
    differentialDrive.arcadeDrive(driveSpeed * driveSpeedMult, turnSpeed * turnSpeedMult);
  }

  public void stop() {
    differentialDrive.stopMotor();
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
