// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimbSubsystem;

public class RunClimbLeft extends Command {

  private LeftClimbSubsystem leftClimbSubsystem;
  private double climbSpeed;

  /** Creates a new RunClimbLeft. */
  public RunClimbLeft(LeftClimbSubsystem leftClimb, double speed) {
    this.leftClimbSubsystem = leftClimb;
    this.climbSpeed = speed;
    addRequirements(leftClimbSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }


  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftClimbSubsystem.driveClimb(climbSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    leftClimbSubsystem.stopClimb();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
