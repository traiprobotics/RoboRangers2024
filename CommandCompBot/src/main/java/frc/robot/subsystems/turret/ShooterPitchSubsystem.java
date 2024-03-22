// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.PIDConstants;

public class ShooterPitchSubsystem extends SubsystemBase {
  /** Creates a new ShooterPitchSubsystem. */

  private static CANSparkMax shooterPitch = new CANSparkMax(8,MotorType.kBrushless);

  private final AbsoluteEncoder shooterPitchEncoder;
  private static SparkPIDController shooterPIDController;

  //private static SparkAbsoluteEncoder shooterPitchEncoder= shooterPitch.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
  
  public ShooterPitchSubsystem() {
    shooterPitch.restoreFactoryDefaults();
    shooterPitch.setSmartCurrentLimit(40);

    shooterPitchEncoder = shooterPitch.getAbsoluteEncoder();
    shooterPitchEncoder.setInverted(true);

    shooterPIDController = shooterPitch.getPIDController();
    shooterPIDController.setFeedbackDevice(shooterPitchEncoder);

    shooterPIDController.setP(PIDConstants.PITCH_P);
    shooterPIDController.setI(PIDConstants.PITCH_I);
    shooterPIDController.setD(PIDConstants.PITCH_D);
    shooterPIDController.setFF(PIDConstants.PITCH_FF);
    shooterPIDController.setOutputRange(PIDConstants.PITCH_MIN, PIDConstants.PITCH_MAX);
    
  }

  public void drivePitch(double pitchSpeed) {
    shooterPitch.set(pitchSpeed);
  }

  public void setPitch(double rotations) {
    shooterPIDController.setReference(rotations, CANSparkMax.ControlType.kPosition);
  }

  public void stopPitch() {
    shooterPitch.stopMotor();
  }

  public double getEncoder(){
    return shooterPitchEncoder.getPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
