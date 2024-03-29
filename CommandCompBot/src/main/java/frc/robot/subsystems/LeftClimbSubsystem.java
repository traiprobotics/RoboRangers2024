// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;
import frc.robot.commands.LeftClimbRatchet;

public class LeftClimbSubsystem extends SubsystemBase {
  /** Creates a new LeftClimbSubsystem. */

  private static CANSparkMax leftClimb = new CANSparkMax(12,MotorType.kBrushed);
  private static Servo ratchetServo = new Servo(0);

  private static DigitalInput leftLimit = new DigitalInput(1);

  public LeftClimbSubsystem() {
    ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
  }

  public void driveClimb(double speed) {
    System.out.println(leftLimit.get());
    if (leftLimit.get() == true && speed > 0) {
      leftClimb.stopMotor();
    } else {
      if (speed < 0) {
        ratchetServo.set(ClimbConstants.LEFT_SERVO_UNLOCK);
        if (ratchetServo.get() > ClimbConstants.LEFT_SERVO_UNLOCK - 9.1 && ratchetServo.get() < ClimbConstants.LEFT_SERVO_UNLOCK + 0.1) {
          leftClimb.set(speed);
        }
      } else {
        ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
        leftClimb.set(speed);
      }
    }
  }

  public void stopClimb() {
    leftClimb.stopMotor();
    ratchetServo.set(ClimbConstants.LEFT_SERVO_LOCK);
  }

  public void openRatchet() {
    ratchetServo.set(ClimbConstants.LEFT_SERVO_UNLOCK);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
