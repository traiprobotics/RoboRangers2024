// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkPIDController;
import com.revrobotics.CANSparkBase.ControlType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.PIDConstants;

public class ShooterSubsystem extends SubsystemBase {
  /** Creates a new ShooterWheelSubsystem. */

  private static CANSparkMax shooterLeft = new CANSparkMax(CANConstants.SHOOTER_LEFT,MotorType.kBrushless);
  private static CANSparkMax shooterRight = new CANSparkMax(CANConstants.SHOOTER_RIGHT,MotorType.kBrushless);

  private static RelativeEncoder leftShooterEncoder;
  private static SparkPIDController leftPIDController;

  private static RelativeEncoder rightShooterEncoder;
  private static SparkPIDController rightPIDController;

  public ShooterSubsystem() {
    shooterLeft.setOpenLoopRampRate(0);
    shooterLeft.setOpenLoopRampRate(0);

    shooterLeft.setInverted(true);
    shooterRight.setInverted(true);

    leftShooterEncoder = shooterLeft.getEncoder();
    rightShooterEncoder = shooterRight.getEncoder();

    leftPIDController = shooterLeft.getPIDController();
    leftPIDController.setFeedbackDevice(leftShooterEncoder);

    rightPIDController = shooterRight.getPIDController();
    rightPIDController.setFeedbackDevice(rightShooterEncoder);

    leftPIDController.setP(PIDConstants.SHOOT_P);
    leftPIDController.setI(PIDConstants.SHOOT_I);
    leftPIDController.setD(PIDConstants.SHOOT_D);
    leftPIDController.setFF(PIDConstants.SHOOT_FF);
    leftPIDController.setOutputRange(PIDConstants.SHOOT_POWER_MIN, PIDConstants.SHOOT_POWER_MAX);

    rightPIDController.setP(PIDConstants.SHOOT_P);
    rightPIDController.setI(PIDConstants.SHOOT_I);
    rightPIDController.setD(PIDConstants.SHOOT_D);
    rightPIDController.setFF(PIDConstants.SHOOT_FF);
    rightPIDController.setOutputRange(PIDConstants.SHOOT_POWER_MIN, PIDConstants.SHOOT_POWER_MAX);
  
  }

  public void shoot(double leftSpeed, double rightSpeed) {
    leftPIDController.setReference(leftSpeed, ControlType.kVelocity);
    rightPIDController.setReference(rightSpeed, ControlType.kVelocity);
    System.out.println(leftShooterEncoder.getVelocity());
  }

  public void stopShooter() {
    leftPIDController.setReference(0, ControlType.kVelocity);
    rightPIDController.setReference(0, ControlType.kVelocity);

    shooterLeft.stopMotor();
    shooterRight.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public double getVelocity() {
    return leftShooterEncoder.getVelocity();
  }
}
