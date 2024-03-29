// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RightClimbSubsystem;

public class RunClimbRight extends Command {

  private RightClimbSubsystem rightClimbSubsystem;
  private double climbSpeed;

  /** Creates a new RunClimbRight. */
  public RunClimbRight(RightClimbSubsystem rightClimb, double speed) {
    this.rightClimbSubsystem = rightClimb;
    this.climbSpeed = speed;
    addRequirements(rightClimbSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     rightClimbSubsystem.driveClimb(climbSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    rightClimbSubsystem.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
