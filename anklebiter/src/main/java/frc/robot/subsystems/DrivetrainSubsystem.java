// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainSubsystem extends SubsystemBase {

  private static CANSparkMax frontLeftDrive = new CANSparkMax(1,MotorType.kBrushless);
  private static CANSparkMax frontRightDrive = new CANSparkMax(2,MotorType.kBrushless);
  private static CANSparkMax backLeftDrive = new CANSparkMax(3,MotorType.kBrushless);
  private static CANSparkMax backRightDrive = new CANSparkMax(4,MotorType.kBrushless);

  DifferentialDrive differentialDrive = new DifferentialDrive(frontRightDrive, frontLeftDrive);


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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
