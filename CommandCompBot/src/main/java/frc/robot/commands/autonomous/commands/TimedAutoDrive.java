// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class TimedAutoDrive extends Command {
  /** Creates a new AutoDrive. */

  private DrivetrainSubsystem drivetrainSubsystem;
  private double desiredDistance;
  //private double desiredAngle;
  private double encoderSetpoint;
  private double speed;
  private double timestamp;
  private double runtime;

  public TimedAutoDrive(DrivetrainSubsystem drive, double speed, double time) {
    this.drivetrainSubsystem = drive;
    this.speed = speed;
    this.runtime = speed;
    //this.desiredAngle = angle;
    //trying this without the angle implementation first

    addRequirements(drivetrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    timestamp = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrainSubsystem.arcadeDrive(speed, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (Timer.getFPGATimestamp() > timestamp + runtime) {
      return true;
    } else {
      return false;
    }
  }
}
