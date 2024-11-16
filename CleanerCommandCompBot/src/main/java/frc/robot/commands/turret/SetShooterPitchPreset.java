// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.turret;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.PitchConstants;
import frc.robot.subsystems.turret.ShooterPitchSubsystem;

public class SetShooterPitchPreset extends Command {
  /** Creates a new AmpScore. */

  public ShooterPitchSubsystem shooterPitchSubsystem;
  private double rotation;

  public SetShooterPitchPreset(ShooterPitchSubsystem shooterPitch, double rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.shooterPitchSubsystem = shooterPitch;
    this.rotation = rotation;
    addRequirements(shooterPitchSubsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooterPitchSubsystem.getPitch();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooterPitchSubsystem.setPitch(rotation);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooterPitchSubsystem.setPitch(PitchConstants.PITCH_MIN);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
