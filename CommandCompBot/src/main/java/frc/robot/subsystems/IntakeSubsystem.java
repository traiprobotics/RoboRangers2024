// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.IntakeConstants;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new Intake. */

  private static CANSparkMax backIntake = new CANSparkMax(5,MotorType.kBrushed);;
  private static CANSparkMax frontIntake = new CANSparkMax(6,MotorType.kBrushed);;

  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void runIntake(double speed) {
    frontIntake.set(speed);
    backIntake.set(speed);
  }

  public void stopIntake() {
    frontIntake.stopMotor();
    backIntake.stopMotor();
  }
}
