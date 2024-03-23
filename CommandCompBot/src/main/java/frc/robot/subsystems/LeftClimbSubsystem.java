// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class LeftClimbSubsystem extends SubsystemBase {
  /** Creates a new LeftClimbSubsystem. */

  private static CANSparkMax leftClimb = new CANSparkMax(12,MotorType.kBrushed);
  private static Servo ratchetServo = new Servo(0);

  public LeftClimbSubsystem() {
    ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
  }

  public void driveClimb(double speed) {
    if (speed < 0) {
      ratchetServo.set(ClimbConstants.LEFT_SERVO_UNLOCK);
    } else {
      ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
    }
    //leftClimb.set(speed);
  }

  public void stopClimb() {
    leftClimb.stopMotor();
    ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
