// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

public class AutoDrive extends Command {
  /** Creates a new AutoDrive. */

  private DrivetrainSubsystem drivetrainSubsystem;
  private double desiredDistance;
  private double desiredAngle;
  private double encoderSetpoint;

  public AutoDrive(DrivetrainSubsystem drive, double distance, double angle) {
    this.drivetrainSubsystem = drive;
    this.desiredDistance = distance;
    //this.desiredAngle = angle;
    //trying this without the angle implementation first

    addRequirements(drivetrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    encoderSetpoint = drivetrainSubsystem.getEncoderFeet() + desiredDistance;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrainSubsystem.setDriveSpeed(0.3, 0.3);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    drivetrainSubsystem.setDriveSpeed(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if (drivetrainSubsystem.getEncoderFeet() > encoderSetpoint) {
      return true;
    } else {
      return false;
    }
  }
}
