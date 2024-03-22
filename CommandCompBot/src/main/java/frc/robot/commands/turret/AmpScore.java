// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;

public class AmpScore extends Command {
  /** Creates a new AmpScore. */

  public ShooterPitchSubsystem shooterPitchSubsystem;
  private double previousPitch;

  public AmpScore(ShooterPitchSubsystem shooterPitch) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooterPitchSubsystem = shooterPitch;
    addRequirements(shooterPitchSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    previousPitch = shooterPitchSubsystem.getEncoder();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterPitchSubsystem.setPitch(0.32);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterPitchSubsystem.setPitch(previousPitch);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
