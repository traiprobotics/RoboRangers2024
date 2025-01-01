// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;

import static edu.wpi.first.units.Units.Meters;
import static edu.wpi.first.units.Units.MetersPerSecond;
import static edu.wpi.first.units.Units.Volts;


import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import java.util.function.DoubleSupplier;

public class DrivetrainSubsystem extends SubsystemBase {

  private static CANSparkMax frontLeftDrive = new CANSparkMax(1,MotorType.kBrushless);
  private static CANSparkMax frontRightDrive = new CANSparkMax(2,MotorType.kBrushless);
  private static CANSparkMax backLeftDrive = new CANSparkMax(3,MotorType.kBrushless);
  private static CANSparkMax backRightDrive = new CANSparkMax(4,MotorType.kBrushless);

  DifferentialDrive differentialDrive = new DifferentialDrive(frontRightDrive, frontLeftDrive);

  private static final RelativeEncoder leftDriveEncoder = frontLeftDrive.getEncoder();
  private static final RelativeEncoder rightDriveEncoder = frontRightDrive.getEncoder();

  private final MutVoltage appliedVoltage = Volts.mutable(0);
  private final MutDistance distance = Meters.mutable(0);
  private final MutLinearVelocity velocity = MetersPerSecond.mutable(0);


  private SysIdRoutine sysId = new SysIdRoutine(
    new SysIdRoutine.Config(),
    new SysIdRoutine.Mechanism(
      voltage -> {
        frontLeftDrive.setVoltage(voltage);
        frontRightDrive.setVoltage(voltage);
      }, 
      log -> {
        log.motor("LeftDrive")
          .voltage(
            appliedVoltage.mut_replace(
              frontLeftDrive.get() * RobotController.getBatteryVoltage(), Volts))
          .linearPosition(distance.mut_replace(leftDriveEncoder.get))
          )
        )
      }, this));


  /** Creates a new DrivetrainSubsystem. */
  public DrivetrainSubsystem() {

    backRightDrive.follow(frontRightDrive);
    backLeftDrive.follow(frontLeftDrive);

    frontRightDrive.setInverted(true);
    backRightDrive.setInverted(true);

    
    

  }

  public void arcadeDrive(double driveSpeed, double turnSpeed) {
    differentialDrive.arcadeDrive(driveSpeed, turnSpeed);
  }

  public void driveForward() {
    frontLeftDrive.set(0.5);
    frontRightDrive.set(0.5);
  }

  public void driveStop() {
    frontLeftDrive.set(0);
    frontRightDrive.set(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
