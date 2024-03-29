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

  public AutoDrive(DrivetrainSubsystem drive, double distance, double angle) {
    this.drivetrainSubsystem = drive;
    this.desiredDistance = distance;
    this.desiredAngle = angle;
    addRequirements(drivetrainSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    drivetrainSubsystem.autoDrive(desiredDistance, desiredAngle);
    //if (drivetrainSubsystem.)
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
