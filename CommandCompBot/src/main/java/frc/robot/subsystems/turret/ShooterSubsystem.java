// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterWheelSubsystem. */

  private static CANSparkMax shooterLeft = new CANSparkMax(10,MotorType.kBrushless);
  private static CANSparkMax shooterRight = new CANSparkMax(11,MotorType.kBrushless);

  public ShooterSubsystem() {
    shooterPitchEncoder = shooterPitch.getAbsoluteEncoder();
    shooterPIDController = shooterPitch.getPIDController();
    shooterPIDController.setFeedbackDevice(shooterPitchEncoder);

    shooterPIDController.setP(PIDConstants.PITCH_P);
    shooterPIDController.setI(PIDConstants.PITCH_I);
    shooterPIDController.setD(PIDConstants.PITCH_D);
    shooterPIDController.setFF(PIDConstants.PITCH_FF);
    shooterPIDController.setOutputRange(PIDConstants.PITCH_MIN, PIDConstants.PITCH_MAX);


  }

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
