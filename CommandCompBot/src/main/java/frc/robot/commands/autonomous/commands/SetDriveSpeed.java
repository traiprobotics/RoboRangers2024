// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.DrivetrainConstants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class SetDriveSpeed extends Command {
  /** Creates a new SetDriveSpeed. */

  private double leftSpeed;
  private double rightSpeed;

  public SetDriveSpeed(double leftSpeed, double rightSpeed) {
    this.leftSpeed = leftSpeed;
    this.rightSpeed = rightSpeed;
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    DrivetrainConstants.leftAutoSpeed = leftSpeed;
    DrivetrainConstants.rightAutoSpeed = rightSpeed;
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
