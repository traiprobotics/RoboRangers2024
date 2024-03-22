// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterWheelSubsystem. */

  private static CANSparkMax shooterLeft = new CANSparkMax(CANConstants.SHOOTER_LEFT,MotorType.kBrushless);
  private static CANSparkMax shooterRight = new CANSparkMax(CANConstants.SHOOTER_RIGHT,MotorType.kBrushless);

  public ShooterSubsystem() {}

  public void shoot(double leftSpeed, double rightSpeed) {
    shooterLeft.set(leftSpeed);
    shooterRight.set(-rightSpeed);
  }

  public void stopShooter() {
    shooterLeft.stopMotor();
    shooterRight.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
