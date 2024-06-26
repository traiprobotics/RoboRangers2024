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

public class RightClimbSubsystem extends SubsystemBase {
  /** Creates a new RightClimbSubsystem. */
 private static CANSparkMax rightClimb = new CANSparkMax(13,MotorType.kBrushed);

  private static Servo ratchetServo = new Servo(1);
  private static DigitalInput rightLimit = new DigitalInput(0);

  public RightClimbSubsystem() {
    ratchetServo.set(ClimbConstants.RIGHT_SERVO_UNLOCK);
  }

  public void driveClimb(double speed) {
    if (rightLimit.get() == true && speed > 0) {
      rightClimb.stopMotor();
    } else {
      if (speed <= 0) {
        ratchetServo.set(ClimbConstants.RIGHT_SERVO_UNLOCK);
        if (ratchetServo.get() > ClimbConstants.RIGHT_SERVO_UNLOCK - 9.1 && ratchetServo.get() < ClimbConstants.RIGHT_SERVO_UNLOCK + 0.1) {
          rightClimb.set(speed);
        }
      } else {
        ratchetServo.set(ClimbConstants.RIGHT_SERVO_LOCK);
        rightClimb.set(speed);
      }
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void openRatchet() {
    ratchetServo.set(ClimbConstants.RIGHT_SERVO_UNLOCK);
  }

  public void stopClimb() {
    rightClimb.stopMotor();
    ratchetServo.set(ClimbConstants.RIGHT_SERVO_LOCK);
  }
}
