// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.climb;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.LeftClimbSubsystem;
import frc.robot.subsystems.RightClimbSubsystem;

public class LeftClimbRatchet extends Command {
  /** Creates a new OpenClimbRatchet. */
  private LeftClimbSubsystem leftClimbSubsystem;
  private Servo ratchet;


  public LeftClimbRatchet(LeftClimbSubsystem leftClimb) {
    this.leftClimbSubsystem = leftClimb;
    addRequirements(leftClimbSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    leftClimbSubsystem.openRatchet();
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
