// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems.turret;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.AbsoluteEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkPIDController;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CANConstants;
import frc.robot.Constants.PIDConstants;

public class ShooterPitchSubsystem extends SubsystemBase {
  /** Creates a new ShooterPitchSubsystem. */

  private static CANSparkMax shooterPitch = new CANSparkMax(CANConstants.SHOOTER_PITCH,MotorType.kBrushless);

  private final AbsoluteEncoder shooterPitchEncoder;
  private static SparkPIDController shooterPIDController;

  //private static SparkAbsoluteEncoder shooterPitchEncoder= shooterPitch.getAbsoluteEncoder(SparkAbsoluteEncoder.Type.kDutyCycle);
  
  public ShooterPitchSubsystem() {
    shooterPitch.restoreFactoryDefaults();
    shooterPitch.setInverted(true);
    shooterPitch.setSmartCurrentLimit(40);

    shooterPitchEncoder = shooterPitch.getAbsoluteEncoder();
    shooterPitchEncoder.setInverted(false);

    shooterPIDController = shooterPitch.getPIDController();
    shooterPIDController.setFeedbackDevice(shooterPitchEncoder);

    shooterPIDController.setP(PIDConstants.PITCH_P);
    shooterPIDController.setI(PIDConstants.PITCH_I);
    shooterPIDController.setD(PIDConstants.PITCH_D);
    shooterPIDController.setFF(PIDConstants.PITCH_FF);
    shooterPIDController.setOutputRange(PIDConstants.PITCH_POWER_MIN, PIDConstants.PITCH_POWER_MAX);
    
  }

  // public static SparkPIDController getPIDObject() {
  //   return shooterPIDController;
  // }

  public void setPitch(double rotations) {
    shooterPIDController.setReference(rotations, CANSparkMax.ControlType.kPosition);
    //updateDashboard();
  }

  public void stopPitch() {
    shooterPitch.stopMotor();
  }

  public double getPitch(){
    return shooterPitchEncoder.getPosition();
  }

  // public void updateDashboard() {
  //   double p = SmartDashboard.getNumber("P Gain", 0);
  //   double i = SmartDashboard.getNumber("I Gain", 0);
  //   double d = SmartDashboard.getNumber("D Gain", 0);
  //   double ff = SmartDashboard.getNumber("Feed Forward", 0);
  //   double max = SmartDashboard.getNumber("Max Output", 0);
  //   double min = SmartDashboard.getNumber("Min Output", 0);

  //   if((p != PIDConstants.PITCH_P)) { shooterPIDController.setP(p); PIDConstants.PITCH_P = p; }
  //   if((i != PIDConstants.PITCH_I)) {  shooterPIDController.setI(i); PIDConstants.PITCH_I = i; }
  //   if((d != PIDConstants.PITCH_D)) {  shooterPIDController.setD(d); PIDConstants.PITCH_D = d; }
  //   if((ff != PIDConstants.PITCH_FF)) {  shooterPIDController.setFF(ff); PIDConstants.PITCH_FF = ff; }
  //   if((max != PIDConstants.PITCH_POWER_MAX) || (min != PIDConstants.PITCH_POWER_MIN)) { 
  //     shooterPIDController.setOutputRange(min, max); 
  //     PIDConstants.PITCH_POWER_MIN = min; PIDConstants.PITCH_POWER_MAX = max; 
  //   }
  // }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }


}
