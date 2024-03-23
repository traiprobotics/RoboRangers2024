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
import frc.robot.Constants.YawConstants;

public class TurretYawSubsystem extends SubsystemBase {
  /** Creates a new TurretYawSubsystem. */

  private static CANSparkMax turretYaw = new CANSparkMax(CANConstants.TURRET_YAW,MotorType.kBrushless);
  
  private static RelativeEncoder turretYawEncoder;
  private static SparkPIDController turretPIDController;

  private double yaw;
  

  public TurretYawSubsystem() {

    turretYawEncoder = turretYaw.getAlternateEncoder(8192);
    turretYawEncoder.setInverted(true);
    turretYawEncoder.setPosition(0);
    turretYawEncoder.setPositionConversionFactor(1/6);

    turretPIDController = turretYaw.getPIDController();
    turretPIDController.setFeedbackDevice(turretYawEncoder);
    
    turretPIDController.setP(PIDConstants.YAW_P);
    turretPIDController.setI(PIDConstants.YAW_I);
    turretPIDController.setD(PIDConstants.YAW_D);
    turretPIDController.setFF(PIDConstants.YAW_FF);
    turretPIDController.setOutputRange(PIDConstants.YAW_POWER_MIN, PIDConstants.YAW_POWER_MAX);

    yaw = YawConstants.YAW_HOME;
    
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setYaw(double yaw) {
    turretPIDController.setReference(yaw, ControlType.kPosition);
  }

  public double getTurretYaw() {
    return turretYawEncoder.getPosition();
  }

}
